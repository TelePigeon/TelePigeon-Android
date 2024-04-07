plugins {
    `kotlin-dsl`
}

group = "com.dongguk.telepigeon.build.logic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("android-application") {
            id = "com.dongguk.telepigeon.application"
            implementationClass = "com.dongguk.telepigeon.plugin.AndroidApplicationPlugin"
        }
        create("android-hilt") {
            id = "com.dongguk.telepigeon.hilt"
            implementationClass = "com.dongguk.telepigeon.plugin.AndroidHiltPlugin"
        }
        create("android-kotlin") {
            id = "com.dongguk.telepigeon.kotlin"
            implementationClass = "com.dongguk.telepigeon.plugin.AndroidKotlinPlugin"
        }
        create("android-library") {
            id = "com.dongguk.telepigeon.library"
            implementationClass = "com.dongguk.telepigeon.plugin.AndroidLibraryPlugin"
        }
        create("android-test") {
            id = "com.dongguk.telepigeon.test"
            implementationClass = "com.dongguk.telepigeon.plugin.AndroidTestPlugin"
        }
        create("kotlin-serialization") {
            id = "com.dongguk.telepigeon.serialization"
            implementationClass = "com.dongguk.telepigeon.plugin.KotlinSerializationPlugin"
        }
    }
}