package com.kodecamp.gettechie.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SignUpViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: Flow<String> get() = _name

    private val _email = MutableStateFlow("")
    val email: Flow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: Flow<String> get() = _confirmPassword

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: Flow<String> get() = _confirmPassword


    fun updateName(name: String) {
        _name.tryEmit(name)
    }

    fun updateEmail(email: String) {
        _email.tryEmit(email)
    }

    fun updatePassword(password: String) {
        _password.tryEmit(password)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.tryEmit(confirmPassword)
    }
}