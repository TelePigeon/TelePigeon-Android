@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.library)
    alias(libs.plugins.telepigeon.android.test)
}

android {
    namespace = "com.dongguk.telepigeon.data"
}

dependencies {
    implementation(project(":domain"))

    // Third Party
    implementation(libs.bundles.retrofit)
}
