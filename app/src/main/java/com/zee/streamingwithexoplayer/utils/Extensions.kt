package com.zee.streamingwithexoplayer.utils

import android.view.View
import kotlinx.coroutines.*


fun View.blockClickResponse(blockTime: Long = 500) {
    runBlocking {
        CoroutineScope(Dispatchers.Main).launch {
            isClickable = false
            delay(blockTime)
            isClickable = true

        }
    }
}
