package com.example.tourism.data.repository

import com.example.tourism.data.local.HotelDao
import com.example.tourism.data.model.Hotel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach

class HotelRepository(private val hotelDao: HotelDao) {

    fun getAllHotels(): Flow<List<Hotel>> {
        return hotelDao.getAllHotels().onEach { list ->
            if (list.isEmpty()) {
                seedHotels()
            }
        }
    }

    suspend fun getHotelById(id: String): Hotel? {
        return hotelDao.getHotelById(id)
    }

    private suspend fun seedHotels() {
        val ugandaHotels = listOf(
            Hotel(
                id = "h1",
                name = "Serena Hotel Kampala",
                location = "Kampala",
                description = "Five-star luxury in the heart of the city.",
                pricePerNight = 250.0,
                imageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=800&q=80",
                rating = 4.8,
                amenities = listOf("Pool", "Spa", "Gym", "WiFi")
            ),
            Hotel(
                id = "h2",
                name = "Chobe Safari Lodge",
                location = "Murchison Falls",
                description = "Luxury safari experience on the banks of the Nile.",
                pricePerNight = 350.0,
                imageUrl = "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?auto=format&fit=crop&w=800&q=80",
                rating = 4.9,
                amenities = listOf("Safari Tours", "Nile View", "Restaurant")
            ),
            Hotel(
                id = "h3",
                name = "Protea Hotel Entebbe",
                location = "Entebbe",
                description = "Perfect stay near the airport with a view of Lake Victoria.",
                pricePerNight = 180.0,
                imageUrl = "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?auto=format&fit=crop&w=800&q=80",
                rating = 4.5,
                amenities = listOf("Airport Shuttle", "WiFi", "Bar")
            ),
            Hotel(
                id = "h4",
                name = "BirdNest Resort",
                location = "Lake Bunyonyi",
                description = "Cozy resort overlooking the stunning Lake Bunyonyi.",
                pricePerNight = 120.0,
                imageUrl = "https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?auto=format&fit=crop&w=800&q=80",
                rating = 4.7,
                amenities = listOf("Boat Trips", "Canoeing", "Lake View")
            )
        )
        hotelDao.insertHotels(ugandaHotels)
    }
}
