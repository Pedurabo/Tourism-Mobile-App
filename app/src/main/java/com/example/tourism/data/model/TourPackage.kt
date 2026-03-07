package com.example.tourism.data.model

data class TourPackage(
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val durationDays: Int,
    val landmarkIds: List<String>,
    val inclusions: List<String>,
    val imageUrl: String
)
