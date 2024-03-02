plugins {
    alias(libs.plugins.kallapp.android.library)
    alias(libs.plugins.kallapp.android.library.compose)
    alias(libs.plugins.kallapp.android.library.jacoco)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.example.kallapp.core.ui"
}

dependencies {
    api(projects.core.designsystem)
    api(projects.core.model)

    androidTestImplementation(projects.core.testing)
}
