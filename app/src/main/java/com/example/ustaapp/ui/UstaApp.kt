package com.example.ustaapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.usta_app.R
import com.example.ustaapp.core.data.util.NetworkMonitor
import com.example.ustaapp.core.designsystem.component.UstaNavigationBar
import com.example.ustaapp.core.designsystem.component.UstaNavigationBarItem
import com.example.ustaapp.core.designsystem.component.UstaTopAppBar
import com.example.ustaapp.core.designsystem.icon.UstaIcons
import com.example.ustaapp.navigation.TopLevelDestination
import com.example.ustaapp.navigation.UstaNavHost
import com.example.ustaapp.feature.settings.R as settingsR

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
fun UstaApp(
    networkMonitor: NetworkMonitor,
    appState: UstaAppState = rememberUstaAppState(
        networkMonitor = networkMonitor,
    ),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            UstaBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination,
                modifier = Modifier.testTag("UstaBottomBar"),
            )
        },
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding),
//                .windowInsetsPadding(
//                    WindowInsets.safeDrawing.only(
//                        Horizontal,
//                    ),
//                )
        ) {
            Column(Modifier.fillMaxSize()) {
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    UstaTopAppBar(
                        titleRes = destination.titleTextId,
                        actionIcon = UstaIcons.Settings,
                        actionIconContentDescription = stringResource(id = settingsR.string.feature_settings_title),
                    )
                }

                UstaNavHost(
                    appState = appState,
                    onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = SnackbarDuration.Short,
                        ) == SnackbarResult.ActionPerformed
                    },
                )
            }
        }
    }
}

@Composable
private fun UstaBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    UstaNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            UstaNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
