package com.blazingtech.githubbrowser.home

import com.blazingtech.githubbrowser.home.list.RepoItem

sealed class HomeViewState
object HomeLoadingState: HomeViewState()
data class HomeLoadedState(val repos:List<RepoItem>) : HomeViewState()
data class HomeLoadingErrorState(val message: String) :HomeViewState()