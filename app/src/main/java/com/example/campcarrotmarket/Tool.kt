package com.example.campcarrotmarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import java.text.NumberFormat
import java.util.Locale

fun temperatureFormatString(temperature: Double): String {
    return String.format("%.1f\u2103", temperature)
}

fun currencyFormatString(price: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale.KOREA)
    return format.format(price)
}

