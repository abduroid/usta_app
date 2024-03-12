package com.example.ustaapp.core.data.repository

import com.example.ustaapp.core.model.data.Appointment
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
