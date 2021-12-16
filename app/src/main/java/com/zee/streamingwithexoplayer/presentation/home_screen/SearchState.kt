package com.zee.offlinemusicplayer.presentation.home_screen

data class SearchState(val searchStarted: Boolean = false, val searchResult: List<String>)

sealed class SearchWorkingState {
    object SearchStarted : SearchWorkingState()
    object SearchFinished : SearchWorkingState()
}

sealed class SearchResultState {
    object SearchResultEmpty : SearchResultState()
    data class SearchResult(val result: List<String>) : SearchResultState()
}