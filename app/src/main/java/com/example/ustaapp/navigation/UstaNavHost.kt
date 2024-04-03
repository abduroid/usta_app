package com.example.ustaapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.ustaapp.feature.requests.navigation.requestsScreen
import com.example.ustaapp.feature.schedule.navigation.SCHEDULE_ROUTE
import com.example.ustaapp.feature.schedule.navigation.scheduleScreen
import com.example.ustaapp.feature.settings.navigation.settingsScreen
import com.example.ustaapp.ui.UstaAppState

@Composable
fun UstaNavHost(
    appState: UstaAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = SCHEDULE_ROUTE,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        scheduleScreen()
        requestsScreen(onRequestsClick = navController::navigate)
        settingsScreen()
    }
}
