package com.example.tbc_assign22.presentation.extension

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TextView.epochToDate(epochTime: Long) {
    val date = Date(epochTime * 1000)
    val formatter = SimpleDateFormat("dd MMMM 'at' h:mm a", Locale.getDefault())
    text = formatter.format(date)
}