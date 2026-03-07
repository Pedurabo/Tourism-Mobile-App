package com.example.tourism.data.repository

import com.example.tourism.data.local.CarDao
import com.example.tourism.data.model.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class CarRepository(private val carDao: CarDao) {

    fun getAllCars(): Flow<List<Car>> {
        return carDao.getAllCars().onEach { list ->
            if (list.isEmpty()) {
                seedCars()
            }
        }
    }

    suspend fun getCarById(id: String): Car? {
        return carDao.getCarById(id)
    }

    private suspend fun seedCars() {
        val ugandaCars = listOf(
            Car(
                id = "c1",
                model = "Toyota Land Cruiser V8",
                type = "SUV",
                transmission = "Automatic",
                pricePerDay = 150.0,
                imageUrl = "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?auto=format&fit=crop&w=800&q=80",
                seatingCapacity = 7,
                location = "Kampala"
            ),
            Car(
                id = "c2",
                model = "Toyota RAV4",
                type = "Compact SUV",
                transmission = "Automatic",
                pricePerDay = 60.0,
                imageUrl = "https://images.unsplash.com/photo-1511919884226-fd3cad34687c?auto=format&fit=crop&w=800&q=80",
                seatingCapacity = 5,
                location = "Entebbe"
            ),
            Car(
                id = "c3",
                model = "Safari Van (Drone)",
                type = "Van",
                transmission = "Manual",
                pricePerDay = 100.0,
                imageUrl = "https://images.unsplash.com/photo-1523983388277-336a66bf9bcd?auto=format&fit=crop&w=800&q=80",
                seatingCapacity = 9,
                location = "Kampala"
            ),
            Car(
                id = "c4",
                model = "Mercedes Benz E-Class",
                type = "Sedan",
                transmission = "Automatic",
                pricePerDay = 120.0,
                imageUrl = "https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=800&q=80",
                seatingCapacity = 5,
                location = "Kampala"
            )
        )
        carDao.insertCars(ugandaCars)
    }
}
