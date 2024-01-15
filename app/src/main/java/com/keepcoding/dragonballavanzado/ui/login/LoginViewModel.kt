package com.keepcoding.dragonballavanzado.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dragonballavanzado.data.Repository
import com.keepcoding.dragonballavanzado.models.commons.ErrorState
import com.keepcoding.dragonballavanzado.models.commons.Idle
import com.keepcoding.dragonballavanzado.models.commons.ResponseOK
import com.keepcoding.dragonballavanzado.models.commons.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _usernameFormat = MutableStateFlow(false)
    private val _passwordFormat = MutableStateFlow(false)
    private val _state = MutableStateFlow<State>(Idle())

    val usernameFormat: StateFlow<Boolean> = _usernameFormat
    val passwordFormat: StateFlow<Boolean> = _passwordFormat
    val state: StateFlow<State> = _state

    fun checkUsernameFormat(text: String) {
        _usernameFormat.value = text.length > 3
    }

    fun checkPasswordFormat(text: String) {
        _passwordFormat.value = text.length > 3
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!_usernameFormat.value || !_passwordFormat.value) return@launch

            val response = repository.login(username, password)

            if (response.isSuccessful) {
                _state.value = ResponseOK()

                response.body()?.let {
                    repository.setToken(it)
                }
            } else {
                _state.value = ErrorState(response.message())
            }
        }
    }

}