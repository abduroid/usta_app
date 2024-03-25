package com.example.ustaapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ustaapp.core.designsystem.icon.UstaIcons
import com.example.ustaapp.feature.requests.R as requestsR
import com.example.ustaapp.feature.schedule.R as scheduleR
import com.example.ustaapp.feature.settings.R as settingsR

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    SCHEDULE(
        selectedIcon = UstaIcons.Table,
        unselectedIcon = UstaIcons.TableBorder,
        iconTextId = scheduleR.string.feature_schedule_title,
        titleTextId = scheduleR.string.feature_schedule_title,
    ),
    REQUESTS(
        selectedIcon = UstaIcons.Requests,
        unselectedIcon = UstaIcons.RequestsBorder,
        iconTextId = requestsR.string.feature_requests_title,
        titleTextId = requestsR.string.feature_requests_title,
    ),
    SETTINGS(
        selectedIcon = UstaIcons.Settings,
        unselectedIcon = UstaIcons.SettingsBorder,
        iconTextId = settingsR.string.feature_settings_title,
        titleTextId = settingsR.string.feature_settings_title,
    ),
}
