package com.dongguk.telepigeon.config

import com.dongguk.telepigeon.data.local.datasource.TelePigeonLocalDataSource
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TelePigeonMessagingService : FirebaseMessagingService(){
    @Inject
    lateinit var telePigeonLocalDataSource: TelePigeonLocalDataSource

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        telePigeonLocalDataSource.deviceToken = token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}