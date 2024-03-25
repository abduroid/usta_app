package com.example.ustaapp.feature.requests

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun RequestsRoute(
    onRequestClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RequestsViewModel = hiltViewModel(),
) {
//    val requestsState by viewModel.
}

@Composable
internal fun RequestsScreen(
    requestsState: RequestsUiState,
    onRequestClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (requestsState) {
        RequestsUiState.Empty -> Text(text = "empty requests")
        RequestsUiState.Loading -> Text(text = "Loading requests")
        is RequestsUiState.Success -> {
            Text(text = "First request sender id: ${requestsState.requests.first().senderId}")
        }
    }
}
