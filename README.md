# ETERNIA AI RPG

> **Une application Android complète de roleplay IA, RPG isekai et fiction interactive**

ETERNIA AI RPG est une application Android native qui combine les possibilités de roleplay et de gestion de personnages, la personnalisation avancée, les systèmes de progression RPG et les choix narratifs des Visual Novels. Tout cela gratuitement, sans compte obligatoire, avec vos propres clés API.

## Fonctionnalités Principales

### Roleplay & Personnages
- **Personas** : Créez et gérez vos personnages joueurs (ce que vous incarnez)
- **Personnages IA** : Créez, modifiez, importez/exportez des personnages contrôlés par l'IA
- **Conversations persistantes** : Discussions longues avec streaming en temps réel
- **Groupes de personnages** : Conversations multi-personnages avec ordre de parole configurable

### Formats Supportés
- **Character Card v1/v2/v3** (format standard)
- **SillyTavern** (import/export complet avec PNG)
- **PNG avec métadonnées** (chara cards embarquées)
- **JSON/JSONL** pour conversations et lorebooks

### Moteur RPG Complet
- **Stats** : Force, Dextérité, Constitution, Intelligence, Sagesse, Charisme, Chance
- **Santé/Mana/Endurance** avec régénération
- **Inventaire** : Objets, équipement, rareté (Commun → Divin), durabilité
- **Compétences** : Actives/passives, arbres de compétences, cooldowns
- **Quêtes** : Principales, secondaires, quotidiennes, objectifs multiples
- **Combat** : Jets d'attaque/défense, coups critiques, types de dégâts
- **NPCs** : Marchands, donneurs de quêtes, factions, réputation
- **Monde** : Localisations, découverte, voyages

### Fiction Interactive
- **Lecteur de romans** : TXT, EPUB, Markdown avec signets, TOC, recherche
- **Continuation IA** : Continuer, réécrire, développer, résumer, traduire
- **Branches narratives** : Versions multiples sans écraser l'original
- **Mode Visual Novel** : Arrière-plans, sprites, expressions, choix, variables, fins multiples

### Mémoire & Contexte
- **Résumés automatiques** : Par session, chapitre, arc, complet
- **Récolte de faits** : Événements, relations, préférences, secrets
- **Recherche sémantique** : Embeddings vectoriels (préparé)
- **Packs de contexte** : Configurables par type (roleplay, narration, RPG, etc.)

### Fournisseurs IA Multiples
- **Google Gemini** (texte, images, audio)
- **DeepSeek** (chat, code)
- **OpenAI** (GPT-4o, GPT-4, DALL-E, TTS, Whisper)
- **Anthropic** (Claude 3.5 Sonnet/Opus/Haiku)
- **Mistral** (chat, embeddings)
- **OpenRouter** (accès à 100+ modèles)
- **Groq** (inférence ultra-rapide)
- **Endpoints personnalisés** (API OpenAI-compatible)

### Multimédia
- **Génération d'images** : Portraits, scènes, objets
- **Synthèse vocale (TTS)** : Multiples voix
- **Reconnaissance vocale (STT)** : Transcription audio
- **Pièces jointes** : Images, documents, audio dans le chat

### Import/Export & Sauvegarde
- **Archives ZIP versionnées** : Tout exporter en une fois
- **Import SillyTavern** : Personnages, lorebooks, conversations, mondes
- **Sauvegardes automatiques** : Configurables (quotidien, hebdo)
- **Restauration complète** : Avec vérification d'intégrité

### Sécurité & Confidentialité
- **Stockage 100% local** : Room Database + DataStore chiffré
- **Clés API** : Android Keystore (AES-256-GCM)
- **Aucune télémétrie** : Rien ne sort sans votre consentement
- **Suppression complète** : Données effaçables à tout moment
- **Import sécurisé** : Validation, limites de taille, sandbox

## Architecture Technique

### Stack
- **Kotlin** + **Jetpack Compose** (Material 3)
- **Architecture modulaire** : 20+ modules indépendants
- **Room** : Base de données locale avec migrations
- **DataStore** : Préférences chiffrées
- **Hilt** : Injection de dépendances
- **Coroutines/Flow** : Asynchrone réactif
- **Navigation Compose** : Navigation type-safe

### Modules
```
app                 # Application principale, navigation, DI
core                # Utilitaires, constantes, extensions, Result<T>
design-system       # Thème, composants, Material 3 personnalisé
database            # Room, entités, DAOs, migrations
domain              # Interfaces repository, use cases, modèles
ai-providers        # Abstraction providers, Gemini, DeepSeek, OpenAI, Anthropic, OpenRouter
personas            # Gestion des Personas
character-cards     # Character Card v1/v2/v3, SillyTavern, PNG
lorebooks           # Lorebooks, entrées, scanner contextuel
memory              # Résumés, recollections, packs de contexte
rpg-engine          # Stats, inventaire, compétences, quêtes, combat, monde
roleplay            # Sessions chat, streaming, prompt builder
reader              # TXT/EPUB/MD, continuation IA, branches
visual-novel        # Scènes, choix, sprites, variables, fins
companions          # Groupes, relations, messages proactifs
media               # Images, TTS, STT, albums
import-export       # ZIP, SillyTavern, sauvegardes, migrations
settings            # DataStore, KeyManager (Keystore), presets, templates
agent               # Assistant IA pour la bibliothèque
testing             # Fakes, utilitaires de test
```

## Installation

### Prérequis
- Android 8.0 (API 26) ou supérieur
- ~200 MB d'espace de stockage
- Connexion Internet pour les fournisseurs IA (optionnel pour le local)

### Depuis GitHub Releases
1. Téléchargez le dernier `app-release.apk` depuis [Releases](https://github.com/your-repo/releases)
2. Activez "Installer des applications inconnues" pour votre navigateur/gestionnaire de fichiers
3. Ouvrez l'APK pour installer

### Compilation depuis les sources
```bash
# Cloner le repo
git clone https://github.com/your-repo/eternia-ai-rpg.git
cd eternia-ai-rpg

# Compiler (nécessite Android SDK 34 + JDK 17)
./gradlew assembleDebug    # APK debug
./gradlew assembleRelease  # APK release (nécessite keystore)

# Tests
./gradlew test             # Tests unitaires
./gradlew connectedAndroidTest  # Tests instrumentés
```

## Configuration des API

1. Ouvrez **Paramètres** → **Fournisseurs IA**
2. Ajoutez un fournisseur (Gemini, DeepSeek, OpenAI, etc.)
3. Entrez votre clé API (stockée chiffrée dans Android Keystore)
4. Sélectionnez le modèle par défaut pour chaque profil (roleplay, narration, etc.)
5. Testez la connexion

### Modèles Gratuits Recommandés
- **Gemini 1.5 Flash** (gratuit, 1M tokens/min)
- **DeepSeek Chat** (gratuit, haute qualité)
- **OpenRouter** (accès à modèles gratuits : Llama, Nemotron, etc.)
- **Groq** (Llama 3 ultra-rapide, gratuit)

## Guide d'Importation SillyTavern

### Personnages
1. **Paramètres** → **Import/Export** → **Importer**
2. Sélectionnez fichiers `.json`, `.png`, ou dossier
3. Choisissez le format : SillyTavern, Character Card v3, etc.
4. Vérifiez le rapport d'import (créés, mis à jour, ignorés, erreurs)

### Lorebooks
- Format `.json` SillyTavern supporté
- Entrées avec clés, priorités, ordre d'insertion
- Scan récursif configurable

### Conversations
- Format JSONL (une ligne par message)
- Messages avec rôle, contenu, métadonnées
- Branches narratives préservées

## Sauvegarde & Restauration

### Sauvegarde Complète
1. **Paramètres** → **Sauvegarde** → **Créer une sauvegarde**
2. Choisissez : Complète / Incrémentale / Schéma seul
3. Optionnel : Chiffrement AES-256
4. Sauvegardé dans `Documents/EterniaBackups/`

### Restauration
1. **Paramètres** → **Sauvegarde** → **Restaurer**
2. Sélectionnez le fichier de sauvegarde
3. Choisissez : Écraser / Fusionner / Sélectif
5. Vérification d'intégrité automatique

## Structure des Données

### Entités Principales
- **Persona** : Votre avatar dans l'histoire
- **Character** : PNJ/IA avec personnalité complète
- **Conversation** : Session de chat/aventure
- **Message** : Tour de parole (user/assistant/system/tool)
- **World** : Univers avec géographie, histoire, factions
- **Lorebook** : Entrées contextuelles (clés → contenu)
- **Novel** : Roman avec chapitres et branches
- **VisualNovel** : Scènes, choix, variables, fins

### RPG
- **RpgCharacter** : Stats, niveau, XP, ressources
- **InventoryItem** : Objets, équipement, consommables
- **Skill** : Compétences actives/passives
- **Quest** : Quêtes avec objectifs et récompenses
- **Combat** : Logs de combat, tours, dégâts

## Développement

### Structure du Projet
```
eternia-ai-rpg/
├── app/                    # Application principale
├── core/                   # Utilitaires partagés
├── design-system/          # Thème Material 3 + composants
├── database/               # Room Database
├── domain/                 # Interfaces & Use Cases
├── ai-providers/           # Providers IA
├── personas/               # Gestion Personas
├── character-cards/        # Character Cards
├── lorebooks/              # Lorebooks
├── memory/                 # Mémoire & Contexte
├── rpg-engine/             # Moteur RPG
├── roleplay/               # Chat & Roleplay
├── reader/                 # Lecteur de romans
├── visual-novel/           # Visual Novel Engine
├── companions/             # Compagnons & Groupes
├── media/                  # Images, Audio, TTS/STT
├── import-export/          # Import/Export
├── settings/               # Paramètres & Keystore
├── agent/                  # Assistant IA
├── testing/                # Tests & Fakes
├── .github/workflows/      # CI/CD
└── docs/                   # Documentation
```

### Commandes Utiles
```bash
# Lint & Analyse statique
./gradlew detekt
./gradlew lint

# Tests
./gradlew test
./gradlew connectedAndroidTest

# Build
./gradlew assembleDebug
./gradlew assembleRelease

# Dépendances
./gradlew dependencyCheckAnalyze
./gradlew dependencies

# Nettoyage
./gradlew clean
```

## Licence

MIT License - Voir [LICENSE](LICENSE) pour plus de détails.

## Contribution

Les contributions sont les bienvenues ! Voir [CONTRIBUTING.md](CONTRIBUTING.md) pour les guidelines.

## Remerciements

- **Jetpack Compose** pour l'UI moderne
- **Room** pour la base de données robuste
- **Hilt** pour l'injection de dépendances
- **Coil** pour le chargement d'images
- **OkHttp/Retrofit/Moshi** pour le réseau
- **Arrow** pour la programmation fonctionnelle
- **SillyTavern** pour l'inspiration des formats
- **Toutes les bibliothèques open-source** utilisées