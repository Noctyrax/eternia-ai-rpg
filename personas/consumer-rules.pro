# Consumer ProGuard Rules for personas module

# Keep public API
-keep public class com.eternia.ai.rpg.personas.** { *; }
-keep public interface com.eternia.ai.rpg.personas.** { *; }

# Keep ViewModel
-keep class com.eternia.ai.rpg.personas.PersonaViewModel { *; }

# Keep Manager
-keep class com.eternia.ai.rpg.personas.PersonaManager { *; }

# Keep Models
-keep class com.eternia.ai.rpg.personas.model.** { *; }