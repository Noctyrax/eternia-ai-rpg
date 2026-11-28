# PROJECT STATUS — ETERNIA AI RPG

**Version :** 1.0.0 (pré-alpha)
**Date :** 2026-11-27
**Cahier des charges :** `inbox/ETERNIA_AI_RPG_PROMPT_COMPLET (1).txt`

## État général

Le projet est en pré-alpha. Architecture modulaire, vrais écrans Compose pour la navigation principale, base de données Room complète (16 entités, 13 DAO, migrations v1→v2→v3), 7 fournisseurs IA implémentés avec appels REST réels. Aucune compilation Android testée (pas de JVM fonctionnelle, pas de Gradle, SDK incomplet).

## Environnement de développement

| Outil | Statut | Détail |
|-------|--------|--------|
| Java/JDK 21 | ⚠️ Partiel | Installé mais `java -version` échoue (grsecurity/PaX) |
| Gradle | ❌ Absent | Non installé |
| Android SDK | ⚠️ Partiel | Présent dans `/opt/android-sdk` mais sdkmanager/avdmanager non fonctionnels |
| Git | ✅ Oui | Commit et push sur `fix/compilation-errors` réussis |

## Progression par étape (cahier des charges 14 étapes)

| Étape | Titre | Statut | Détail |
|-------|-------|--------|--------|
| 1 | Audit et architecture | ✅ | Terminé |
| 2 | UX/UI et navigation | ✅ | Vrais écrans Compose, navigation fonctionnelle, design-system |
| 3 | Données locales | ✅ | 16 entités Room, 13 DAO, migrations v1→v2→v3, Converters |
| 4 | Personas et personnages | ⚠️ En cours | Écrans + PersonaManager, CharacterManager (CRUD, duplication) |
| 5 | Chat et fournisseurs IA | ⚠️ En cours | Écrans de chat + 7 fournisseurs (Gemini, OpenAI, DeepSeek, Anthropic, Mistral, OpenRouter, Groq) avec appels REST réels |
| 6 | Import/export | ⚠️ Partiel | Écran ImportExportScreen créé, logique à implémenter |
| 7 | Compatibilité SillyTavern | ⚠️ Partiel | CharacterCardParser avec stubs, CharacterManager créé |
| 8 | Mémoire | ⚠️ Partiel | Écran MemoryScreen créé, MemoryDao créé, logique à implémenter |
| 9 | Moteur RPG | ⚠️ Partiel | Entités RpgCharacter, InventoryItem, DAO RpgDao, écrans créés |
| 10 | Reader et branches | ⚠️ Partiel | Écrans Novel créés, NovelDao, ChapterDao, logique à implémenter |
| 11 | Visual Novel et Companions | ⚠️ Partiel | Écrans créés, VisualNovelDao, CompanionDao, logique à implémenter |
| 12 | Multimédia et Agent | ⚠️ Partiel | Écrans créés, logique à implémenter |
| 13 | Tests et sécurité | ❌ Non commencé | |
| 14 | APK Release | ❌ Non commencé | Impossible de compiler ici |

## Dernier commit

**Commit `de7ff5f` sur branche `fix/compilation-errors` :**
- 12 fichiers, 955 insertions, 32 suppressions
- Branche poussée sur `origin/fix/compilation-errors`

## Contraintes identifiées

1. Impossible de compiler ou tester l'APK dans cet environnement (pas de JVM fonctionnelle, pas de Gradle, SDK Android incomplet)
2. Certains écrans affichent encore des messages "À venir" — logique métier à implémenter (Visual Novel, Reader, Combat, etc.)
3. Les managers ont des implémentations partielles (mémoire interne, pas encore de persistance Room)
4. Aucune clé API privée n'est dans le code (conforme au cahier des charges)

## Prochaine action immédiate

Implémenter l'Étape 6 (Import/export : JSON, JSONL, TXT, Markdown, ZIP) et l'Étape 7 (Compatibilité SillyTavern : Character Card V1/V2/V3, PNG, macros).

## Lien du repo

https://github.com/Noctyrax/eternia-ai-rpg/tree/fix/compilation-errors
