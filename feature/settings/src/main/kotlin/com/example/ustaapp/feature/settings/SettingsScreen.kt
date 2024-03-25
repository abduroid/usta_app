package com.example.ustaapp.feature.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SettingsRoute(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
}

@Composable
internal fun SettingsScreen(
    settingsUiState: SettingsUiState,
    onRequestClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (settingsUiState) {
        SettingsUiState.Empty -> Text(text = "empty requests")
        SettingsUiState.Loading -> Text(text = "Loading requests")
        is SettingsUiState.Success -> {
            Text(text = "Barber name is:  ${settingsUiState.userData.firstName}")
        }
    }
}
