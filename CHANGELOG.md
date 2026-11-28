# CHANGELOG — ETERNIA AI RPG

## [1.0.0-pre-alpha] — 2026-11-27

### Ajouté
- Architecture modulaire complète (20+ modules : app, core, design-system, database, domain, ai-providers, personas, character-cards, lorebooks, memory, rpg-engine, roleplay, reader, visual-novel, companions, media, import-export, settings, agent, testing)
- Design-system avec thèmes sombre et clair, typographie fantasy, composants réutilisables
- 6 entités Room : Persona, Character, Conversation, Message, World, Novel
- 5 DAO : PersonaDao, CharacterDao, ConversationDao, MessageDao, WorldDao
- 8 managers de base : AiProviderManager, PersonaManager, KeyManager, RpgEngine, CompanionManager, CharacterCardParser, LorebookScanner, MemoryManager
- Navigation Compose avec AppNavHost (tous les écrans référencés)
- Écrans principaux : Home, Library, Discover, Rpg, Settings, Agent, et tous les sous-écrans
- Fichiers de statut du projet : PROJECT_STATUS.md, TODO.md, CHANGELOG.md

### Modifié
- Extensions.kt : remplacement de `getColumnIndex` déprécié par `getColumnIndexOrThrow`
- EterniaDatabase.kt : simplifié pour référencer uniquement les entités existantes
- AppNavHost.kt : imports corrigés depuis les bons packages

### Corrections
- Correction de l'import de build.gradle.kts (version du plugin Android)
- Résolution des dépendances circulaires entre modules

### Supprimé
- (aucune)

### Connu / À venir
- Les écrans affichent encore des messages "À venir" (implémentation partielle)
- Les managers sont des classes vides (logique métier à implémenter)
- Aucune compilation Android testée (environnement sans JVM fonctionnelle, sans Gradle)
- Aucune clé API dans le code (conforme au cahier des charges)
