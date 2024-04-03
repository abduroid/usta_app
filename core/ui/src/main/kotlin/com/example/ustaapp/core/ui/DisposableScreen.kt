package com.example.ustaapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun <T : MviState> DisposableScreen(
    viewModel: BaseViewModel<T>,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(key1 = viewModel) {
        viewModel.onStart(coroutineScope)
        onDispose {
            viewModel.onStop()
        }
    }

    content()
}
