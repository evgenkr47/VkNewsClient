package com.example.vknewsclient.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult

class LoginViewModel: ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    init {
        _authState.value = if (VK.isLoggedIn()) AuthState.Authorized
        else AuthState.NotAuthorized
    }

    fun performAuthResult(result: VKAuthenticationResult){
        if (result is VKAuthenticationResult.Success){
            _authState.value = AuthState.Authorized
        }
        else{
            _authState.value = AuthState.NotAuthorized
        }
    }

}