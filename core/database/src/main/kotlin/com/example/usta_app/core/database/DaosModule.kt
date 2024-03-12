package com.example.usta_app.core.database

import com.example.usta_app.core.database.dao.ScheduleDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    fun provideScheduleDao(
        database: UstaDatabase
    ): ScheduleDao = database.scheduleDao()
}
