package com.example.ustaapp.feature.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ScheduleRoute(
    onAppointmentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: ScheduleViewModel = hiltViewModel(),
) {
    val scheduleState by viewmodel.uiState.collectAsStateWithLifecycle()

    ScheduleScreen(scheduleState = scheduleState, onAppointmentClick = onAppointmentClick)
}

@Composable
internal fun ScheduleScreen(
    scheduleState: ScheduleUiState,
    onAppointmentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (scheduleState) {
        ScheduleUiState.Loading -> {
            Text(text = "Loading")
        }

        ScheduleUiState.Empty -> Text(text = "empty appointments")
        is ScheduleUiState.Success -> {
            Column {
                Text(text = "success")
                Text(text = scheduleState.appointments.firstOrNull()?.clientFirstName ?: "empty ")
            }
        }
    }
}
