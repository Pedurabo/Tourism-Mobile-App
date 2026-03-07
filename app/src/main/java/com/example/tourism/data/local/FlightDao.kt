package com.example.tourism.data.local

import androidx.room.*
import com.example.tourism.data.model.Flight
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query("SELECT * FROM flights")
    fun getAllFlights(): Flow<List<Flight>>

    @Query("SELECT * FROM flights WHERE id = :flightId")
    suspend fun getFlightById(flightId: String): Flight?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlights(flights: List<Flight>)

    @Delete
    suspend fun deleteFlight(flight: Flight)
}
