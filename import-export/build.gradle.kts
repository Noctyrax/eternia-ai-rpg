plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "com.eternia.ai.rpg.importexport"
    compileSdk = rootProject.ext.get("android.compileSdk") as Int
    defaultConfig {
        minSdk = rootProject.ext.get("android.minSdk") as Int
        targetSdk = rootProject.ext.get("android.targetSdk") as Int
        versionCode = rootProject.ext.get("android.versionCode") as Int
        versionName = rootProject.ext.get("android.versionName") as String
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
        )
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE,NOTICE}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":database"))
    implementation(project(":character-cards"))
    implementation(project(":lorebooks"))
    implementation(project(":personas"))
    implementation(project(":memory"))
    implementation(project(":rpg-engine"))
    implementation(project(":roleplay"))
    implementation(project(":reader"))
    implementation(project(":visual-novel"))
    implementation(project(":companions"))
    implementation(project(":media"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.8")
    implementation("io.arrow-kt:arrow-core:1.2.1")
    implementation("org.apache.tika:tika-core:2.9.1")
    implementation("org.apache.commons:commons-compress:1.15.0")
    implementation("org.apache.commons:commons-io:1.15.0")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.mockk:mockk:1.13.12")
}