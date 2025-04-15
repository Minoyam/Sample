package com.example.assignment

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltAndroid() {
    pluginManager.apply {
        apply("dagger.hilt.android.plugin")
        apply("kotlin-kapt")
    }

    val libs = extensions.libs
    dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "kapt"(libs.findLibrary("hilt.android.compiler").get())
        "kaptAndroidTest"(libs.findLibrary("hilt.android.compiler").get())
        "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
        "testImplementation"(libs.findLibrary("hilt.android.testing").get())
        "androidTestAnnotationProcessor" (libs.findLibrary("hilt.android.compiler").get())
    }
}

internal class HiltAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureHiltAndroid()
        }
    }
}