plugins {
    alias(libs.plugins.ustaapp.android.library)
    alias(libs.plugins.ustaapp.android.hilt)
}

android {
    namespace = "com.example.usta_app.core.notifications"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)

    compileOnly(platform(libs.androidx.compose.bom))
    compileOnly(libs.androidx.compose.runtime)
}
