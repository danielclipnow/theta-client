plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("8.11.2").apply(false)
    kotlin("multiplatform").version("2.2.20").apply(false)
    kotlin("plugin.serialization").version("2.2.20").apply(false)
    id("org.jetbrains.dokka").version("2.0.0")
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:versioning-plugin:2.0.0")
    }
}

tasks.register("clean", Delete::class) {
}
