package com.example.ustaapp.core.data.test

import com.example.ustaapp.core.data.di.DataModule
import com.example.ustaapp.core.data.repository.ScheduleRepository
import com.example.ustaapp.core.data.repository.fake.FakeScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class],
)
interface TestDataModule {

    @Binds
    fun bindsScheduleRepository(
        fakeScheduleRepository: FakeScheduleRepository,
    ): ScheduleRepository
}
