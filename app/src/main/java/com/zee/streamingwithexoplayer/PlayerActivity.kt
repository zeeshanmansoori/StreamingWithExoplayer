package com.zee.streamingwithexoplayer

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.zee.streamingwithexoplayer.databinding.ActivityVideoPlayerBinding
import com.zee.streamingwithexoplayer.utils.Constants
import com.zee.streamingwithexoplayer.utils.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerActivity : AppCompatActivity() {


    private var exoPLayer: ExoPlayer? = null
    private var qualityBtn: ImageButton? = null

    private val binding: ActivityVideoPlayerBinding by lazy {
        ActivityVideoPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemBars(window)
        exoPLayer = ExoPlayer.Builder(this).build()

        lifecycleScope.launch {
            delay(700)
            setFullScreen()
        }

        setContentView(binding.root)
        binding.playerView.player = exoPLayer
        qualityBtn = binding.playerView.findViewById(R.id.quality_btn)

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

        //hideSystemBars(window)

        //supportActionBar?.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        //val parentParams = exoPlayerBinding.root.layoutParams
        //parentParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        //oldHeight = parentParams.height
        //parentParams.height = window.attributes.height
        //exoPlayerBinding.root.layoutParams = parentParams
        //exoPlayerBinding.root.layoutParams = parentParams
        //fullscreen = true
        //playBackListener?.onFullScreenChanged(fullscreen)

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
        showToast(this, "quality btn clicked")
    }

}

