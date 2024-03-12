package com.example.ustaapp.core.data.model

import com.example.ustaapp.core.database.model.AppointmentEntity
import com.example.ustaapp.core.network.model.NetworkAppointment

fun NetworkAppointment.asEntity() = AppointmentEntity(
    id = id,
    clientId = clientId,
    clientFirstName = clientFirstName,
    clientLastName = clientLastName,
//    createdDateTime = createdDateTime,
//    selectedDate = selectedDate,
    startTime = startTime,
    endTime = endTime,
)
