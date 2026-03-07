package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey
    val id: String,
    val airline: String,
    val flightNumber: String,
    val departureCity: String,
    val arrivalCity: String,
    val departureTime: String,
    val arrivalTime: String,
    val price: Double,
    val imageUrl: String? = null
)
