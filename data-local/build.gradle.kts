@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.library)
    alias(libs.plugins.telepigeon.android.test)
}

android {
    namespace = "com.dongguk.telepigeon.data.local"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":data"))
}
