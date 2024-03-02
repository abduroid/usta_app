package com.example.kallapp.core.database

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// TODO commented out the provider due to absence of database initialization
@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
//    @Provides
//    @Singleton
//    fun providesKallaDatabase(
//        @ApplicationContext context: Context,
//    ): KallaDatabase = Room.databaseBuilder(
//        context,
//        KallaDatabase::class.java,
//        "kalla-database",
//    ).build()
}
