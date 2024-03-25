package com.example.ustaapp.feature.requests

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ustaapp.core.model.data.Request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
//    val uiState: StateFlow<RequestsUiState> =
}

sealed interface RequestsUiState {
    data object Loading : RequestsUiState

    data class Success(
        val requests: List<Request>,
    ) : RequestsUiState

    data object Empty : RequestsUiState
}
