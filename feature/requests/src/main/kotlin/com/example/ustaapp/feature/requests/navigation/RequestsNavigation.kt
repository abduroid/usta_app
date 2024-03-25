package com.example.ustaapp.feature.requests.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.ustaapp.feature.requests.RequestsRoute

const val REQUESTS_ROUTE = "requests_route"

fun NavController.navigateToRequests(navOptions: NavOptions) = navigate(REQUESTS_ROUTE, navOptions)

fun NavGraphBuilder.requestsScreen(onRequestsClick: (String) -> Unit) {
    composable(
        route = REQUESTS_ROUTE,
    ) {
        RequestsRoute(onRequestsClick)
    }
}
