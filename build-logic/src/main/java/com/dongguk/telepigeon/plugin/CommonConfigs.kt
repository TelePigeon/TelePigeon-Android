package com.dongguk.telepigeon.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

internal fun Project.configureAndroidCommonPlugin() {
    apply<AndroidKotlinPlugin>()
    apply<KotlinSerializationPlugin>()
    apply<AndroidHiltPlugin>()
    with(plugins) {
        apply("kotlin-parcelize")
    }

    extensions.getByType<BaseExtension>().apply {
        buildFeatures.apply {
            viewBinding = true
            buildConfig = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        "implementation"(libs.findLibrary("androidx.appcompat").get())
        "implementation"(libs.findLibrary("androidx.core.ktx").get())
        "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel").get())
        "implementation"(libs.findLibrary("google.material").get())
        "implementation"(libs.findLibrary("timber").get())
    }
}