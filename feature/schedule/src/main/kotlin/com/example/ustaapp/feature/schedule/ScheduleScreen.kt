package com.example.ustaapp.feature.schedule

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ustaapp.core.ui.DisposableScreen
import com.example.ustaapp.core.ui.MviIntent
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
internal fun ScheduleScreen(
    modifier: Modifier = Modifier,
    viewmodel: ScheduleViewModel = hiltViewModel(),
) {
    DisposableScreen(viewmodel) {
        val state by viewmodel.state.collectAsStateWithLifecycle()
        ScheduleContent(state = state, intents = viewmodel.intents)
    }
}

@Composable
internal fun ScheduleContent(
    state: ScheduleState,
    intents: MutableSharedFlow<MviIntent>,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) LinearProgressIndicator()
}
