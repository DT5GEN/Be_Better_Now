package com.deeppowercrew.bebetternow.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    @SuppressLint("SimpleDateFormat")
    val formater = SimpleDateFormat("mm:ss")

    fun getTime(time: Long) : String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return formater.format(calendar.time)
    }

}