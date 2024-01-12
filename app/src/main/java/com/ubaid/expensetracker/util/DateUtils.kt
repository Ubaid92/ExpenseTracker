package com.ubaid.expensetracker.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun getFormattedTime(timeStamp:String):String{
        val simpleDateFormat = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.US)
        return simpleDateFormat.format(Date(timeStamp.toLong()))
    }
}