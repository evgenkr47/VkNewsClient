package com.example.vknewsclient.presentation.login

sealed class AuthState{
    object Authorized: AuthState()
    object NotAuthorized: AuthState()
    object Initial: AuthState()
}
