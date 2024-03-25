pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "usta_app"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:model")
include(":core:common")
include(":core:testing")
include(":core:network")
include(":core:data")
include(":core:data-test")
include(":core:database")
include(":core:datastore")
include(":core:datastore-proto")
include(":core:datastore-test")
include(":core:designsystem")
include(":core:domain")
include(":core:notifications")
include(":core:ui")

include(":feature:schedule")
include(":feature:requests")
include(":feature:settings")
