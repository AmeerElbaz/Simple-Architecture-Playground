package com.elbaz.sample.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel : ViewModel() {


    private val handler = CoroutineExceptionHandler { _, throwable ->

    //  Handle errors here and return suitable msg,

        onCoroutineError(throwable.message.toString())

    }
    val scope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob() + handler)



    open fun onCoroutineError(errorMsg: String) {}


    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
}