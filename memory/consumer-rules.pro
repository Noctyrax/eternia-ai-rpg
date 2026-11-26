# Consumer ProGuard Rules for memory module

# Keep public API
-keep public class com.eternia.ai.rpg.memory.** { *; }
-keep public interface com.eternia.ai.rpg.memory.** { *; }

# Keep Manager
-keep class com.eternia.ai.rpg.memory.MemoryManager { *; }

# Keep Models
-keep class com.eternia.ai.rpg.memory.model.** { *; }