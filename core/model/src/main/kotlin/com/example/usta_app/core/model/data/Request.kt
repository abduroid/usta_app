package com.example.usta_app.core.model.data

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

data class Request(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val createdTime: LocalDateTime,
    val selectedDate: LocalDate,
    val startTime: Float,
    val endTime: Float,
    val relatedRequestId: String = "",
    val relatedAppointmentId: String = "",
    val isRejected: Boolean,
)
