package com.example.ustaapp.feature.schedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ustaapp.core.domain.GetScheduleUseCase
import com.example.ustaapp.core.model.data.Appointment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    getScheduleUseCase: GetScheduleUseCase,
) : ViewModel() {

    val today = LocalDate.of(2024, 3, 14)
    val uiState: StateFlow<ScheduleUiState> =
        getScheduleUseCase(today, today)
            .map(ScheduleUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ScheduleUiState.Loading,
            )
}

sealed interface ScheduleUiState {
    data object Loading : ScheduleUiState

    data class Success(
        val appointments: List<Appointment>,
    ) : ScheduleUiState

    data object Empty : ScheduleUiState
}
