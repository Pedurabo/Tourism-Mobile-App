package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey
    val id: String,
    val name: String,
    val location: String,
    val description: String,
    val pricePerNight: Double,
    val imageUrl: String,
    val rating: Double,
    val amenities: List<String> = emptyList()
)
