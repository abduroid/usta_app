package com.example.ustaapp.core.network.di

import com.example.ustaapp.core.network.UstaNetworkDataSource
import com.example.ustaapp.core.network.retrofit.RetrofitUstaNetwork
import com.example.ustaapp.core.network.token.PersistentTokenManager
import com.example.ustaapp.core.network.token.TokenManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun bindsNetworkDataSource(retrofitUstaNetwork: RetrofitUstaNetwork): UstaNetworkDataSource

    @Binds
    fun bindsTokenManager(tokenManager: PersistentTokenManager): TokenManager
}
