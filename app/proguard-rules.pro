# ETERNIA AI RPG ProGuard Rules
# Generated for Android Gradle Plugin 8.5.0

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class com.eternia.ai.rpg.**_HiltComponents { *; }
-keep class com.eternia.ai.rpg.**_GeneratedRootComponent { *; }
-keep class com.eternia.ai.rpg.**_ViewModelFactory { *; }

# Keep Room database
-keep class com.eternia.ai.rpg.database.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * implements androidx.room.TypeConverter { *; }
-keepclassmembers class * implements androidx.room.TypeConverter {
    *;
}

# Keep Kotlin serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

# Keep Compose
-keep class androidx.compose.** { *; }
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *;
}

# Keep Navigation
-keep class androidx.navigation.** { *; }

# Keep Hilt Navigation Compose
-keep class androidx.hilt.navigation.compose.** { *; }

# Keep Coil
-keep class coil3.** { *; }

# Keep OkHttp
-keep class okhttp3.** { *; }
-keep class okio.** { *; }

# Keep Retrofit
-keep class retrofit2.** { *; }

# Keep Moshi
-keep class com.squareup.moshi.** { *; }

# Keep Kotlinx Coroutines
-keep class kotlinx.coroutines.** { *; }

# Keep Kotlinx Serialization JSON
-keep class kotlinx.serialization.json.** { *; }

# Keep Arrow
-keep class io.arrow_kt.** { *; }

# Keep Kotlinx Datetime
-keep class kotlinx.datetime.** { *; }

# Keep Kotlinx Collections Immutable
-keep class kotlinx.collections.immutable.** { *; }

# Keep Datastore
-keep class androidx.datastore.** { *; }

# Keep Security Crypto
-keep class androidx.security.crypto.** { *; }

# Keep Biometric
-keep class androidx.biometric.** { *; }

# Keep Splash Screen
-keep class androidx.core.splashscreen.** { *; }

# Keep WorkManager
-keep class androidx.work.** { *; }

# Keep Media3
-keep class androidx.media3.** { *; }

# Keep ExoPlayer
-keep class com.google.android.exoplayer2.** { *; }

# Keep Tika
-keep class org.apache.tika.** { *; }

# Keep Apache Commons
-keep class org.apache.commons.** { *; }

# Keep KSP generated
-keep class com.google.devtools.ksp.** { *; }

# Keep our model classes
-keep class com.eternia.ai.rpg.**.model.** { *; }
-keep class com.eternia.ai.rpg.**.entity.** { *; }
-keep class com.eternia.ai.rpg.**.data.** { *; }
-keep class com.eternia.ai.rpg.**.dto.** { *; }

# Keep ViewModels
-keep class com.eternia.ai.rpg.**.viewmodel.** { *; }

# Keep Screens/Composables
-keep class com.eternia.ai.rpg.**.ui.screens.** { *; }

# Keep Repository interfaces
-keep class com.eternia.ai.rpg.domain.repository.** { *; }

# Keep AI Provider classes
-keep class com.eternia.ai.rpg.aiproviders.** { *; }

# Keep RPG Engine classes
-keep class com.eternia.ai.rpg.rpgengine.** { *; }

# Keep Visual Novel classes
-keep class com.eternia.ai.rpg.visualnovel.** { *; }

# Keep Character Cards classes
-keep class com.eternia.ai.rpg.charactercards.** { *; }

# Keep Lorebooks classes
-keep class com.eternia.ai.rpg.lorebooks.** { *; }

# Keep Memory classes
-keep class com.eternia.ai.rpg.memory.** { *; }

# Keep Companions classes
-keep class com.eternia.ai.rpg.companions.** { *; }

# Keep Media classes
-keep class com.eternia.ai.rpg.media.** { *; }

# Keep Import/Export classes
-keep class com.eternia.ai.rpg.importexport.** { *; }

# Keep Settings classes
-keep class com.eternia.ai.rpg.settings.** { *; }

# Keep Agent classes
-keep class com.eternia.ai.rpg.agent.** { *; }

# Keep Personas classes
-keep class com.eternia.ai.rpg.personas.** { *; }

# Keep Reader classes
-keep class com.eternia.ai.rpg.reader.** { *; }

# Keep Roleplay classes
-keep class com.eternia.ai.rpg.roleplay.** { *; }

# Don't warn about missing classes
-dontwarn kotlinx.coroutines.**
-dontwarn kotlinx.serialization.**
-dontwarn kotlinx.datetime.**
-dontwarn kotlinx.collections.immutable.**
-dontwarn io.arrow_kt.**
-dontwarn org.apache.tika.**
-dontwarn org.apache.commons.**
-dontwarn androidx.room.**
-dontwarn androidx.datastore.**
-dontwarn androidx.security.crypto.**
-dontwarn androidx.biometric.**
-dontwarn androidx.core.splashscreen.**
-dontwarn androidx.work.**
-dontwarn androidx.media3.**
-dontwarn com.google.android.exoplayer2.**
-dontwarn coil3.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**
-dontwarn com.squareup.moshi.**
-dontwarn dagger.hilt.**
-dontwarn androidx.hilt.**
-dontwarn androidx.navigation.**
-dontwarn androidx.compose.**
-dontwarn kotlinx.coroutines.channels.**
-dontwarn kotlinx.coroutines.flow.**

# Optimize
-optimizationpasses 5
-allowaccessmodification
-mergespecfiles

# Keep line numbers for debugging
-keepattributes SourceFile,LineNumberTable

# Keep annotations
-keepattributes *Annotation*
-keepattributes RuntimeVisibleAnnotations,RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations,RuntimeInvisibleParameterAnnotations
-keepattributes AnnotationDefault

# Keep generic signatures
-keepattributes Signature

# Keep EnclosingMethod for lambdas
-keepattributes EnclosingMethod

# Keep InnerClasses
-keepattributes InnerClasses