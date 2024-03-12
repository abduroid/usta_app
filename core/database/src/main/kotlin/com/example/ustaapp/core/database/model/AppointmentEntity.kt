package com.example.ustaapp.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ustaapp.core.model.data.Appointment

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey
    val id: String,
    val clientId: String,
    val clientFirstName: String,
    val clientLastName: String,
//    val createdDateTime: LocalDateTime,
//    val selectedDate: LocalDate,
    val startTime: Float,
    val endTime: Float,
)

fun AppointmentEntity.asExternalModel() = Appointment(
    id = id,
    clientId = clientId,
    clientFirstName = clientFirstName,
    clientLastName = clientLastName,
//    createdDateTime = createdDateTime,
//    selectedDate = selectedDate,
    startTime = startTime,
    endTime = endTime,
)
