package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey
    val id: String,
    val model: String,
    val type: String, // Sedan, SUV, etc.
    val transmission: String, // Manual, Automatic
    val pricePerDay: Double,
    val imageUrl: String,
    val seatingCapacity: Int,
    val location: String
)
