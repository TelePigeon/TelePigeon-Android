@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.library)
    alias(libs.plugins.telepigeon.android.test)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dongguk.telepigeon.feature"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:type"))
    implementation(project(":core:ui"))
    implementation(project(":domain"))

    // Android
    implementation(libs.bundles.androidx)

    // Google
    implementation(libs.google.material)

    // Coil
    implementation(libs.coil)
    implementation(libs.activity)
}
