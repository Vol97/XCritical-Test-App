package com.example.xcriticaltestapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xcriticaltestapp.ProjectListItem
import com.example.xcriticaltestapp.dataBase.entities.ProjectEntity
import com.example.xcriticaltestapp.repositories.ProjectsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val projectsRepository: ProjectsRepository
) : ViewModel() {

    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
    )

    private val _allProjects = projectsRepository.getAllProjects()

    private val _isInvalidLiveDataEmail = MutableLiveData<Boolean>()
    val isInvalidLiveDataEmail: LiveData<Boolean>
        get() = _isInvalidLiveDataEmail

    private val _isInvalidLiveDataPassword = MutableLiveData<Boolean>()
    val isInvalidLiveDataPassword: LiveData<Boolean>
        get() = _isInvalidLiveDataPassword

    private val _isPassedValidation = MutableLiveData<Boolean>()
    val isPassedValidation: LiveData<Boolean>
        get() = _isPassedValidation

    private val _isChangingEmailText = MutableLiveData<Boolean>()
    val isChangingEmailText: LiveData<Boolean>
        get() = _isChangingEmailText

    private val _isChangingPasswordText = MutableLiveData<Boolean>()
    val isChangingPasswordText: LiveData<Boolean>
        get() = _isChangingPasswordText

    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _isChangingEmailText.value = true
    }

    fun onPasswordTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _isChangingPasswordText.value = true
    }

    fun addProject(project: ProjectEntity) {
        projectsRepository.addProject(project)
    }

    fun removeProject(projectId: Long) {
        projectsRepository.removeProject(projectId)
    }

    fun getAllProjects() = _allProjects

    /**
     * General validation of [email] and [password] for login
     */
    fun validateLogin(email: String?, password: String?) {
        val mail = validateEmail(email)
        val pass = validatePassword(password)

        _isPassedValidation.value = !(mail!! || pass!!)
    }

    /**
     * Validates given [email] address
     *
     * @return Boolean true if email is invalid
     */
    fun validateEmail(email: String?): Boolean? {
        _isInvalidLiveDataEmail.value =
            email.isNullOrEmpty() || !EMAIL_ADDRESS_PATTERN.matcher(email).matches()
        return _isInvalidLiveDataEmail.value
    }

    /**
     * Validates given [password]
     *
     * @return Boolean true if password is invalid
     */
    private fun validatePassword(password: String?): Boolean? {
        _isInvalidLiveDataPassword.value = password.isNullOrEmpty()
        return _isInvalidLiveDataPassword.value
    }
}