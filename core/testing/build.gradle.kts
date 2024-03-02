plugins {
    alias(libs.plugins.kallapp.android.library)
    alias(libs.plugins.kallapp.android.library.compose)
    alias(libs.plugins.kallapp.android.hilt)
}

android {
    namespace = "com.example.kallapp.core.testing"
}

dependencies {
    api(kotlin("test"))
    api(libs.androidx.compose.ui.test)
    api(projects.core.data)
    api(projects.core.model)
    api(projects.core.notifications)

    debugApi(libs.androidx.compose.ui.testManifest)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
//    implementation(libs.kotlinx.datetime)
//    implementation(libs.robolectric.shadows)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
}
