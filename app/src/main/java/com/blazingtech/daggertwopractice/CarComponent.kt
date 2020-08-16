package com.blazingtech.daggertwopractice

import dagger.Component

@Component
interface CarComponent {
    val car: Car?

    fun inject(mainActivity: MainActivity)
}