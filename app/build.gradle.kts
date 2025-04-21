plugins {
    id("assignment.android.application")
}

android {
    namespace = "com.example.assignment.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.assignment"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "dagger.hilt.android.testing.HiltTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.feature.main)
    implementation(projects.server)
}