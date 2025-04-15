plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "assignment.android.hilt"
            implementationClass = "com.example.assignment.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "assignment.kotlin.hilt"
            implementationClass = "com.example.assignment.HiltKotlinPlugin"
        }
        register("kotlinxSerialization") {
            id = "assignment.kotlinx.serialization"
            implementationClass = "com.example.assignment.SerializationPlugin"
        }
    }
}