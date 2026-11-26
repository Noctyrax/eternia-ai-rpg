# Consumer ProGuard Rules for character-cards module

# Keep public API
-keep public class com.eternia.ai.rpg.charactercards.** { *; }
-keep public interface com.eternia.ai.rpg.charactercards.** { *; }

# Keep Parser
-keep class com.eternia.ai.rpg.charactercards.CharacterCardParser { *; }

# Keep Models
-keep class com.eternia.ai.rpg.charactercards.model.** { *; }