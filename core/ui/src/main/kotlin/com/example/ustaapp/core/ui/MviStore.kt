package com.example.ustaapp.core.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.update

interface MviIntent
interface MviState

sealed interface GenericIntent : MviIntent {
    object Init : GenericIntent
    object Loading : GenericIntent
    open class Failure(
        val error: Throwable,
        val showAlert: Boolean = true,
    ) : GenericIntent
    data class FailureAcknowledged(val failure: Failure) : GenericIntent
}

interface Reducer<State : MviState> {
    fun reduce(state: State, intent: MviIntent): State
}

interface Middleware<State : MviState> {
    fun execute(
        intent: MviIntent,
        state: State,
        outputIntents: MutableSharedFlow<MviIntent>,
        coroutineScope: CoroutineScope,
    )
}

inline fun <reified T> Any?.tryCast(block: T.() -> Unit) {
    if (this is T) block()
}

class MviStore<State : MviState>(
    private val initialState: State,
    private val reducer: Reducer<State>,
    private val middlewares: Set<Middleware<State>>,
) {

    val state = MutableStateFlow(initialState)
    val intents = MutableSharedFlow<MviIntent>(extraBufferCapacity = 100)

    fun init(
        coroutineScope: CoroutineScope,
        savedStateHandle: SavedStateHandle,
    ) {
        intents
            .onSubscription {
                Log.d("jprq", "onSubscription")
                intents.emit(GenericIntent.Init)
            }
            .onEach {
                state.update { old ->
                    reducer.reduce(old, it).also {
                        // TODO uncomment when state saving is fixed
//                        savedStateHandle["mviState"] = it
                    }
                }
            }
            .onEach { intent ->
                Log.d("MviStore intents::onEach", "ACTION: ${intent.javaClass.simpleName}")
                middlewares.forEach {
                    it.execute(intent, state.value, intents, coroutineScope)
                }
            }
            .onCompletion {
                Log.d("MviStore intents::onCompletion", "Clear the STATE: ${initialState.javaClass.simpleName}")
                state.value = initialState
            }
            .launchIn(coroutineScope)
    }
}
