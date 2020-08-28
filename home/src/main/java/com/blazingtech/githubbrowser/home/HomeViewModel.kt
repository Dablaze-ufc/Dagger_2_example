package com.blazingtech.githubbrowser.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blazingtech.githubbrowser.di.scope.ScreenScope
import com.blazingtech.githubbrowser.home.list.RepoItem
import com.blazingtech.githubbrowser.repository.AppRepository
import javax.inject.Inject

@ScreenScope
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>(HomeLoadingState)

    val viewStateUpdate: LiveData<HomeViewState> = _viewState

    init {
        val topRepos = appRepository.getTopRepos()
        _viewState.value = HomeLoadedState(topRepos.map {

            RepoItem(
               name =  it.name,
               description =  it.description,
               forkCount =  it.forksCount,
               starsCount =  it.stargazersCount
            )
        })
    }
}