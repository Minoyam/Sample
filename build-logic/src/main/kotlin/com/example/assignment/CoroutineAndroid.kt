package com.example.assignment

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoroutineAndroid() {
    val libs = extensions.libs
    configureCoroutineKotlin()
    dependencies {
        "implementation"(libs.findLibrary("coroutines.android").get())
    }
}