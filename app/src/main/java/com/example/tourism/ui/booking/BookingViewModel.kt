package com.example.tourism.ui.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Booking
import com.example.tourism.data.model.ServiceType
import com.example.tourism.data.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class BookingViewModel(
    private val bookingRepository: BookingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookingUiState>(BookingUiState.Idle)
    val uiState: StateFlow<BookingUiState> = _uiState

    fun createServiceBooking(
        userId: String,
        serviceId: String,
        serviceType: ServiceType,
        totalAmount: Double,
        travelDate: Date,
        numberOfUnits: Int,
        returnDate: Date? = null,
        specialRequirements: String? = null
    ) {
        viewModelScope.launch {
            _uiState.value = BookingUiState.Loading
            try {
                val booking = bookingRepository.createBooking(
                    userId = userId,
                    serviceId = serviceId,
                    serviceType = serviceType,
                    travelDate = travelDate,
                    returnDate = returnDate,
                    numberOfPeople = numberOfUnits,
                    totalAmount = totalAmount,
                    specialRequirements = specialRequirements
                )
                _uiState.value = BookingUiState.Success(booking)
            } catch (e: Exception) {
                _uiState.value = BookingUiState.Error(e.message ?: "Failed to create booking")
            }
        }
    }
}

sealed class BookingUiState {
    object Idle : BookingUiState()
    object Loading : BookingUiState()
    data class Success(val booking: Booking) : BookingUiState()
    data class Error(val message: String) : BookingUiState()
}
