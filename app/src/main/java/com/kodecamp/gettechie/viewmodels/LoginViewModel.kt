package com.kodecamp.gettechie.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: Flow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: Flow<String> get() = _password

    fun updateEmail(email: String) {
        _email.tryEmit(email)
    }

    fun updatePassword(password: String) {
        _password.tryEmit(password)
    }
}