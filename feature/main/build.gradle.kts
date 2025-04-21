plugins {
    id("assignment.android.feature")
}

android {
    namespace = "com.example.assignment.feature.main"
}

dependencies {
    implementation(projects.feature.commerce)
}