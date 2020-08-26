package com.blazingtech.githubbrowser.appdeps

import android.content.Context
import com.blazingtech.githubbrowser.repository.AppRepository

interface ApplicationDeps {
    fun appRepository(): AppRepository
}

fun Context.applicationDeps(): ApplicationDeps {
    return (applicationContext as? HasApplicationDeps)?.getApplicationDeps()
        ?: throw IllegalArgumentException(" Application must Implement HasApplicationDeps")
}