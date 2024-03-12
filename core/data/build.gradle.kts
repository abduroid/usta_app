plugins {
    alias(libs.plugins.ustaapp.android.library)
    alias(libs.plugins.ustaapp.android.library.jacoco)
    alias(libs.plugins.ustaapp.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.usta_app.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.datastore)
    api(projects.core.network)

    implementation(projects.core.notifications)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(projects.core.datastoreTest)
    testImplementation(projects.core.testing)
}
