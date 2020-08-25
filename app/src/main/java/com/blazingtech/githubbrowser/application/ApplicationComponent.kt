package com.blazingtech.githubbrowser.application

import android.content.Context
import com.blazingtech.githubbrowser.githubapi.GitHubApiModule
import com.blazingtech.githubbrowser.repository.AppRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitHubApiModule::class])
interface  ApplicationComponent {

    fun appRepository(): AppRepository

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}