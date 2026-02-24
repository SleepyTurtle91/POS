plugins {
    id("org.jetbrains.kotlin.jvm")
}

// Kotlin JVM module; default toolchain (system JDK) will be used.

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
