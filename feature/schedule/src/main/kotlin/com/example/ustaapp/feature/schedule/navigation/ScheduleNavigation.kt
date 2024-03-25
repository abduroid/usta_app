package com.example.ustaapp.feature.schedule.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.ustaapp.feature.schedule.ScheduleRoute

const val SCHEDULE_ROUTE = "schedule_route"

fun NavController.navigateToSchedule(navOptions: NavOptions) = navigate(SCHEDULE_ROUTE, navOptions)

fun NavGraphBuilder.scheduleScreen(onAppointmentClick: (String) -> Unit) {
    composable(
        route = SCHEDULE_ROUTE,
    ) {
        ScheduleRoute(onAppointmentClick)
    }
}
