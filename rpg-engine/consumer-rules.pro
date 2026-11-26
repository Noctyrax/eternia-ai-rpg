# Consumer ProGuard Rules for rpg-engine module

# Keep public API
-keep public class com.eternia.ai.rpg.rpgengine.** { *; }
-keep public interface com.eternia.ai.rpg.rpgengine.** { *; }

# Keep Engine
-keep class com.eternia.ai.rpg.rpgengine.RpgEngine { *; }

# Keep Models
-keep class com.eternia.ai.rpg.rpgengine.model.** { *; }