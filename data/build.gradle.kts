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
    implementation(project(":data-local"))
    implementation(project(":data-remote"))
    implementation(project(":core:designsystem"))

    // Third Party
    implementation(libs.bundles.retrofit)
}
