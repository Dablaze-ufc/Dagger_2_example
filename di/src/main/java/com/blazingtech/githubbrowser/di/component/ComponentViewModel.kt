package com.blazingtech.githubbrowser.di.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ComponentViewModel : ViewModel() {

    var component: Any? = null

    override fun onCleared() {
        component = null
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> ViewModelStoreOwner.getComponent(createComponent: () -> T) : T {
    val viewModel = ViewModelProvider(this)[ComponentViewModel::class.java]
    if (viewModel.component == null){
        viewModel.component = createComponent()
    }

    return viewModel.component as T
}