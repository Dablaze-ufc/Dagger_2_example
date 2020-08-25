package com.blazingtech.githubbrowser.repository

import com.blazingtech.githubbrowser.githubapi.GitHubApi
import com.blazingtech.githubbrowser.githubapi.model.RepoApiModel
import com.blazingtech.githubbrowser.githubapi.model.UserApiModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
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


class AppRepositoryTest {

    private lateinit var appRepository: AppRepository

    private val fakeGitHubApi = FakeGitHubApi()

    @Before
    fun setUp() {
        appRepository = AppRepository(fakeGitHubApi)
    }

    @Test
    fun successfulQuery() {
        val topRepos = appRepository.getTopRepos()

        assertThat(topRepos.size).isEqualTo(1)
        assertThat(topRepos[0]).isEqualTo(fakeGitHubApiModel)
    }
}

private class FakeGitHubApi : GitHubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(
            fakeGitHubApiModel
        )
    }

}