# Consumer ProGuard Rules for visual-novel module

# Keep public API
-keep public class com.eternia.ai.rpg.visualnovel.** { *; }
-keep public interface com.eternia.ai.rpg.visualnovel.** { *; }

# Keep Models
-keep class com.eternia.ai.rpg.visualnovel.model.** { *; }