package com.example.usta_app.core.data.repository

import com.example.usta_app.core.data.testdoubles.TestUstaNetworkDataSource
import com.example.usta_app.core.data.testdoubles.TestScheduleDao
import com.example.usta_app.core.database.dao.ScheduleDao
import com.example.usta_app.core.database.model.AppointmentEntity
import com.example.usta_app.core.database.model.asExternalModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class OfflineFirstScheduleRepositoryTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: ScheduleRepository

    private lateinit var scheduleDao: ScheduleDao

    private lateinit var network: TestUstaNetworkDataSource


    @Before
    fun setup() {
        scheduleDao = TestScheduleDao()
        network = TestUstaNetworkDataSource()
        subject = OfflineFirstScheduleRepository(
            scheduleDao = scheduleDao,
            network = network
        )
    }

    @Test
    fun offlineFirstScheduleRepository_schedule_stream_is_backed_by_schedule_dao() =
        testScope.runTest {

        }



}