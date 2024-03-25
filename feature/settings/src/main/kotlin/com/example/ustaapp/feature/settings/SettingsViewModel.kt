package com.example.ustaapp.feature.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ustaapp.core.model.data.Barber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel()

sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    data class Success(
        val userData: Barber,
    ) : SettingsUiState

    data object Empty : SettingsUiState
}
