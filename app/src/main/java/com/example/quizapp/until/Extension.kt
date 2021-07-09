package com.example.quizapp.until

import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*

val <T> T.exhaustive: T
    get() = this

fun Long.convertTimestampToDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}