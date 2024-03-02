plugins {
    alias(libs.plugins.kallapp.android.library)
    alias(libs.plugins.kallapp.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.kallapp.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)

    testImplementation(projects.core.testing)
}