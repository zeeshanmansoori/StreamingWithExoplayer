package com.zee.streamingwithexoplayer.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class PlayerGestureListener(context: Context, listener: MyGestureDetectorListener) :
    View.OnTouchListener {


    private var gestureDetector: GestureDetector = GestureDetector(context, listener)


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
        //log("e1 ${e1?.action} distance x $distanceX")
        //log("e2 ${e2?.action} distance y $distanceY")
        if (e1 == null || e2 == null) return true
        val deltaY = abs(abs(e2.y) - e1.y)
        val deltaX = abs(abs(e2.x) - e1.x)

        //log("action ${e2.action } delta y = e2.x - e1.x = $deltaY")

        if (e2.action == MotionEvent.ACTION_MOVE) {
            //log("action starting point y = ${e1.y} y_precision ${e1.yPrecision} y_raw ${e1.rawY}")
            log("action end point y = ${e2.y} delta $deltaY and distanceY $distanceY")

        }
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

