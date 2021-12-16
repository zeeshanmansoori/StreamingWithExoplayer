package com.zee.streamingwithexoplayer.presentation.player_screen

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.zee.streamingwithexoplayer.utils.Constants
import com.zee.streamingwithexoplayer.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerViewModel @Inject
constructor(
    @ApplicationContext context: Context,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var exoPlayer: ExoPlayer? = null
    private var position = mutableStateOf(0L)

    init {
        viewModelScope.launch {
            getExoPlayerInstance(context)
        }
    }

    fun playVideo() {


        val url = savedStateHandle.get<String>(Constants.STREAM_URL)
        if (url.isNullOrEmpty()) return
        exoPlayer?.apply {
            playWhenReady = true
            setMediaItem(MediaItem.fromUri(url.toUri()))
            seekTo(position.value)
            prepare()

        }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer = null
        listener = null
        position.value = 0
    }


    fun getExoPlayerInstance(context: Context): ExoPlayer {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build()
            listener?.let { exoPlayer?.addListener(it) }
        }


        return exoPlayer!!
    }

    fun stopPlayer() {
        exoPlayer?.apply {
            stop()
            //removeMediaItem()
        }
    }

    fun saveState() {
        position.value = exoPlayer?.currentPosition?.coerceAtLeast(0)?:0L

    }


    private var listener: Player.Listener? = object : Player.Listener {

        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
            log("error got while playing $events")
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            log("error got while playing", error)
        }

    }
}