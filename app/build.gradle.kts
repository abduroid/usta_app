import com.example.ustaapp.UstaBuildType

plugins {
    alias(libs.plugins.ustaapp.android.application)
    alias(libs.plugins.ustaapp.android.application.compose)
    alias(libs.plugins.ustaapp.android.hilt)
    alias(libs.plugins.ustaapp.android.application.flavors)
}

android {
    namespace = "com.example.usta_app"
    defaultConfig {
        applicationId = "com.example.usta_app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.usta_app.core.testing.UstaTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = UstaBuildType.DEBUG.applicationIdSuffix
            manifestPlaceholders["securityConfig"] = "@xml/network_security_config_dev"

        }
        val release = getByName("release") {
            manifestPlaceholders["securityConfig"] = "@xml/network_security_config_prod"
            isMinifyEnabled = true
            applicationIdSuffix = UstaBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.feature.schedule)
    implementation(projects.feature.requests)
    implementation(projects.feature.settings)

    implementation(projects.core.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.model)


    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.testManifest)

    kspTest(libs.hilt.compiler)

    testImplementation(projects.core.dataTest)
    testImplementation(projects.core.testing)
    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(projects.core.dataTest)
    androidTestImplementation(projects.core.datastoreTest)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
}