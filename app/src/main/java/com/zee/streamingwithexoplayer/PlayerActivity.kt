package com.zee.streamingwithexoplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.core.view.*
import androidx.lifecycle.lifecycleScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.zee.streamingwithexoplayer.databinding.ActivityVideoPlayerBinding
import com.zee.streamingwithexoplayer.utils.*
import kotlinx.coroutines.*
import android.view.TouchDelegate
import androidx.compose.runtime.Composable


class PlayerActivity : AppCompatActivity() {


    private var exoPLayer: ExoPlayer? = null
    private var qualityBtn: ImageButton? = null
    //private var brightnessController: LinearLayoutCompat? = null

    private val binding: ActivityVideoPlayerBinding by lazy {
        ActivityVideoPlayerBinding.inflate(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemBars(window)
        exoPLayer = ExoPlayer.Builder(this).build()

        lifecycleScope.launch {
            delay(700)
            setFullScreen()
        }

        setContentView(binding.root)

        increaseClickArea(binding.root,binding.progressBar)
        binding.playerView.player = exoPLayer
        qualityBtn = binding.playerView.findViewById(R.id.quality_btn)

        //brightnessController = binding.playerView.findViewById(R.id.brightness_controller)

//        brightnessController?.apply {
//            setOnTouchListener(PlayerGestureListener(this@PlayerActivity, GestureListener(this)))
//        }


        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                binding.seekbar.alpha = 1f
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                binding.seekbar.alpha = 0f
            }
        })
//        binding.seekbar.setOnTouchListener(
//            PlayerGestureListener(
//                this@PlayerActivity,
//                GestureListener(binding.seekbar)
//            )
//        )
        intent.getStringExtra(Constants.STREAM_URL)?.toUri()?.let { uri ->

            val mediaItem: MediaItem =
                MediaItem.fromUri(uri)

            exoPLayer?.apply {

                qualityBtn?.setOnClickListener(qualityBtnListener)
                addListener(playBackListener)
                setMediaItem(mediaItem)
                prepare()
                play()

            }
        }
    }


    private fun hideSystemBars(window: Window) {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun setFullScreen() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }


    override fun onPause() {
        super.onPause()
        exoPLayer?.pause()
    }

    override fun onStop() {
        super.onStop()
        exoPLayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPLayer?.release()
        exoPLayer = null
    }


    // listener
    private val playBackListener = object : Player.Listener {
        override fun onIsLoadingChanged(isLoading: Boolean) {
            super.onIsLoadingChanged(isLoading)
            binding.progressBar.isVisible = isLoading
        }
    }


    private val qualityBtnListener = View.OnClickListener {
        it.blockClickResponse()
        showVideoTrackBtmSheet(it)
    }


    private fun showVideoTrackBtmSheet(view: View) {
        val popup = PopupMenu(this, view)

        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
        popup.menu[1].apply {
            isChecked = true
            isCheckable = true
        }

        popup.setOnMenuItemClickListener {
            showToast(this, "${it.title}")
            return@setOnMenuItemClickListener true
        }

        popup.show()
    }


    private class GestureListener(val seekBar: AppCompatSeekBar) :
        MyGestureDetectorListener() {
        var job: Job? = null

        override fun onTap() {

        }

        override fun onHorizontalScroll(event: MotionEvent?, delta: Float) {

        }

        override fun onVerticalScroll(event: MotionEvent?, delta: Float) {

            if (!Settings.System.canWrite(seekBar.context)) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                startActivity(seekBar.context, intent, null)
                return

            }
            val isVisible = seekBar.visibility == View.VISIBLE
            if (!isVisible) {
                seekBar.visibility = View.VISIBLE
            }
//            job?.cancel()
//            job = CoroutineScope(Dispatchers.Main).launch {
//                delay(500)
//                brightnessController.forEach { child ->
//                    child.visibility = View.INVISIBLE
//                }
//            }

            val level = extractVerticalDeltaScale(-delta, seekBar)

            //updateBrightnessProgressBar(level, brightnessController[1] as ProgressBar)

            //log("outside onVerticalScroll val $delta")
            //ObjectAnimator.ofFloat(brightnessController,"alpha",0,1)
            //BrightnessUtils.set(context = brightnessController.context,)
        }

        override fun onSwipeRight() {

        }

        override fun onSwipeLeft() {
            log("Gesture Listener outside onSwipeLeft")
        }

        override fun onSwipeBottom() {
            log("Gesture Listener outside onSwipeBottom ")
        }

        override fun onSwipeTop() {
            log("Gesture Listener outside onSwipeTop")
        }


        fun extractVerticalDeltaScale(deltaY: Float, progressBar: ProgressBar): Int {
            return extractDeltaScale(progressBar.height, deltaY, progressBar)
        }

        private fun extractDeltaScale(
            availableSpace: Int,
            deltaX: Float,
            progressBar: ProgressBar
        ): Int {
            val x = deltaX.toInt()
            val scale: Float
            var progress = progressBar.progress.toFloat()
            val max = progressBar.max
            if (x < 0) {
                scale = x.toFloat() / (max - availableSpace).toFloat()
                progress -= scale * progress
            } else {
                scale = x.toFloat() / availableSpace.toFloat()
                progress += scale * max
            }
            return progress.toInt()
        }


        private fun updateBrightnessProgressBar(v: Int, progressBar: ProgressBar) {
            val value = when (v) {
                BrightnessUtils.MIN_BRIGHTNESS -> BrightnessUtils.MIN_BRIGHTNESS
                BrightnessUtils.MAX_BRIGHTNESS -> BrightnessUtils.MAX_BRIGHTNESS
                else -> v
            }

            val oldBrightnessVal = BrightnessUtils[progressBar.context]
            if (value == oldBrightnessVal)
                return
            BrightnessUtils[progressBar.context] = v
            progressBar.progress = v

        }
    }

    fun increaseClickArea(parent: View, child: View) {

        // increase the click area with delegateArea, can be used in + create
        // icon
        parent.post { // Post in the parent's message queue to make sure the
            // parent
            // lays out its children before we call getHitRect()
            val delegateArea = Rect()
            child.getHitRect(delegateArea)
            delegateArea.top -= 600
            delegateArea.bottom += 600
            delegateArea.left -= 600
            delegateArea.right += 600
            val expandedArea = TouchDelegate(
                delegateArea,
                child
            )
            // give the delegate to an ancestor of the view we're
            // delegating the
            // area to
            if (View::class.java.isInstance(child.parent)) {
                (child.parent as View).touchDelegate = expandedArea
            }
        }
    }


    @Composable
    fun SetUpBrightnessController() {
        
    }
}


