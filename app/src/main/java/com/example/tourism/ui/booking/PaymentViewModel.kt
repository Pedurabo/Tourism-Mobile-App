package com.example.tourism.ui.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.data.model.Booking
import com.example.tourism.data.model.Payment
import com.example.tourism.data.model.PaymentMethod
import com.example.tourism.data.repository.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private val _paymentState = MutableStateFlow<PaymentUiState>(PaymentUiState.Idle)
    val paymentState: StateFlow<PaymentUiState> = _paymentState

    fun processPayment(booking: Booking, method: PaymentMethod) {
        viewModelScope.launch {
            _paymentState.value = PaymentUiState.Processing
            try {
                val payment = paymentRepository.processPayment(
                    bookingId = booking.id,
                    amount = booking.totalAmount,
                    method = method
                )
                _paymentState.value = PaymentUiState.Success(payment)
            } catch (e: Exception) {
                _paymentState.value = PaymentUiState.Error(e.message ?: "Payment failed")
            }
        }
    }
}

sealed class PaymentUiState {
    object Idle : PaymentUiState()
    object Processing : PaymentUiState()
    data class Success(val payment: Payment) : PaymentUiState()
    data class Error(val message: String) : PaymentUiState()
}
