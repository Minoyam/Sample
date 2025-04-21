plugins {
    id("assignment.android.library")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.assignment.server"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.bundles.remote)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.paging)
    implementation(projects.data)
}