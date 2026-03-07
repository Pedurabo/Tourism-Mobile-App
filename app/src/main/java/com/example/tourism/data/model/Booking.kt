package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey
    val id: String,
    val userId: String,
    val serviceId: String, // ID of Landmark, Hotel, Car, or Flight
    val serviceType: ServiceType,
    val bookingDate: Date,
    val travelDate: Date,
    val returnDate: Date? = null,
    val numberOfPeople: Int,
    val totalAmount: Double,
    val status: BookingStatus = BookingStatus.PENDING,
    val paymentStatus: PaymentStatus = PaymentStatus.UNPAID,
    val specialRequirements: String? = null
)

enum class ServiceType {
    LANDMARK,
    HOTEL,
    CAR_HIRE,
    FLIGHT,
    AIRPORT_PICKUP
}

enum class BookingStatus {
    CANCELLED,
    COMPLETED,
    CONFIRMED,
    PENDING
}

enum class PaymentStatus {
    PAID,
    PARTIAL,
    REFUNDED,
    UNPAID
}
