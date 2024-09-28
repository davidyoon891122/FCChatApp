package com.example.fcchatapp

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.app.NotificationChannel
import androidx.core.app.NotificationCompat

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        // Create the NotificationChannel.
        val name = "채팅 알림"
        val descriptionText = "채팅 알림입니다."
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(getString(R.string.default_notification_channel_id), name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system. You can't change the importance
        // or other notification behaviors after this.
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        val body = message.notification?.body ?: ""
        val notificationBuilder = NotificationCompat.Builder(applicationContext, getString(R.string.default_notification_channel_id))
            .setSmallIcon(R.drawable.baseline_chat_24)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(body)

        notificationManager.notify(0, notificationBuilder.build())

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}