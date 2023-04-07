package com.pocproject.architecture.splash.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pocproject.architecture.splash.util.SplashConstant.Companion.SPLASH_TIME_OUT
import kotlinx.coroutines.*

class SplashViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val _splashFinished = MutableLiveData<Boolean>()
    val splashFinished: LiveData<Boolean>
        get() = _splashFinished

    fun startSplashScreen() {
        coroutineScope.launch {
            delay(SPLASH_TIME_OUT.toLong()) // delay for 3 seconds
            _splashFinished.postValue(true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}