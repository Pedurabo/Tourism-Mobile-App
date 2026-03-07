package com.example.tourism.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.User
import com.example.tourism.data.model.UserType
import com.example.tourism.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.LoggedOut)
    val authState: StateFlow<AuthState> = _authState

    private val _uiEvent = MutableSharedFlow<AuthUiEvent>()
    val uiEvent: SharedFlow<AuthUiEvent> = _uiEvent

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val user = userRepository.login(email, password)
            if (user != null) {
                _authState.value = AuthState.LoggedIn(user)
                _uiEvent.emit(AuthUiEvent.NavigateToHome)
            } else {
                _authState.value = AuthState.LoggedOut
                _uiEvent.emit(AuthUiEvent.ShowError("Invalid email or password"))
            }
        }
    }

    fun loginWithGoogle() {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            // Simulated Google Auth delay
            delay(2000)
            
            val googleUser = User(
                id = UUID.randomUUID().toString(),
                fullName = "Google User",
                email = "user@gmail.com",
                phoneNumber = "+256 000 000 000",
                password = "", // No password for OAuth users
                userType = UserType.TOURIST
            )
            
            userRepository.register(googleUser)
            _authState.value = AuthState.LoggedIn(googleUser)
            _uiEvent.emit(AuthUiEvent.NavigateToHome)
        }
    }

    fun register(fullName: String, email: String, phone: String, password: String, userType: UserType) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            
            val newUser = User(
                id = UUID.randomUUID().toString(),
                fullName = fullName,
                email = email,
                phoneNumber = phone,
                password = password,
                userType = userType
            )
            userRepository.register(newUser)
            _authState.value = AuthState.LoggedIn(newUser)
            _uiEvent.emit(AuthUiEvent.NavigateToHome)
        }
    }

    fun logout() {
        _authState.value = AuthState.LoggedOut
    }
}

sealed class AuthState {
    object LoggedOut : AuthState()
    object Loading : AuthState()
    data class LoggedIn(val user: User) : AuthState()
}

sealed class AuthUiEvent {
    data class ShowError(val message: String) : AuthUiEvent()
    object NavigateToHome : AuthUiEvent()
}
