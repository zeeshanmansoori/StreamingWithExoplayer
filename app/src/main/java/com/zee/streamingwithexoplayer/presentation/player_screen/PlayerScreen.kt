package com.zee.streamingwithexoplayer.presentation.player_screen

import android.content.pm.ActivityInfo
import android.view.LayoutInflater
import android.view.Window
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.zee.streamingwithexoplayer.MainActivity

import com.zee.streamingwithexoplayer.utils.log

@Composable
fun PlayerScreen(navController: NavController, viewModel: PlayerViewModel = hiltViewModel()) {

//    val lifecycle = LocalLifecycleOwner.current.lifecycle
//    val context = LocalContext.current
//
//    val playerView = remember {
//
//        val playerViewBinding = VideoPlayerBinding
//            .inflate(LayoutInflater.from(context), null, false)
//
//        lifecycle.addObserver(object : LifecycleEventObserver {
//            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//                if (Lifecycle.Event.ON_START == event)
//                    playerViewBinding.playerView.apply {
//                        onResume()
//                        player?.play()
//                    }
//                else if (Lifecycle.Event.ON_STOP == event)
//                    playerViewBinding.playerView.apply {
//                        viewModel.saveState()
//                        onPause()
//                        player?.pause()
//                    }
//
//            }
//
//        })
//        playerViewBinding.playerView
//    }
//
//    AndroidView(factory =
//    {
//
//
//        playerView.apply {
//            kotlin.runCatching {
//                (context as MainActivity).requestedOrientation =
//                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//            }.onFailure {
//                log("exception $it")
//            }
//
//            player = viewModel.getExoPlayerInstance(context)
//            //useController = false
//            viewModel.playVideo()
//        }
//    }
//    )
//
//
//    BackHandler(true) {
//        viewModel.stopPlayer()
//        kotlin.runCatching {
//            (context as MainActivity).requestedOrientation =
//                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        }.onFailure {
//            log("exception $it")
//        }
//
//        navController.navigateUp()
//
//        log("back_stack ${navController.currentBackStackEntry?.destination}")
//    }



}


private fun hideSystemBars(window:Window) {
    val windowInsetsController =
        ViewCompat.getWindowInsetsController(window.decorView) ?: return
    // Configure the behavior of the hidden system bars
    windowInsetsController.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    // Hide both the status bar and the navigation bar
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
}

