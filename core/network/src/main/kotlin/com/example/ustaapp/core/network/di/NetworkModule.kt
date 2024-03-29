package com.example.ustaapp.core.network.di

import android.content.Context
import com.example.ustaapp.core.network.BuildConfig
import com.example.ustaapp.core.network.fake.FakeAssetManager
import com.example.ustaapp.core.network.retrofit.AuthenticatedClient
import com.example.ustaapp.core.network.retrofit.TokenRefresherClient
import com.example.ustaapp.core.network.retrofit.UnauthenticatedClient
import com.example.ustaapp.core.network.retrofit.UstaAuthenticator
import com.example.ustaapp.core.network.retrofit.api.AuthApi
import com.example.ustaapp.core.network.retrofit.api.RefreshTokenApi
import com.example.ustaapp.core.network.retrofit.api.UstaApi
import com.example.ustaapp.core.network.retrofit.interceptors.AccessTokenInterceptor
import com.example.ustaapp.core.network.retrofit.interceptors.RefreshTokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesFakeAssetManager(
        @ApplicationContext context: Context,
    ): FakeAssetManager = FakeAssetManager(context.assets::open)

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
        ).build()

    private fun retrofitBuilder(
        networkJson: Json,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .callFactory(okHttpClient)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    @UnauthenticatedClient
    fun providesAuthApi(
        networkJson: Json,
        okHttpClient: OkHttpClient,
    ): AuthApi = retrofitBuilder(networkJson, okHttpClient)
        .create(AuthApi::class.java)

    @Provides
    @Singleton
    @AuthenticatedClient
    fun providesUstaApi(
        networkJson: Json,
        interceptor: AccessTokenInterceptor,
        authenticator: UstaAuthenticator,
        okHttpClient: OkHttpClient,
    ): UstaApi = retrofitBuilder(
        networkJson = networkJson,
        okHttpClient = okHttpClient.newBuilder().addInterceptor(interceptor).authenticator(authenticator).build(),
    )
        .create(UstaApi::class.java)

    @Provides
    @Singleton
    @TokenRefresherClient
    fun providesRefreshTokenApi(
        interceptor: RefreshTokenInterceptor,
        okHttpClient: OkHttpClient,
        networkJson: Json,
    ): RefreshTokenApi = retrofitBuilder(
        networkJson = networkJson,
        okHttpClient = okHttpClient.newBuilder().addInterceptor(interceptor).build(),
    )
        .create(RefreshTokenApi::class.java)
}
