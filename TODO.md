# TODO — ETERNIA AI RPG

## Étape 2 — UX/UI et navigation (en cours)

- [ ] Remplacer les écrans "À venir" par de vraies implémentations Compose
- [ ] Implémenter HomeScreen avec données réelles (liste des conversations, personas, etc.)
- [ ] Créer PersonaListScreen avec liste réelle depuis Room
- [ ] Créer PersonaEditorScreen avec formulaire complet (nom, description, avatar, stats, etc.)
- [ ] Créer PersonaDetailScreen avec aperçu du prompt injecté
- [ ] Créer CharacterListScreen et CharacterEditorScreen
- [ ] Créer AppNavHost fonctionnel avec navigation type-safe
- [ ] Implémenter EterniaTheme complet (thème sombre/clair, typographie fantasy)
- [ ] Créer les composants UI réutilisables (FantasyCard, SectionCard, etc.)

## Étape 3 — Données locales

- [ ] Compléter EterniaDatabase avec toutes les entités du cahier des charges
- [ ] Créer tous les DAO manquants (LorebookDao, RpgDao, ReaderDao, etc.)
- [ ] Ajouter les migrations Room (version 1 → 2, etc.)
- [ ] Implémenter PreferencesManager avec DataStore chiffré
- [ ] Implémenter KeyManager avec Android Keystore

## Étape 4 — Personas et personnages

- [ ] Implémenter PersonaManager (CRUD complet)
- [ ] Implémenter CharacterManager (CRUD complet)
- [ ] Gestion des images/avatars
- [ ] Duplication et archivage de Personas
- [ ] Sélection de Persona par conversation

## Étape 5 — Chat et fournisseurs IA

- [ ] Implémenter AiProviderManager (abstraction multi-fournisseurs)
- [ ] Implémenter Gemini, DeepSeek, OpenAI, Anthropic, Mistral, OpenRouter, Groq
- [ ] Streaming de réponses
- [ ] Gestion des erreurs API
- [ ] Prompt Inspector
- [ ] Test de connexion

## Étape 6 — Import/export

- [ ] Import JSON, JSONL, TXT, Markdown, ZIP
- [ ] Export conversations, personnages, données RPG
- [ ] Rapport d'importation
- [ ] Sauvegardes automatiques

## Étape 7 — Compatibilité SillyTavern

- [ ] Character Card V1, V2, V3
- [ ] PNG avec métadonnées
- [ ] CHARX, Worldbooks, Lorebooks
- [ ] Prompt Inspector
- [ ] Macros {{user}}, {{char}}, {{random}}, {{getvar}}, {{setvar}}

## Étape 8 — Mémoire

- [ ] Résumés de conversation, chapitre, arc
- [ ] Récolte de faits (événements, relations, préférences, secrets)
- [ ] Recherche sémantique (préparé)
- [ ] Packs de contexte

## Étape 9 — Moteur RPG

- [ ] Statistiques (Force, Dextérité, Constitution, Intelligence, Sagesse, Charisme, Chance)
- [ ] Santé/Mana/Endurance avec régénération
- [ ] Inventaire (objets, équipement, rareté)
- [ ] Compétences (actives/passives, arbres, cooldowns)
- [ ] Quêtes (principales, secondaires, quotidiennes)
- [ ] Combat (jets d'attaque/défense, coups critiques, types de dégâts)
- [ ] PNJ, marchands, factions, réputation
- [ ] Monde (localisations, découverte, voyages)

## Étape 10 — Reader et branches

- [ ] Lecteur TXT, EPUB, Markdown
- [ ] Signets, TOC, recherche
- [ ] Continuation IA, réécriture, développement
- [ ] Branches narratives sans écraser l'original

## Étape 11 — Visual Novel et Companions

- [ ] Scènes, arrière-plans, sprites, expressions
- [ ] Choix interactifs, variables, fins multiples
- [ ] Compagnons, groupes, relations, messages proactifs

## Étape 12 — Multimédia et Agent

- [ ] Génération d'images
- [ ] TTS/STT
- [ ] Agent IA personnel (recherche, résumé, organisation)

## Étape 13 — Tests et sécurité

- [ ] Tests unitaires, d'intégration, UI
- [ ] Audit des permissions Android
- [ ] Audit des secrets (Android Keystore)
- [ ] Vérification des imports (ZIP, scripts malveillants)

## Étape 14 — APK Release

- [ ] Installer Android SDK et Gradle dans un environnement adapté
- [ ] Compiler `./gradlew assembleDebug`
- [ ] Signer l'APK Release avec keystore utilisateur
- [ ] Créer une release GitHub avec l'APK
- [ ] Documenter l'installation sur Motorola ThinkPhone 25

## Documentation

- [ ] README d'installation
- [ ] Guide de configuration des API
- [ ] Guide d'importation SillyTavern
- [ ] Guide de sauvegarde et restauration
- [ ] Documentation des formats de données
