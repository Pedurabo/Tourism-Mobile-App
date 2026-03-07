package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landmarks")
data class Landmark(
    @PrimaryKey
    val id: String,
    val name: String,
    val location: String,
    val description: String,
    val category: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val tourPrice: Double
)
