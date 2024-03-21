package com.claudio.aularedearquitetura.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val user = "admin"
    private val pass = "1234"

    val loginResultLiveData = MutableLiveData<Boolean>()

    fun perfomLogin(username: String, password: String) {
        val isLoginSuccessful = username == user && password == pass
        loginResultLiveData.value = isLoginSuccessful
    }
}