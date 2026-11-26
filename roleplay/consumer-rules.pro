# Consumer ProGuard Rules for roleplay module

# Keep public API
-keep public class com.eternia.ai.rpg.roleplay.** { *; }
-keep public interface com.eternia.ai.rpg.roleplay.** { *; }

# Keep Session
-keep class com.eternia.ai.rpg.roleplay.RoleplaySession { *; }

# Keep Models
-keep class com.eternia.ai.rpg.roleplay.model.** { *; }