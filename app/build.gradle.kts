import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.telepigeon.android.application)
    alias(libs.plugins.telepigeon.android.test)
    alias(libs.plugins.telepigeon.android.hilt)
}

val properties =
    Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }

android {
    namespace = "com.dongguk.telepigeon"

    defaultConfig {
        applicationId = "com.dongguk.telepigeon"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", properties["dev.base.url"].toString())
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            buildConfigField("String", "BASE_URL", properties["prod.base.url"].toString())
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":feature"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:type"))
    implementation(project(":core:ui"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":data-local"))
    implementation(project(":data-remote"))

    // Third Party
    implementation(libs.coil)
    implementation(libs.bundles.retrofit)
    implementation(platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.crashlytics)
}
