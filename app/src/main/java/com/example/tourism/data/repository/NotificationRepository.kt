package com.example.tourism.data.repository

import com.example.tourism.data.local.NotificationDao
import com.example.tourism.data.model.Notification
import com.example.tourism.data.model.NotificationType
import kotlinx.coroutines.flow.Flow
import java.util.*

class NotificationRepository(private val notificationDao: NotificationDao) {

    fun getNotificationsForUser(userId: String): Flow<List<Notification>> {
        return notificationDao.getNotificationsForUser(userId)
    }

    suspend fun sendNotification(
        userId: String,
        title: String,
        message: String,
        type: NotificationType = NotificationType.BOOKING_UPDATE
    ) {
        val notification = Notification(
            id = UUID.randomUUID().toString(),
            userId = userId,
            title = title,
            message = message,
            timestamp = Date(),
            type = type
        )
        notificationDao.insertNotification(notification)
    }

    suspend fun markAsRead(notificationId: String) {
        notificationDao.markAsRead(notificationId)
    }

    suspend fun markAllAsRead(userId: String) {
        notificationDao.markAllAsRead(userId)
    }

    suspend fun deleteNotification(notification: Notification) {
        notificationDao.deleteNotification(notification)
    }
}
