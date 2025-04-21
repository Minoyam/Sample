plugins {
    id("assignment.android.library")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.assignment.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}