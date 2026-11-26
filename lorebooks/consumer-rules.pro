# Consumer ProGuard Rules for lorebooks module

# Keep public API
-keep public class com.eternia.ai.rpg.lorebooks.** { *; }
-keep public interface com.eternia.ai.rpg.lorebooks.** { *; }

# Keep Scanner
-keep class com.eternia.ai.rpg.lorebooks.LorebookScanner { *; }

# Keep Models
-keep class com.eternia.ai.rpg.lorebooks.model.** { *; }