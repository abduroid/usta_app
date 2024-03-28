package com.example.ustaapp.core.network.retrofit

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticatedClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TokenRefresherClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UnauthenticatedClient
