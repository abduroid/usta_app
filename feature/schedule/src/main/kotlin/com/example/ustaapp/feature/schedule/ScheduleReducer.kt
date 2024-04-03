package com.example.ustaapp.feature.schedule

import com.example.ustaapp.core.ui.GenericIntent
import com.example.ustaapp.core.ui.MviIntent
import com.example.ustaapp.core.ui.Reducer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleReducer @Inject constructor() : Reducer<ScheduleState> {
    override fun reduce(state: ScheduleState, intent: MviIntent) = when (intent) {
        GenericIntent.Init -> state.copy(isLoading = true)
        is ScheduleIntent.ScheduleLoaded -> state.copy(isLoading = false)
        is ScheduleIntent.ScheduleReady -> state.copy(appointments = intent.data)
        is GenericIntent.Failure -> state.copy(isLoading = false)
        else -> state
    }
}
