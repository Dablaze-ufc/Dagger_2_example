package com.blazingtech.githubbrowser.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blazingtech.githubbrowser.githubapi.GitHubApi
import com.blazingtech.githubbrowser.githubapi.model.RepoApiModel
import com.blazingtech.githubbrowser.githubapi.model.UserApiModel
import com.blazingtech.githubbrowser.home.list.RepoItem
import com.blazingtech.githubbrowser.repository.AppRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule

import org.junit.Test

private val fakeGitHubApiModel = RepoApiModel(

    1L,
    "Mock Repo",
    "Mock description",
    UserApiModel(1L, "dagger"),
    1,
    1,
    "",
    "1/1/2020",
    "1/1/2020"
)

class HomeViewModelTest {

    @get:Rule val taskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewStateValue : MutableList<HomeViewState>

    @Before
    fun setUp() {
        val appRepository = AppRepository(FakeGitHubApi())
        viewStateValue = mutableListOf()

        viewModel = HomeViewModel(appRepository)
        viewModel.viewStateUpdate.observeForever{viewStateValue.add(it)}
    }

    @Test
    fun `loaded state contains repo models` (){
        assertThat(viewStateValue.size).isEqualTo(1)
        val expectedState = HomeLoadedState(
            repos = listOf(
                RepoItem(
                    name = fakeGitHubApiModel.name,
                    description = fakeGitHubApiModel.description,
                    starsCount = fakeGitHubApiModel.stargazersCount,
                    forkCount = fakeGitHubApiModel.forksCount
                )
            )
        )

        assertThat(viewStateValue[0]).isEqualTo(expectedState)
    }
}

private class FakeGitHubApi : GitHubApi{
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(fakeGitHubApiModel)
    }

}