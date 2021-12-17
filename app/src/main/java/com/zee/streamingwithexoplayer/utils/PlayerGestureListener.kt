package com.zee.streamingwithexoplayer.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class PlayerGestureListener(context: Context, listener: MyGestureDetectorListener) :
    View.OnTouchListener {


    private var gestureDetector: GestureDetector = GestureDetector(context,listener)


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        gestureDetector.onTouchEvent(event)
        return true
    }
}


abstract class MyGestureDetectorListener : IPlayerGestureListener,
    GestureDetector.SimpleOnGestureListener() {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100

    }




    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {

        val deltaY = e2!!.y - e1!!.y
        val deltaX = e2.x - e1.x

        if (abs(deltaX) > abs(deltaY)) {
            if (abs(deltaX) > SWIPE_THRESHOLD) {
                onHorizontalScroll(e2, deltaX);
            }
        } else {
            if (abs(deltaY) > SWIPE_THRESHOLD) {
                onVerticalScroll(e2, deltaY);
            }
        }
        return false
    }
}

