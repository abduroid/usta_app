package com.example.ustaapp.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.ustaapp.core.datastore.TokenPairSerializer
import com.example.ustaapp.core.datastore.Tokenpair
import com.example.ustaapp.core.network.Dispatcher
import com.example.ustaapp.core.network.UstaDispatchers.IO
import com.example.ustaapp.core.network.di.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesTokenPairDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        tokenPairSerializer: TokenPairSerializer,
    ): DataStore<Tokenpair> =
        DataStoreFactory.create(
            serializer = tokenPairSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile("token_pair.pb")
        }
}
