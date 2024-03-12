package com.example.ustaapp.core.data.testdoubles

import com.example.ustaapp.core.database.dao.ScheduleDao
import com.example.ustaapp.core.database.model.AppointmentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestScheduleDao : ScheduleDao {

    private val entitiesStateFlow = MutableStateFlow(emptyList<AppointmentEntity>())

    override fun getAppointments(): Flow<List<AppointmentEntity>> = entitiesStateFlow

    override suspend fun insertAppointments(appointments: List<AppointmentEntity>) {
        entitiesStateFlow.update { appointments }
    }

    override suspend fun clearAppointments() {
        entitiesStateFlow.update { emptyList() }
    }
}
