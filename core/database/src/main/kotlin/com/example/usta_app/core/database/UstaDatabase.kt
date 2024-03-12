package com.example.usta_app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usta_app.core.database.dao.ScheduleDao
import com.example.usta_app.core.database.model.AppointmentEntity

@Database(
    entities = [AppointmentEntity::class],
    version = 1,
    exportSchema = true,
 )
internal abstract class UstaDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}
