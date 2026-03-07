package com.example.tourism.data.local

import androidx.room.*
import com.example.tourism.data.model.Hotel
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {
    @Query("SELECT * FROM hotels")
    fun getAllHotels(): Flow<List<Hotel>>

    @Query("SELECT * FROM hotels WHERE id = :hotelId")
    suspend fun getHotelById(hotelId: String): Hotel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotels(hotels: List<Hotel>)

    @Delete
    suspend fun deleteHotel(hotel: Hotel)
}
