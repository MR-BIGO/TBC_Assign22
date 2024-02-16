package com.example.tbc_assign22.presentation.notification

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.tbc_assign22.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(message.data["id"]?.toInt() ?: 0, message.data["title"] ?: "", message.data["desc"] ?: "")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    private fun showNotification(id: Int, title: String, desc: String) {
        val args = Bundle().also {
            it.putInt("id", id)
        }

        val pendingIntent = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.detailsFragment)
            .setArguments(args)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(desc)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notification.build())
    }
}