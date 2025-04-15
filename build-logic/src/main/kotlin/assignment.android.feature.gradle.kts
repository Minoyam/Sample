import com.example.assignment.configureHiltAndroid
import com.example.assignment.configureKotlinxSerialization
import com.example.assignment.libs

plugins {
    id("assignment.android.library")
    id("assignment.android.compose")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureKotlinxSerialization()
configureHiltAndroid()

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))

    val libs = project.extensions.libs
    implementation(libs.findLibrary("hilt.navigation.compose").get())
    implementation(libs.findLibrary("androidx.compose.navigation").get())

    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())

    implementation(libs.findLibrary("kotlinx.serialization.json").get())

}