package com.example.ustaapp.core.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel<State : MviState>(
    private val savedStateHandle: SavedStateHandle,
    private val store: MviStore<State>,
) : ViewModel() {

    val state = store.state
    val intents = store.intents

    open fun onStart(coroutineScope: CoroutineScope) {
        var restoredFromState = false
//        if (savedStateHandle.contains("mviState")) {
//            restoredFromState = true
//            state.value = checkNotNull(savedStateHandle["mviState"])
//        }

        store.init(
            coroutineScope = coroutineScope,
            savedStateHandle = savedStateHandle,
        )

        if (!restoredFromState) {
            // TODO uncomment when saving state is fixed
//            savedStateHandle["mviState"] = state.value
        }
    }

    open fun onStop() {}
}
