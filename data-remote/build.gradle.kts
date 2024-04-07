@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.library)
    alias(libs.plugins.telepigeon.android.test)
}

android {
    namespace = "com.dongguk.telepigeon.data.remote"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    // Third Party
    implementation(libs.bundles.retrofit)
}