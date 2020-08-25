package com.blazingtech.githubbrowser.application

import android.app.Application

class GitHubBrowserApp : Application() {
    private val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}