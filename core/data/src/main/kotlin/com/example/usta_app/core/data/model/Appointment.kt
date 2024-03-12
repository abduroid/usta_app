package com.example.usta_app.core.data.model

import com.example.usta_app.core.database.model.AppointmentEntity
import com.example.usta_app.core.network.model.NetworkAppointment

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