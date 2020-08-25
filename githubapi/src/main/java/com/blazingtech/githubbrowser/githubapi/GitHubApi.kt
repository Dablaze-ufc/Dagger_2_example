package com.blazingtech.githubbrowser.githubapi

import com.blazingtech.githubbrowser.githubapi.model.RepoApiModel
import com.blazingtech.githubbrowser.githubapi.model.UserApiModel
import javax.inject.Inject
import javax.inject.Singleton

interface GitHubApi {
    fun getTopRepositories(): List<RepoApiModel>
}

@Singleton
class MockGitHubApi @Inject constructor() : GitHubApi{
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(
            RepoApiModel(
                1L,"Mock Repo",
                "Mock description",
                UserApiModel(1L, "dagger"),
                1,
                1,
                "",
                "1/1/2020",
                "1/1/2020"
            )
        )
    }

}