plugins {
    alias(libs.plugins.ustaapp.android.feature)
    alias(libs.plugins.ustaapp.android.library.compose)
    alias(libs.plugins.ustaapp.android.library.jacoco)
}

android {
    namespace = "com.example.ustaapp.feature.settings"
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(projects.core.data)
    implementation(projects.core.domain)

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
}
