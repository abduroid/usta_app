package com.example.ustaapp.core.domain

import com.example.ustaapp.core.data.repository.ScheduleRepository
import com.example.ustaapp.core.model.data.Appointment
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {

    /**
     * Returns a list of appointments
     *
     * @param from - The starting date of the range.
     * Appointments on or after this date will be included.
     *
     * @param to - The ending date of the range.
     * Appointments on or before this date will be included.
     */
    operator fun invoke(from: LocalDate, to: LocalDate): Flow<List<Appointment>> =
        scheduleRepository.getScheduleStream(from, to)
}
