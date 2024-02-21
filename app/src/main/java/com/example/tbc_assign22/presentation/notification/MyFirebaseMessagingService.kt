package com.example.tbc_assign22.presentation.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.tbc_assign22.R
import com.example.tbc_assign22.presentation.screen.activity.MainActivity
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

//        val pendingIntent = NavDeepLinkBuilder(this)
//            .setGraph(R.navigation.nav_graph)
//            .setDestination(R.id.detailsFragment)
//            .setArguments(args)
//            .createPendingIntent()

//        val detailsIntent = NavDeepLinkBuilder(this)
//            .setGraph(R.navigation.nav_graph)
//            .setDestination(R.id.detailsFragment)
//            .setArguments(args)
//            .createPendingIntent()
//
//        val stackBuilder = TaskStackBuilder.create(this).apply {
//            addNextIntentWithParentStack(Intent(this@MyFirebaseMessagingService, MainActivity::class.java))
//            addNextIntent(detailsIntent)
//        }
//        stackBuilder.addNextIntentWithParentStack(detailsIntent)

//        val detailsIntent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java).apply {
//            action = Intent.ACTION_VIEW
//            putExtra("destination", "detailsFragment")
//            putExtras(args)
//        }

//        val pendingIntent = NavDeepLinkBuilder(this)
//            .setGraph(R.navigation.nav_graph)
//            .setDestination(R.id.detailsFragment)
//            .setArguments(args)
//            .createPendingIntent()

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id", id)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

//        val stackBuilder = TaskStackBuilder.create(this).apply {
//            addNextIntentWithParentStack(detailsIntent)
//        }


//
//        val pendingIntent = stackBuilder.getPendingIntent(
//            0,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//        )


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