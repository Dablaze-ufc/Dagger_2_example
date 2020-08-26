package com.blazingtech.githubbrowser.application

import android.app.Application
import com.blazingtech.githubbrowser.appdeps.ApplicationDeps
import com.blazingtech.githubbrowser.appdeps.HasApplicationDeps

class GitHubBrowserApp : Application(), HasApplicationDeps {
    private val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getApplicationDeps(): ApplicationDeps {
        return applicationComponent
    }
}