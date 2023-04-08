package com.pocproject.architecture.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocproject.architecture.login.di.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading


    fun loginUser(email: String,password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val success = userRepository.loginUser(email,password)
            _loginSuccess.postValue(success)
            _isLoading.value = false
        }
    }
}