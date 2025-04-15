package com.example.assignment
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinxSerialization() {
    pluginManager.apply("kotlinx-serialization")

    val libs = extensions.libs

    dependencies {
        "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
    }
}

internal class SerializationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureKotlinxSerialization()
        }
    }
}