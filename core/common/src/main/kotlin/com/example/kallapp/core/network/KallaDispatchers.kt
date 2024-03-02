package com.example.kallapp.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val kallaDispatcher: KallaDispatchers)

enum class KallaDispatchers {
    Default,
    IO,
}
