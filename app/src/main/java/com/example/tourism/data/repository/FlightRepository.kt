package com.example.tourism.data.repository

import com.example.tourism.data.local.FlightDao
import com.example.tourism.data.model.Flight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class FlightRepository(private val flightDao: FlightDao) {

    fun getAllFlights(): Flow<List<Flight>> {
        return flightDao.getAllFlights().onEach { list ->
            if (list.isEmpty()) {
                seedFlights()
            }
        }
    }

    suspend fun getFlightById(id: String): Flight? {
        return flightDao.getFlightById(id)
    }

    private suspend fun seedFlights() {
        val flights = listOf(
            Flight(
                id = "f1",
                airline = "Uganda Airlines",
                flightNumber = "UR 202",
                departureCity = "Entebbe (EBB)",
                arrivalCity = "Nairobi (NBO)",
                departureTime = "08:00 AM",
                arrivalTime = "09:15 AM",
                price = 250.0
            ),
            Flight(
                id = "f2",
                airline = "AeroLink Uganda",
                flightNumber = "AL 101",
                departureCity = "Entebbe (EBB)",
                arrivalCity = "Kasese (KSE)",
                departureTime = "10:30 AM",
                arrivalTime = "11:45 AM",
                price = 180.0
            ),
            Flight(
                id = "f3",
                airline = "Uganda Airlines",
                flightNumber = "UR 340",
                departureCity = "Entebbe (EBB)",
                arrivalCity = "Johannesburg (JNB)",
                departureTime = "02:00 PM",
                arrivalTime = "06:30 PM",
                price = 450.0
            ),
            Flight(
                id = "f4",
                airline = "Eagle Air",
                flightNumber = "EA 505",
                departureCity = "Entebbe (EBB)",
                arrivalCity = "Arua (RUA)",
                departureTime = "09:00 AM",
                arrivalTime = "10:30 AM",
                price = 150.0
            )
        )
        flightDao.insertFlights(flights)
    }
}
