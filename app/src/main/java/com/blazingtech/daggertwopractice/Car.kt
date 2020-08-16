package com.blazingtech.daggertwopractice

import android.util.Log
import javax.inject.Inject

class Car @Inject constructor( public var engine: Engine, public var wheels: Wheels) {
    fun drive() {
        Log.d(TAG, "driving....")
    }

    companion object {
        private const val TAG = "Car"
    }

}