package com.example.usta_app.core.database

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// TODO commented out the provider due to absence of database initialization
@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
//    @Provides
//    @Singleton
//    fun providesUstaDatabase(
//        @ApplicationContext context: Context,
//    ): UstaDatabase = Room.databaseBuilder(
//        context,
//        UstaDatabase::class.java,
//        "usta-database",
//    ).build()
}
