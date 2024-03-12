plugins {
    alias(libs.plugins.ustaapp.android.library)
    alias(libs.plugins.ustaapp.android.library.jacoco)
    alias(libs.plugins.ustaapp.android.hilt)
}

android {
    namespace = "com.example.usta_app.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}