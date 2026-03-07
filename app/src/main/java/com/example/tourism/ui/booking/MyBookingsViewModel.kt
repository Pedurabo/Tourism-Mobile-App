package com.example.tourism.ui.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Booking
import com.example.tourism.data.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MyBookingsViewModel(
    private val bookingRepository: BookingRepository
) : ViewModel() {

    private val _bookings = MutableStateFlow<List<Booking>>(emptyList())
    val bookings: StateFlow<List<Booking>> = _bookings

    init {
        loadUserBookings()
    }

    fun loadUserBookings() {
        viewModelScope.launch {
            // Simulated user_123
            bookingRepository.getBookingsForUser("user_123").collectLatest { list ->
                _bookings.value = list
            }
        }
    }
}
