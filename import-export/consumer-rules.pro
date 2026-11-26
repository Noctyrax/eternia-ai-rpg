# Consumer ProGuard Rules for import-export module

# Keep public API
-keep public class com.eternia.ai.rpg.importexport.** { *; }
-keep public interface com.eternia.ai.rpg.importexport.** { *; }

# Keep Models
-keep class com.eternia.ai.rpg.importexport.model.** { *; }