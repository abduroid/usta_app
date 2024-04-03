package com.example.ustaapp.feature.schedule

import com.example.ustaapp.core.ui.Middleware
import com.example.ustaapp.core.ui.MviStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ScheduleMiddlewares {

    @IntoSet
    @Binds
    fun bindGetScheduleMiddleware(impl: GetScheduleMiddleware): Middleware<ScheduleState>

    @IntoSet
    @Binds
    fun bindRefreshScheduleMiddleware(impl: RefreshScheduleMiddleware): Middleware<ScheduleState>
}

@Module
@InstallIn(SingletonComponent::class)
class ScheduleModule {

    @Provides
    @Singleton
    fun provideScheduleStore(
        middlewares: Set<@JvmSuppressWildcards Middleware<ScheduleState>>,
    ): MviStore<ScheduleState> = MviStore(
        initialState = ScheduleState(),
        reducer = ScheduleReducer(),
        middlewares = middlewares,
    )
}
