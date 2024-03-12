package com.example.usta_app.core.data.repository

import com.example.usta_app.core.data.model.asEntity
import com.example.usta_app.core.database.dao.ScheduleDao
import com.example.usta_app.core.database.model.AppointmentEntity
import com.example.usta_app.core.database.model.asExternalModel
import com.example.usta_app.core.model.data.Appointment
import com.example.usta_app.core.network.UstaNetworkDataSource
import com.example.usta_app.core.network.model.NetworkAppointment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.LocalDate
import javax.inject.Inject

internal class OfflineFirstScheduleRepository @Inject constructor(
    private val scheduleDao: ScheduleDao,
    private val network: UstaNetworkDataSource,
) : ScheduleRepository {

    override fun getSchedule(from: LocalDate, to: LocalDate): Flow<List<Appointment>> =
        scheduleDao.getAppointments().map { it.map(AppointmentEntity::asExternalModel) }

    override suspend fun refreshSchedule(from: LocalDate, to: LocalDate) {
        val fetchedSchedule = network.getSchedule(from, to)
        scheduleDao.insertAppointments(fetchedSchedule.map(NetworkAppointment::asEntity))
    }
}