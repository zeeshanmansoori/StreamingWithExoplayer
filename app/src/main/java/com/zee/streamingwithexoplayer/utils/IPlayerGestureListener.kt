package com.zee.streamingwithexoplayer.utils

import android.view.GestureDetector
import android.view.MotionEvent

interface IPlayerGestureListener : GestureDetector.OnGestureListener {
    fun onTap()
    fun onHorizontalScroll(event: MotionEvent?, delta: Float)
    fun onVerticalScroll(event: MotionEvent?, delta: Float)
    fun onSwipeRight()
    fun onSwipeLeft()
    fun onSwipeBottom()
    fun onSwipeTop()
}