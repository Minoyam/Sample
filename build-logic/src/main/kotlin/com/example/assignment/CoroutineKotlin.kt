package com.example.assignment

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoroutineKotlin() {
    val libs = extensions.libs
    dependencies {
        "implementation"(libs.findLibrary("coroutines.core").get())
        "testImplementation"(libs.findLibrary("coroutines.test").get())
    }
}