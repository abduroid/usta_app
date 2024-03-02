plugins {
    alias(libs.plugins.kallapp.android.feature)
    alias(libs.plugins.kallapp.android.library.compose)
    alias(libs.plugins.kallapp.android.library.jacoco)
}

android {
    namespace = "com.example.kallapp.feature.schedule"
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
