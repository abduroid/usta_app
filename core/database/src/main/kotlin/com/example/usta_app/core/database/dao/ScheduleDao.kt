package com.example.usta_app.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.usta_app.core.database.model.AppointmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query(
        value = """
            SELECT * FROM appointments
        """
    )
    fun getAppointments(): Flow<List<AppointmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointments(appointments: List<AppointmentEntity>)

    @Query(value = """DELETE FROM appointments""")
    suspend fun clearAppointments()
}