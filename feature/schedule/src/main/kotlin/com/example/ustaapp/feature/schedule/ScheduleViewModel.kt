package com.example.ustaapp.feature.schedule

import androidx.lifecycle.SavedStateHandle
import com.example.ustaapp.core.ui.BaseViewModel
import com.example.ustaapp.core.ui.MviStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    store: MviStore<ScheduleState>,
) : BaseViewModel<ScheduleState>(savedStateHandle, store)
