package com.example.usta_app.core.data.repository

import com.example.usta_app.core.model.data.Appointment
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

interface ScheduleRepository {

    fun getSchedule(
        from: LocalDate,
        to: LocalDate,
    ): Flow<List<Appointment>>

    suspend fun refreshSchedule(
        from: LocalDate,
        to: LocalDate,
    )
}