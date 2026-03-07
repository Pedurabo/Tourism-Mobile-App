package com.example.tourism.data.repository

import com.example.tourism.data.local.LandmarkDao
import com.example.tourism.data.model.Landmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach

class LandmarkRepository(private val landmarkDao: LandmarkDao) {

    fun getAllLandmarks(): Flow<List<Landmark>> {
        return landmarkDao.getAllLandmarks().onEach { list ->
            if (list.isEmpty()) {
                seedDatabase()
            }
        }
    }

    suspend fun getMajorLandmarks(): List<Landmark> {
        return landmarkDao.getAllLandmarks().first()
    }

    suspend fun addLandmark(landmark: Landmark) {
        landmarkDao.insertLandmark(landmark)
    }

    suspend fun deleteLandmark(landmark: Landmark) {
        landmarkDao.deleteLandmark(landmark)
    }

    private suspend fun seedDatabase() {
        val initialLandmarks = listOf(
            Landmark(
                id = "1",
                name = "Murchison Falls National Park",
                location = "Masindi",
                description = "The world's most powerful waterfall where the Nile forces its way through a narrow gorge.",
                category = "National Park",
                imageUrl = "https://images.unsplash.com/photo-1523805009345-7448845a9e53?auto=format&fit=crop&w=800&q=80",
                latitude = 2.2472,
                longitude = 31.7825,
                tourPrice = 40.0
            ),
            Landmark(
                id = "2",
                name = "Bwindi Impenetrable Park",
                location = "Kanungu",
                description = "Home to roughly half of the world's remaining mountain gorillas.",
                category = "National Park",
                imageUrl = "https://images.unsplash.com/photo-1516426122078-c23e76319801?auto=format&fit=crop&w=800&q=80",
                latitude = -1.0475,
                longitude = 29.7025,
                tourPrice = 700.0
            ),
            Landmark(
                id = "3",
                name = "Queen Elizabeth Park",
                location = "Kasese",
                description = "Famous for tree-climbing lions and the Kazinga Channel boat cruise.",
                category = "National Park",
                imageUrl = "https://images.unsplash.com/photo-1547471080-7cc2caa01a7e?auto=format&fit=crop&w=800&q=80",
                latitude = -0.1911,
                longitude = 29.9322,
                tourPrice = 40.0
            ),
            Landmark(
                id = "5",
                name = "Rwenzori Mountains",
                location = "Kasese",
                description = "The 'Mountains of the Moon' offer snow-capped peaks in the heart of Africa.",
                category = "Mountain",
                imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=800&q=80",
                latitude = 0.3833,
                longitude = 29.8667,
                tourPrice = 50.0
            ),
            Landmark(
                id = "6",
                name = "Lake Bunyonyi",
                location = "Kabale",
                description = "One of the most beautiful and safest lakes for swimming in Africa.",
                category = "Lake",
                imageUrl = "https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=800&q=80",
                latitude = -1.2833,
                longitude = 29.9167,
                tourPrice = 20.0
            ),
            Landmark(
                id = "7",
                name = "Kidepo Valley",
                location = "Kaabong",
                description = "Uganda's most isolated and scenic park with rugged savannah.",
                category = "National Park",
                imageUrl = "https://images.unsplash.com/photo-1516026672322-bc52d61a55d5?auto=format&fit=crop&w=800&q=80",
                latitude = 3.9000,
                longitude = 33.7833,
                tourPrice = 40.0
            ),
            Landmark(
                id = "11",
                name = "Kibale Forest",
                location = "Fort Portal",
                description = "The best place in East Africa for chimpanzee trekking.",
                category = "National Park",
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?auto=format&fit=crop&w=800&q=80",
                latitude = 0.4833,
                longitude = 30.4000,
                tourPrice = 200.0
            ),
            Landmark(
                id = "19",
                name = "Itanda Falls",
                location = "Jinja",
                description = "A hidden gem on the Nile with powerful white-water rapids.",
                category = "Waterfall",
                imageUrl = "https://images.unsplash.com/photo-1542332213-9b5a5a3fab35?auto=format&fit=crop&w=800&q=80",
                latitude = 0.5167,
                longitude = 33.1500,
                tourPrice = 5.0
            )
        )
        landmarkDao.insertLandmarks(initialLandmarks)
    }
}
