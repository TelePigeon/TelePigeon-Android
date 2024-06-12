package com.dongguk.telepigeon.config

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.dongguk.telepigeon.TelePigeonApp.Companion.CHANNEL_ID
import com.dongguk.telepigeon.data.local.datasource.TelePigeonLocalDataSource
import com.dongguk.telepigeon.feature.MainActivity
import com.dongguk.telpigeon.core.ui.util.context.colorOf
import com.google.firebase.messaging.Constants.MessageNotificationKeys.ENABLE_NOTIFICATION
import com.google.firebase.messaging.Constants.MessageNotificationKeys.NOTIFICATION_PREFIX
import com.google.firebase.messaging.Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TelePigeonMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var telePigeonLocalDataSource: TelePigeonLocalDataSource
    private lateinit var title: String
    private lateinit var body: String

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        telePigeonLocalDataSource.deviceToken = token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        sendPushAlarm(
            title = if (::title.isInitialized) title else "",
            body = if (::body.isInitialized) body else "",
            roomId = message.data[ROOM_ID] ?: "-1",
        )
    }

    override fun handleIntent(intent: Intent?) {
        val newPushAlarmIntent =
            intent?.apply {
                val temp =
                    extras?.apply {
                        title = getString(NOTIFICATION_TITLE).orEmpty()
                        body = getString(NOTIFICATION_BODY).orEmpty()
                        remove(ENABLE_NOTIFICATION)
                        remove(getKeyWithOldPrefix())
                    }
                replaceExtras(temp)
            }
        super.handleIntent(newPushAlarmIntent)
    }

    private fun getKeyWithOldPrefix(): String {
        val key = ENABLE_NOTIFICATION
        return if (!key.startsWith(NOTIFICATION_PREFIX)) {
            key
        } else {
            key.replace(
                NOTIFICATION_PREFIX,
                NOTIFICATION_PREFIX_OLD,
            )
        }
    }

    private fun sendPushAlarm(
        title: String,
        body: String,
        roomId: String,
    ) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        val notification = buildNotification(title, body, roomId)
        notificationManager?.notify(NOTIFICATION_ID, notification)
    }

    private fun buildNotification(
        title: String,
        body: String,
        roomId: String,
    ): Notification {
        val pendingIntent = createPendingIntent(roomId)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(com.dongguk.telepigeon.core.design.system.R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setColor(colorOf(com.dongguk.telepigeon.core.design.system.R.color.lemon))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setShowWhen(true)
            .build()
    }

    private fun createPendingIntent(roomId: String): PendingIntent {
        val intent =
            Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                putExtra(ROOM_ID, roomId)
            }
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE,
        )
    }

    companion object {
        const val ROOM_ID = "id"
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_TITLE = "gcm.notification.title"
        const val NOTIFICATION_BODY = "gcm.notification.body"
    }
}
