plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

android {
    namespace = "com.eternia.ai.rpg"
    compileSdk = rootProject.ext.get("android.compileSdk") as Int
    defaultConfig {
        applicationId = "com.eternia.ai.rpg"
        minSdk = rootProject.ext.get("android.minSdk") as Int
        targetSdk = rootProject.ext.get("android.targetSdk") as Int
        versionCode = rootProject.ext.get("android.versionCode") as Int
        versionName = rootProject.ext.get("android.versionName") as String
        vectorDrawables.useSupportLibrary = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-Xopt-in=kotlin.RequiresOptIn"
        )
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.ext.get("compose.compiler.version") as String
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE,NOTICE}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":design-system"))
    implementation(project(":database"))
    implementation(project(":domain"))
    implementation(project(":ai-providers"))
    implementation(project(":personas"))
    implementation(project(":character-cards"))
    implementation(project(":lorebooks"))
    implementation(project(":memory"))
    implementation(project(":rpg-engine"))
    implementation(project(":roleplay"))
    implementation(project(":reader"))
    implementation(project(":visual-novel"))
    implementation(project(":companions"))
    implementation(project(":import-export"))
    implementation(project(":settings"))
    implementation(project(":media"))
    implementation(project(":agent"))
    implementation(project(":testing"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("androidx.biometric:biometric:1.2.0-alpha05")
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    implementation("io.coil-kt:coil-compose:2.5.0")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest")
    ksp("androidx.room:room-compiler:2.6.1")
}
