package com.zee.streamingwithexoplayer.presentation.home_screen

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zee.streamingwithexoplayer.PlayerActivity
import com.zee.streamingwithexoplayer.presentation.home_screen.component.VideoCard
import com.zee.streamingwithexoplayer.utils.Constants

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {


    val list = viewModel.homeScreenState.value
    val scaffoldState = rememberScaffoldState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {

            }
        }
    ) {


        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            cells = GridCells.Fixed(2),
            state = lazyListState
        ) {

            itemsIndexed(list) { index, videoModel ->

                val even = index % 2 == 0

                VideoCard(
                    modifier = Modifier.padding(
                        end = if (even) 6.dp else 0.dp,
                        start = if (even) 0.dp else 6.dp,
                        bottom = 8.dp,
                        top = 8.dp
                    ),
                    title = videoModel.title,
                    subTitle = videoModel.subtitle,
                    thumb = videoModel.thumb,
                    onCardClick = {

                        //navController.navigate(Screen.PlayerScreen.route + "?${Constants.STREAM_URL}=${videoModel.sources}")
                        context.startActivity(
                            Intent(
                                context,
                                PlayerActivity::class.java
                            ).apply { putExtra(Constants.STREAM_URL, videoModel.sources) })

                    }
                )
            }

        }


    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController())
}

