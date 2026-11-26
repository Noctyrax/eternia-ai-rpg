# ProGuard Rules for domain module

# Keep public API
-keep public class com.eternia.ai.rpg.domain.** { *; }
-keep public interface com.eternia.ai.rpg.domain.** { *; }

# Keep Repository Interfaces
-keep class com.eternia.ai.rpg.domain.repository.** { *; }