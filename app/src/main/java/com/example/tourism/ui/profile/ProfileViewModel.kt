package com.example.tourism.ui.profile

import androidx.lifecycle.ViewModel
import com.example.tourism.data.model.User
import com.example.tourism.data.model.UserType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _user = MutableStateFlow(
        User(
            id = "guest",
            fullName = "Guest User",
            email = "guest@example.com",
            phoneNumber = "000",
            userType = UserType.TOURIST
        )
    )
    val user: StateFlow<User> = _user

    fun updateFromUser(user: User) {
        _user.value = user
    }

    fun updateProfile(fullName: String, email: String, phoneNumber: String) {
        _user.value = _user.value.copy(
            fullName = fullName,
            email = email,
            phoneNumber = phoneNumber
        )
    }

    // Still useful for quick testing in dev
    fun switchAccount(type: UserType) {
        _user.value = when(type) {
            UserType.ADMIN -> User(
                id = "admin_001",
                fullName = "Admin User",
                email = "admin@pearlguide.ug",
                phoneNumber = "+256 788 123 456",
                userType = UserType.ADMIN
            )
            UserType.TOURIST -> User(
                id = "user_123",
                fullName = "John Doe",
                email = "john.doe@example.com",
                phoneNumber = "+256 700 000 000",
                userType = UserType.TOURIST
            )
            UserType.TOUR_OPERATOR -> User(
                id = "op_99",
                fullName = "Operator X",
                email = "ops@tours.com",
                phoneNumber = "+256 755 987 654",
                userType = UserType.TOUR_OPERATOR
            )
        }
    }
}
