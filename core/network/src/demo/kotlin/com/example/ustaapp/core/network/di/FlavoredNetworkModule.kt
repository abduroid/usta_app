package com.example.ustaapp.core.network.di

import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.fake.FakeTokenManager
import com.example.ustaapp.core.network.fake.FakeUstaNetworkDataSource
import com.example.ustaapp.core.network.token.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun bindsNetworkDataSource(impl: FakeUstaNetworkDataSource): UstaNetworkDataSource

    @Binds
    fun bindsTokenManager(impl: FakeTokenManager): TokenManager
}
