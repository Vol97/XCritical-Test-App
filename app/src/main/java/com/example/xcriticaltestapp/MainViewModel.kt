package com.example.xcriticaltestapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.regex.Pattern

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
    )
    private val _isInvalidLiveDataEmail = MutableLiveData<Boolean>()
    private val _isInvalidLiveDataPassword = MutableLiveData<Boolean>()
    val isInvalidLiveDataEmail: LiveData<Boolean>
        get() = _isInvalidLiveDataEmail
    val isInvalidLiveDataPassword: LiveData<Boolean>
        get() = _isInvalidLiveDataPassword

    /**
     * Validates given [email] address
     *
     * @return MutableLiveData<Boolean> true if email is not valid
     */
    fun validateEmail(email: String?) : MutableLiveData<Boolean> {
        _isInvalidLiveDataEmail.value = email.isNullOrEmpty() || !EMAIL_ADDRESS_PATTERN.matcher(email).matches()
        return _isInvalidLiveDataEmail
    }

    /**
     * Validates given [password]
     *
     * @return MutableLiveData<Boolean> true if password is not valid
     */
    fun validatePassword(password: String?) : MutableLiveData<Boolean>{
        _isInvalidLiveDataPassword.value = password.isNullOrEmpty()
        return _isInvalidLiveDataPassword
    }
}