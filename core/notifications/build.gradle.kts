plugins {
    alias(libs.plugins.kallapp.android.library)
    alias(libs.plugins.kallapp.android.hilt)
}

android {
    namespace = "com.example.kallapp.core.notifications"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)

    compileOnly(platform(libs.androidx.compose.bom))
    compileOnly(libs.androidx.compose.runtime)
}
