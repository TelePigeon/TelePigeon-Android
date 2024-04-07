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

rootProject.name = "TelePigeon"
include(":app")
include(":core:ui")
include(":core:designsystem")
include(":core:model")
include(":core:type")
include(":data-local")
include(":data")
include(":data-remote")
include(":domain")
include(":feature")
