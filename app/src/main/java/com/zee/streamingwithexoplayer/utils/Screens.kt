package com.zee.streamingwithexoplayer.utils

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object PlayerScreen : Screen("player_screen")
}