package com.blazingtech.githubbrowser.application

import android.content.Context
import com.blazingtech.githubbrowser.appdeps.ApplicationDeps
import com.blazingtech.githubbrowser.githubapi.GitHubApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GitHubApiModule::class])
interface  ApplicationComponent: ApplicationDeps {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}