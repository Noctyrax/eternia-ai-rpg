plugins {
    id("com.android.application") version "8.5.0" apply false
    id("com.android.library") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.23" apply false
}

// Values from gradle.properties are project properties, not automatically
// extra properties. Expose the names used by the existing module scripts.
extra["android.compileSdk"] = property("android.compileSdk").toString().toInt()
extra["android.minSdk"] = property("android.minSdk").toString().toInt()
extra["android.targetSdk"] = property("android.targetSdk").toString().toInt()
extra["android.versionCode"] = property("android.versionCode").toString().toInt()
extra["android.versionName"] = property("android.versionName").toString()
extra["compose.compiler.version"] = property("compose.compiler.version").toString()

allprojects {
    group = "com.eternia.ai.rpg"
    version = "1.0.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
