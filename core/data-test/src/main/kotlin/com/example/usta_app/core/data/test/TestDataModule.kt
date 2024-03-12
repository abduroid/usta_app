package com.example.usta_app.core.data.test

import com.example.usta_app.core.data.di.DataModule
import com.example.usta_app.core.data.repository.ScheduleRepository
import com.example.usta_app.core.data.repository.fake.FakeScheduleRepository
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
