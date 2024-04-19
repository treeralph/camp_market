package com.example.campcarrotmarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat

const val CHANNEL_ID = "CHANNEL_ID"
const val CHANNEL_NAME = "CHANNEL_NAME"
const val CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION"
const val NOTIFICATION_ID = 11

fun Context.notification() {
    val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val builder: NotificationCompat.Builder
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = CHANNEL_ID
        val channelName = CHANNEL_NAME
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
            setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            setSound(uri, audioAttributes)
            enableVibration(true)
        }
        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelId)
    } else builder = NotificationCompat.Builder(this)

    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_happy_face)
    builder.run {
        setSmallIcon(R.drawable.ic_happy_face)
        setWhen(System.currentTimeMillis())
        setContentTitle("새로운 알람이 있습니다.")
        setContentText("알람")
        setLargeIcon(bitmap)
    }

    manager.notify(NOTIFICATION_ID, builder.build())
}