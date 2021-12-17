package com.zee.streamingwithexoplayer.utils

import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException

object BrightnessUtils {

    const val MAX_BRIGHTNESS = 255
    const val MIN_BRIGHTNESS = 0

    operator fun set(context: Context, brightness: Int) {
        val cResolver = context.contentResolver
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
    }

    operator fun get(context: Context): Int {
        val cResolver = context.contentResolver
        return try {
            Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: SettingNotFoundException) {
            0
        }
    }
}