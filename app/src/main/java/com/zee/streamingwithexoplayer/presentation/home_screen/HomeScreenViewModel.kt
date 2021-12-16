package com.zee.streamingwithexoplayer.presentation.home_screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zee.streamingwithexoplayer.domain.model.VideoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val _searchState = mutableStateOf("")
    val searchState: State<String> = _searchState

    fun onSearchChange(search: String) {
        _searchState.value = search
    }

    private val _homeScreenState = mutableStateOf(listOf<VideoModel>())
    val homeScreenState: State<List<VideoModel>> = _homeScreenState


    init {

        viewModelScope.launch {

            val ls = tempVideos()
            _homeScreenState.value = ls

        }
    }


    private fun tempVideos(): List<VideoModel> {
        return listOf(
            VideoModel(
                id = 1L,
                description = "Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\n\nLicensed under the Creative Commons Attribution license\nhttp://www.bigbuckbunny.org",
                sources = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",

                //
                subtitle = "By Blender Foundation",
                thumb = "https://i.ytimg.com/vi/aqz-KE-bpKQ/maxresdefault.jpg",
                title = "Big Buck Bunny"
            ),

            VideoModel(
                id = 2L,
                description = "The first Blender Open Movie from 2006",
                sources = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                subtitle = "By Blender Foundation",
                thumb = "https://i.ytimg.com/vi/F4Kk0BgJVpg/hqdefault.jpg",
                title = "Elephant Dream"
            ),

            VideoModel(
                id = 3,
                description =
                "HBO GO now works with Chromecast -- the easiest way to enjoy online video on your TV. For when you want to settle into your Iron Throne to watch the latest episodes. For $35.\nLearn how to use Chromecast with HBO GO and more at google.com/chromecast.",
                sources =
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                subtitle = "By Google",
                thumb = "https://www.businessinsider.in/photo/75741787/Master.jpg",
                title = "For Bigger Blazes"
            ),
            VideoModel(
                id =4,
                description =
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when Batman's escapes aren't quite big enough. For $35. Learn how to use Chromecast with Google Play Movies and more at google.com/chromecast.",
                sources =
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                subtitle = "By Google",
                thumb = "https://i.ytimg.com/vi/6DTnjC6F3uw/maxresdefault.jpg",
                title = "For Bigger Escape"
            ),

            )
    }



}