package com.example.tourism.data.repository

import com.example.tourism.data.model.Payment
import com.example.tourism.data.model.PaymentMethod
import com.example.tourism.data.model.PaymentStatus
import kotlinx.coroutines.delay
import java.util.UUID

class PaymentRepository {
    private val payments = mutableListOf<Payment>()

    suspend fun processPayment(
        bookingId: String,
        amount: Double,
        method: PaymentMethod
    ): Payment {
        // Simulate network delay for payment processing
        delay(2000)
        
        val payment = Payment(
            id = UUID.randomUUID().toString(),
            bookingId = bookingId,
            amount = amount,
            paymentMethod = method,
            status = PaymentStatus.PAID, // Simulate successful payment
            transactionReference = "TXN-${UUID.randomUUID().toString().take(8).uppercase()}"
        )
        payments.add(payment)
        return payment
    }

    fun getPaymentsByBooking(bookingId: String): List<Payment> {
        return payments.filter { it.bookingId == bookingId }
    }
}
