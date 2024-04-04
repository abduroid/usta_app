package com.example.ustaapp.core.domain

import com.example.ustaapp.core.data.repository.ScheduleRepository
import org.threeten.bp.LocalDate
import javax.inject.Inject

class RefreshScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    suspend operator fun invoke(from: LocalDate, to: LocalDate) {
        scheduleRepository.refreshSchedule(from, to)
    }
}
