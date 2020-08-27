package com.blazingtech.githubbrowser.home

import com.blazingtech.githubbrowser.appdeps.ApplicationDeps
import com.blazingtech.githubbrowser.appdeps.applicationDeps
import com.blazingtech.githubbrowser.di.component.getComponent
import com.blazingtech.githubbrowser.di.scope.ScreenScope
import dagger.Component

@ScreenScope
@Component(dependencies = [ApplicationDeps::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(applicationDeps: ApplicationDeps): HomeComponent
    }


}

fun HomeFragment.inject() {
    getComponent {
        DaggerHomeComponent.factory()
            .create(requireContext().applicationDeps())
    }.inject(this)
}