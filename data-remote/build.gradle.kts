@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.library)
    alias(libs.plugins.telepigeon.android.test)
}

android {
    namespace = "com.dongguk.telepigeon.data.remote"
}

dependencies {
    // Third Party
    implementation(libs.bundles.retrofit)
    implementation(libs.kakao)
}
