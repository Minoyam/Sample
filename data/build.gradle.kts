plugins {
    id("assignment.android.library")
    id("kotlinx-serialization")
}

android{
    namespace = "com.example.assignment.data"
}
dependencies {
    implementation(libs.paging)
    implementation(projects.domain)
    implementation(projects.datastore)
}