package com.zee.streamingwithexoplayer.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun log(msg: String?, exception: Exception? = null) {
    var finalString = msg
    if (exception != null)
        finalString += " $exception"
    Log.d("zeeshan", finalString.toString())
}

fun log(exception: Exception) {
    Log.e("zeeshan", "$exception")
}

fun showToast(
    context: Context,
    msg: String,
    duration: Int = Toast.LENGTH_SHORT,
    log: Boolean = false
) {
    Toast.makeText(context, msg, duration).show()
    if (log){
        log(msg)
    }
}