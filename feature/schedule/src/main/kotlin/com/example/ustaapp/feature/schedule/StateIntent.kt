package com.example.ustaapp.feature.schedule

import com.example.ustaapp.core.model.data.Appointment
import com.example.ustaapp.core.ui.MviIntent
import com.example.ustaapp.core.ui.MviState

sealed interface ScheduleIntent : MviIntent {
    data class ScheduleReady(val data: List<Appointment>) : MviIntent

    object ScheduleLoaded : MviIntent

    object RefreshScheduleClicked : MviIntent
}

data class ScheduleState(
    val isLoading: Boolean = false,
    val appointments: List<Appointment> = emptyList(),
) : MviState
