package com.example.tourism.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notifications")
data class Notification(
    @PrimaryKey
    val id: String,
    val userId: String,
    val title: String,
    val message: String,
    val timestamp: Date,
    val isRead: Boolean = false,
    val type: NotificationType = NotificationType.BOOKING_UPDATE
)

enum class NotificationType {
    BOOKING_UPDATE,
    PROMOTION,
    SYSTEM_ALERT
}
