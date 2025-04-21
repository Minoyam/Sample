plugins {
    id("assignment.android.library")
    id("kotlinx-serialization")
}

android{
    namespace = "com.example.assignment.datastore"
}
dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.datastore.preferences)
}