package com.dongguk.telepigeon.config

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dongguk.telepigeon.R
import com.dongguk.telepigeon.TelePigeonApp.Companion.CHANNEL_ID
import com.dongguk.telepigeon.data.local.datasource.TelePigeonLocalDataSource
import com.dongguk.telepigeon.feature.MainActivity
import com.dongguk.telpigeon.core.ui.util.context.colorOf
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

        Log.e("ㅋㅋ", message.toString())
        Log.e("ㅋㅋ", message.data.toString())

        sendPushAlarm(
            title = if (::title.isInitialized) title else "",
            body = if (::body.isInitialized) body else "",
            contentId = message.data[RELATED_CONTENT_ID] ?: "-1"
        )
    }

    private fun sendPushAlarm(title: String, body: String, contentId: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        val notification = buildNotification(title, body, contentId)
        notificationManager?.notify(NOTIFICATION_ID, notification)
    }

    private fun buildNotification(
        title: String,
        body: String,
        contentId: String
    ): Notification {
        val pendingIntent = createPendingIntent(contentId)
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

    private fun createPendingIntent(contentId: String): PendingIntent {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra(RELATED_CONTENT_ID, contentId)
        }
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }
    companion object {
        const val RELATED_CONTENT_ID = "relateContentId"
        const val NOTIFICATION_ID = 1
    }
}
