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

        // KakaoSDK repository
        maven(url = "https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

rootProject.name = "TelePigeon"
include(":app")
include(":core:ui")
include(":core:designsystem")
include(":core:model")
include(":data-local")
include(":data")
include(":data-remote")
include(":domain")
include(":feature")
