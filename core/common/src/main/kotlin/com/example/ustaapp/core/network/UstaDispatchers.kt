package com.example.ustaapp.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val ustaDispatchers: UstaDispatchers)

enum class UstaDispatchers {
    Default,
    IO,
}
