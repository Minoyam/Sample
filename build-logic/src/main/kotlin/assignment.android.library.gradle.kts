import com.example.assignment.configureCoroutineAndroid
import com.example.assignment.configureHiltAndroid
import com.example.assignment.configureKotlinAndroid
import com.example.assignment.libs

plugins {
    id("com.android.library")
}
android {
    defaultConfig {
        testInstrumentationRunner = "dagger.hilt.android.testing.HiltTestRunner"
    }
}
dependencies {
    val libs = project.extensions.libs

    implementation(libs.findLibrary("junit").get())
    implementation(libs.findLibrary("androidx.junit").get())
    implementation(libs.findLibrary("androidx.espresso.core").get())
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()