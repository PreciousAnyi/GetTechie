package com.kodecamp.gettechie.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ForgotPasswordViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: Flow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: Flow<String> get() = _confirmPassword

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: Flow<String> get() = _confirmPassword

    private val _otp1 = MutableStateFlow("")
    val otp1: Flow<String> get() = _otp1

    private val _otp2 = MutableStateFlow("")
    val otp2: Flow<String> get() = _otp2

    private val _otp3 = MutableStateFlow("")
    val otp3: Flow<String> get() = _otp3

    private val _otp4 = MutableStateFlow("")
    val otp4: Flow<String> get() = _otp4


    fun updateEmail(email: String) {
        _email.tryEmit(email)
    }

    fun updatePassword(password: String) {
        _password.tryEmit(password)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.tryEmit(confirmPassword)
    }

    fun updateOtp1(otp1: String) {
        _otp1.tryEmit(otp1)
    }

    fun updateOtp2(otp2: String) {
        _otp2.tryEmit(otp2)
    }

    fun updateOtp3(otp3: String) {
        _otp3.tryEmit(otp3)
    }

    fun updateOtp4(otp4: String) {
        _otp4.tryEmit(otp4)
    }

}