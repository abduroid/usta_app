plugins {
    alias(libs.plugins.kallapp.android.library)
    alias(libs.plugins.kallapp.android.library.jacoco)
    alias(libs.plugins.kallapp.android.hilt)
    alias(libs.plugins.kallapp.android.room)
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.example.kallapp.core.testing.KallaTestRunner"
    }
    namespace = "com.example.kallapp.core.database"
}

dependencies {
    api(projects.core.model)

    androidTestImplementation(projects.core.testing)
}
