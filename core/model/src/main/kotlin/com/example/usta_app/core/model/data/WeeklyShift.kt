package com.example.usta_app.core.model.data

data class WeeklyShift(
    val days: List<DailyShift>,
)

data class DailyShift(
    val shiftStartTime: Float,
    val shiftEndTime: Float,
    val lunchStartTime: Float,
    val lunchEndTime: Float,
    val isWorkingDay: Boolean,
)