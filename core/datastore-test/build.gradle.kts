plugins {
    alias(libs.plugins.ustaapp.android.library)
    alias(libs.plugins.ustaapp.android.hilt)
}

android {
    namespace = "com.example.usta_app.core.datastore.test"
}

dependencies {
    implementation(libs.hilt.android.testing)
    implementation(projects.core.common)
    implementation(projects.core.datastore)
}
