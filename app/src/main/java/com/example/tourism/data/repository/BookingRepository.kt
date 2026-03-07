package com.example.tourism.data.repository

import com.example.tourism.data.local.BookingDao
import com.example.tourism.data.model.Booking
import com.example.tourism.data.model.BookingStatus
import com.example.tourism.data.model.ServiceType
import kotlinx.coroutines.flow.Flow
import java.util.Date
import java.util.UUID

class BookingRepository(private val bookingDao: BookingDao) {

    fun getAllBookings(): Flow<List<Booking>> {
        return bookingDao.getAllBookings()
    }

    fun getBookingsForUser(userId: String): Flow<List<Booking>> {
        return bookingDao.getBookingsByUserId(userId)
    }

    suspend fun createBooking(
        userId: String,
        serviceId: String,
        serviceType: ServiceType,
        travelDate: Date,
        numberOfPeople: Int,
        totalAmount: Double,
        returnDate: Date? = null,
        specialRequirements: String? = null
    ): Booking {
        val newBooking = Booking(
            id = UUID.randomUUID().toString(),
            userId = userId,
            serviceId = serviceId,
            serviceType = serviceType,
            bookingDate = Date(),
            travelDate = travelDate,
            returnDate = returnDate,
            numberOfPeople = numberOfPeople,
            totalAmount = totalAmount,
            specialRequirements = specialRequirements
        )
        bookingDao.insertBooking(newBooking)
        return newBooking
    }

    suspend fun updateBookingStatus(bookingId: String, status: BookingStatus) {
        bookingDao.updateBookingStatus(bookingId, status)
    }
}
