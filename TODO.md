# TODO — ETERNIA AI RPG

## Étape 4 — Personas et personnages (en cours)

- [ ] Implémenter PersonaManager : CRUD complet, duplication, archivage, sélection par conversation
- [ ] Implémenter CharacterManager : CRUD complet, duplication, versions
- [ ] Gestion des images/avatars
- [ ] Variables de roleplay {{user}} compatibles
- [ ] Aperçu du prompt injecté pour Persona

## Étape 5 — Chat et fournisseurs IA (priorité)

- [ ] Implémenter AiProviderManager (abstraction multi-fournisseurs)
- [ ] Implémenter fournisseur Gemini
- [ ] Implémenter fournisseur DeepSeek
- [ ] Implémenter fournisseur OpenAI
- [ ] Implémenter fournisseur Anthropic
- [ ] Implémenter fournisseur Mistral
- [ ] Implémenter fournisseur OpenRouter
- [ ] Implémenter fournisseur Groq
- [ ] Streaming de réponses
- [ ] Gestion des erreurs API
- [ ] Prompt Inspector
- [ ] Test de connexion
- [ ] Gestion des quotas et limites de débit

## Étape 6 — Import/export

- [ ] Import JSON, JSONL, TXT, Markdown, ZIP
- [ ] Export conversations, personnages, données RPG
- [ ] Rapport d'importation
- [ ] Sauvegardes automatiques

## Étape 7 — Compatibilité SillyTavern

- [ ] Character Card V1, V2, V3 (dans CharacterCardParser)
- [ ] PNG avec métadonnées
- [ ] CHARX, Worldbooks, Lorebooks
- [ ] Prompt Inspector
- [ ] Macros {{user}}, {{char}}, {{random}}, {{getvar}}, {{setvar}}

## Étape 3 — Données locales (compléter)

- [ ] Implémenter PreferencesManager avec DataStore chiffré
- [ ] Implémenter KeyManager avec Android Keystore

## Étape 8 — Mémoire

- [ ] Résumés de conversation, chapitre, arc
- [ ] Récolte de faits (événements, relations, préférences, secrets)
- [ ] Recherche contextuelle
- [ ] Packs de contexte

## Étape 9 — Moteur RPG

- [ ] Statistiques, progression, montée de niveau
- [ ] Inventaire complet
- [ ] Compétences (actives/passives, arbres, cooldowns)
- [ ] Quêtes (principales, secondaires, quotidiennes)
- [ ] Combat (jets d'attaque/défense, coups critiques)
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
