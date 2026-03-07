package com.example.tourism.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tourism.data.model.*

@Database(
    entities = [
        Landmark::class, 
        Booking::class, 
        User::class, 
        Notification::class,
        Hotel::class,
        Car::class,
        Flight::class
    ], 
    version = 5, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TourismDatabase : RoomDatabase() {
    abstract fun landmarkDao(): LandmarkDao
    abstract fun bookingDao(): BookingDao
    abstract fun userDao(): UserDao
    abstract fun notificationDao(): NotificationDao
    abstract fun hotelDao(): HotelDao
    abstract fun carDao(): CarDao
    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var INSTANCE: TourismDatabase? = null

        fun getDatabase(context: Context): TourismDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TourismDatabase::class.java,
                    "tourism_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
