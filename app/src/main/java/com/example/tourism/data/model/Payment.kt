package com.example.tourism.data.model

import java.util.Date

data class Payment(
    val id: String,
    val bookingId: String,
    val amount: Double,
    val paymentMethod: PaymentMethod,
    val status: PaymentStatus,
    val transactionReference: String,
    val createdAt: Date = Date()
)

enum class PaymentMethod {
    MOBILE_MONEY_MTN,
    MOBILE_MONEY_AIRTEL,
    CREDIT_CARD,
    CASH_AT_OFFICE
}
