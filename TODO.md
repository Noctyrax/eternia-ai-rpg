# TODO — ETERNIA AI RPG

## Étape 6 — Import/export (priorité immédiate)

- [ ] Import JSON, JSONL, TXT, Markdown, ZIP
- [ ] Export conversations, personnages, données RPG
- [ ] Rapport d'importation (données importées, ignorées, inconnues, avertissements)
- [ ] Sauvegardes automatiques facultatives
- [ ] Restauration complète avec contrôle d'intégrité

## Étape 7 — Compatibilité SillyTavern

- [ ] Character Card V1, V2, V3 (dans CharacterCardParser)
- [ ] PNG avec métadonnées embarquées
- [ ] CHARX, Worldbooks, Lorebooks
- [ ] Prompt Inspector (visualiser le contexte envoyé au modèle)
- [ ] Macros {{user}}, {{char}}, {{random}}, {{getvar}}, {{setvar}}
- [ ] Ne jamais exécuter automatiquement un script importé

## Étape 3 — Données locales (compléter)

- [ ] Implémenter PreferencesManager avec DataStore chiffré
- [ ] Implémenter KeyManager avec Android Keystore (AES-256-GCM)
- [ ] Connecter les managers (PersonaManager, CharacterManager) à Room au lieu de listes en mémoire

## Étape 4 — Personas et personnages

- [ ] CRUD complet connecté à Room
- [ ] Gestion des images/avatars
- [ ] Duplication et archivage de Personas
- [ ] Variables de roleplay {{user}} compatibles
- [ ] Aperçu du prompt injecté pour Persona

## Étape 5 — Chat et fournisseurs IA

- [ ] Connecter AiProviderManager aux écrans de chat
- [ ] Streaming de réponses réel (SSE pour OpenAI/Anthropic, etc.)
- [ ] Gestion des erreurs API (quota, rate limit, timeout)
- [ ] Prompt Inspector
- [ ] Test de connexion depuis les écrans Settings
- [ ] Profils distincts : Roleplay, Narration, Résumé, Traduction, Analyse

## Étape 8 — Mémoire

- [ ] Résumés de conversation, chapitre, arc
- [ ] Récolte de faits (événements, relations, préférences, secrets)
- [ ] Recherche contextuelle des souvenirs pertinents
- [ ] Packs de contexte configurables par type

## Étape 9 — Moteur RPG

- [ ] Statistiques, progression, montée de niveau
- [ ] Inventaire complet (armes, armures, potions, objets de quête)
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
- [ ] Vérification des imports (ZIP, scripts malveillants, traversées de chemins)

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
