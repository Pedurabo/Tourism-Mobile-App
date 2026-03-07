package com.example.tourism.data.local

import androidx.room.*
import com.example.tourism.data.model.Landmark
import kotlinx.coroutines.flow.Flow

@Dao
interface LandmarkDao {
    @Query("SELECT * FROM landmarks")
    fun getAllLandmarks(): Flow<List<Landmark>>

    @Query("SELECT * FROM landmarks WHERE id = :id")
    suspend fun getLandmarkById(id: String): Landmark?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLandmarks(landmarks: List<Landmark>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLandmark(landmark: Landmark)

    @Delete
    suspend fun deleteLandmark(landmark: Landmark)
}
