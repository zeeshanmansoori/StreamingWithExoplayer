package com.zee.streamingwithexoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zee.streamingwithexoplayer.presentation.home_screen.HomeScreen
import com.zee.streamingwithexoplayer.presentation.player_screen.PlayerScreen
import com.zee.streamingwithexoplayer.ui.theme.StreamingWithExoplayerTheme
import com.zee.streamingwithexoplayer.utils.Constants
import com.zee.streamingwithexoplayer.utils.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamingWithExoplayerTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {


                    composable(Screen.HomeScreen.route) {
                        HomeScreen(navController)
                    }

                    composable(
                        Screen.PlayerScreen.route + "?${Constants.STREAM_URL}={${Constants.STREAM_URL}}",
                        arguments = listOf(

                            navArgument(Constants.STREAM_URL) {
                                type = NavType.StringType
                                defaultValue = ""
                            }

                        )

                    ) {

                        PlayerScreen(navController)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StreamingWithExoplayerTheme {
        HomeScreen(navController = rememberNavController())
    }
}