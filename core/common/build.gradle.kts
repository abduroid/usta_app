plugins {
    alias(libs.plugins.kallapp.android.library)
    alias(libs.plugins.kallapp.android.library.jacoco)
    alias(libs.plugins.kallapp.android.hilt)
}

android {
    namespace = "com.example.kallapp.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}