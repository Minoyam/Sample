plugins {
    id("assignment.android.feature")
}

android {
    namespace = "com.example.assignment.feature.commerce"
}

dependencies {
    implementation(libs.paging)
    implementation(libs.paging.compose)
    implementation(libs.paging.common)
    implementation(libs.coil)
    implementation(projects.domain)
}