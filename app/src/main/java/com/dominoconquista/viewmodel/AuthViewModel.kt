// Archivo: app/src/main/java/com/dominoconquista/feature/viewmodel/AuthViewModel.kt
package com.dominoconquista.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authRepository.signInWithEmail(email, pass)
            result.onSuccess { uid ->
                _authState.value = AuthState.Success(uid)
            }.onFailure { error ->
                _authState.value = AuthState.Error(error.message ?: "Error desconocido")
            }
        }
    }

    // Misma estructura para register() y googleLogin(idToken)
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}