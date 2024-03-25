package com.example.ustaapp.core.database

import com.example.ustaapp.core.database.dao.ScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun provideScheduleDao(
        database: UstaDatabase,
    ): ScheduleDao = database.scheduleDao()
}
