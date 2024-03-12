plugins {
    alias(libs.plugins.ustaapp.android.library)
    alias(libs.plugins.ustaapp.android.library.jacoco)
    alias(libs.plugins.ustaapp.android.hilt)
    alias(libs.plugins.ustaapp.android.room)
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.example.usta_app.core.testing.UstaTestRunner"
    }
    namespace = "com.example.usta_app.core.database"
}

dependencies {
    api(projects.core.model)

    androidTestImplementation(projects.core.testing)
}
