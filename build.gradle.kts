@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.google.services)
        classpath(libs.google.firebase.crashlytics.gradle)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ktlint)
}

allprojects {
    apply {
        plugin(rootProject.libs.plugins.ktlint.get().pluginId)
    }
}
