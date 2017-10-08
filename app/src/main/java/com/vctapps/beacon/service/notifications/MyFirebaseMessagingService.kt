package com.vctapps.beacon.service.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.vctapps.beacon.R
import com.vctapps.beacon.presentation.buswaiting.WaitingBus


class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage?) {
        val intent = Intent(this, WaitingBus::class.java)

        val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_bus)
                .setContentTitle(p0?.data?.get("body"))
                .setContentText(p0?.data?.get("text"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)

        val mNotigyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        mNotigyManager.notify(0, mBuilder.build())
    }
}