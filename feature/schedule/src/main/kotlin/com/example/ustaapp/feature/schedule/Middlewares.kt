package com.example.ustaapp.feature.schedule

import android.util.Log
import com.example.ustaapp.core.domain.GetScheduleUseCase
import com.example.ustaapp.core.domain.RefreshScheduleUseCase
import com.example.ustaapp.core.ui.GenericIntent
import com.example.ustaapp.core.ui.Middleware
import com.example.ustaapp.core.ui.MviIntent
import com.example.ustaapp.core.ui.tryCast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

class GetScheduleMiddleware @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
) : Middleware<ScheduleState> {

    override fun execute(
        intent: MviIntent,
        state: ScheduleState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope,
    ) {
        Log.d("jprq", "getschedule middleware invoked")
        intent.tryCast<GenericIntent.Init> {
            getScheduleUseCase(LocalDate.MIN, LocalDate.MAX)
                .onEach { appointments ->
                    outputIntents.tryEmit(
                        ScheduleIntent.ScheduleReady(appointments),
                    )
                }
                .catch { outputIntents.tryEmit(GenericIntent.Failure(it)) }
                .launchIn(coroutineScope)
        }
    }
}

class RefreshScheduleMiddleware @Inject constructor(
    private val refreshScheduleUseCase: RefreshScheduleUseCase,
) : Middleware<ScheduleState> {

    override fun execute(
        intent: MviIntent,
        state: ScheduleState,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope,
    ) {
        intent.tryCast<GenericIntent.Init> {
            coroutineScope.launch {
                try {
                    outputIntents.tryEmit(GenericIntent.Loading)
                    refreshScheduleUseCase(LocalDate.MIN, LocalDate.MAX)
                } catch (error: Throwable) {
                    outputIntents.tryEmit(GenericIntent.Failure(error))
                }
            }
        }
    }
}
