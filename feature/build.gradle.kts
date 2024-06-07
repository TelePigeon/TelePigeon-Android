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
    implementation(project(":core:ui"))
    implementation(project(":domain"))

    // Android
    implementation(libs.bundles.androidx)

    // Google
    implementation(libs.google.material)

    // Coil
    implementation(libs.coil)
    implementation(libs.activity)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Splash
    implementation(libs.androidx.core.splashscreen)

    // Network
    implementation(libs.retrofit)

    // Firebase Messaging
    implementation(libs.google.firebase.messaging)
    implementation(libs.google.firebase.messaging.ktx)
}
