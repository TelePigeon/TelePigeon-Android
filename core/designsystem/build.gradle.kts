@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dongguk.telepigeon.core.design.system"
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(project(":core:ui"))

    // Splash
    implementation(libs.androidx.core.splashscreen)
}
