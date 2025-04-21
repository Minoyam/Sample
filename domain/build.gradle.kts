plugins {
    id("assignment.kotlin.library")
}

dependencies {
    implementation(libs.inject)
    implementation(libs.coroutines.core)
    implementation(libs.paging.common)
}