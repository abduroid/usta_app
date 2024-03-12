package com.example.usta_app.core.data.repository.fake

import com.example.usta_app.core.data.model.asEntity
import com.example.usta_app.core.data.repository.ScheduleRepository
import com.example.usta_app.core.database.model.AppointmentEntity
import com.example.usta_app.core.database.model.asExternalModel
import com.example.usta_app.core.model.data.Appointment
import com.example.usta_app.core.network.Dispatcher
import com.example.usta_app.core.network.UstaDispatchers.IO
import com.example.usta_app.core.network.fake.FakeUstaNetworkDataSource
import com.example.usta_app.core.network.model.NetworkAppointment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.threeten.bp.LocalDate
import javax.inject.Inject

class FakeScheduleRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: FakeUstaNetworkDataSource,
): ScheduleRepository {

    override fun getSchedule(from: LocalDate, to: LocalDate): Flow<List<Appointment>> =
        flow {
            emit(
                dataSource.getSchedule(from, to)
                    .map(NetworkAppointment::asEntity)
                    .map(AppointmentEntity::asExternalModel)

            )
        }.flowOn(ioDispatcher)

    override suspend fun refreshSchedule(from: LocalDate, to: LocalDate) {
        TODO("Not yet implemented")
    }
}