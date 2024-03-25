package com.example.ustaapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.ustaapp.core.data.util.NetworkMonitor
import com.example.ustaapp.feature.requests.navigation.REQUESTS_ROUTE
import com.example.ustaapp.feature.requests.navigation.navigateToRequests
import com.example.ustaapp.feature.schedule.navigation.SCHEDULE_ROUTE
import com.example.ustaapp.feature.schedule.navigation.navigateToSchedule
import com.example.ustaapp.feature.settings.navigation.SETTINGS_ROUTE
import com.example.ustaapp.feature.settings.navigation.navigateToSettings
import com.example.ustaapp.navigation.TopLevelDestination
import com.example.ustaapp.navigation.TopLevelDestination.REQUESTS
import com.example.ustaapp.navigation.TopLevelDestination.SCHEDULE
import com.example.ustaapp.navigation.TopLevelDestination.SETTINGS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberUstaAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): UstaAppState {
    return remember(
        navController,
        coroutineScope,
        networkMonitor,
    ) {
        UstaAppState(
            navController,
            coroutineScope,
            networkMonitor,
        )
    }
}

class UstaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            SCHEDULE_ROUTE -> SCHEDULE
            REQUESTS_ROUTE -> REQUESTS
            SETTINGS_ROUTE -> SETTINGS
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                SCHEDULE -> navController.navigateToSchedule(topLevelNavOptions)
                REQUESTS -> navController.navigateToRequests(topLevelNavOptions)
                SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
            }
        }
    }
}
