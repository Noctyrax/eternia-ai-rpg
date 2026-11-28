package com.eternia.ai.rpg.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navType
import com.eternia.ai.rpg.character_cards.ui.CharacterDetailScreen
import com.eternia.ai.rpg.character_cards.ui.CharacterEditorScreen
import com.eternia.ai.rpg.character_cards.ui.CharacterImportScreen
import com.eternia.ai.rpg.character_cards.ui.CharacterListScreen
import com.eternia.ai.rpg.companions.ui.CompanionListScreen
import com.eternia.ai.rpg.core.ThemeState
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.import_export.ui.ImportExportScreen
import com.eternia.ai.rpg.lorebooks.ui.LorebookEditorScreen
import com.eternia.ai.rpg.lorebooks.ui.LorebookListScreen
import com.eternia.ai.rpg.media.ui.MediaListScreen
import com.eternia.ai.rpg.memory.ui.MemoryScreen
import com.eternia.ai.rpg.personas.ui.PersonaDetailScreen
import com.eternia.ai.rpg.personas.ui.PersonaEditorScreen
import com.eternia.ai.rpg.personas.ui.PersonaListScreen
import com.eternia.ai.rpg.personas.viewmodel.PersonaViewModel
import com.eternia.ai.rpg.reader.ui.NovelEditorScreen
import com.eternia.ai.rpg.reader.ui.NovelListScreen
import com.eternia.ai.rpg.reader.ui.NovelReaderScreen
import com.eternia.ai.rpg.roleplay.ui.ChatListScreen
import com.eternia.ai.rpg.roleplay.ui.ChatSessionScreen
import com.eternia.ai.rpg.roleplay.ui.GroupChatScreen
import com.eternia.ai.rpg.rpg_engine.ui.CombatScreen
import com.eternia.ai.rpg.rpg_engine.ui.CharacterSheetScreen
import com.eternia.ai.rpg.rpg_engine.ui.InventoryScreen
import com.eternia.ai.rpg.rpg_engine.ui.QuestsScreen
import com.eternia.ai.rpg.rpg_engine.ui.RpgDashboardScreen
import com.eternia.ai.rpg.rpg_engine.ui.SkillsScreen
import com.eternia.ai.rpg.settings.ui.SettingsScreen
import com.eternia.ai.rpg.visual_novel.ui.VisualNovelEditorScreen
import com.eternia.ai.rpg.visual_novel.ui.VisualNovelListScreen
import com.eternia.ai.rpg.visual_novel.ui.VisualNovelPlayerScreen
import com.eternia.ai.rpg.worlds.ui.WorldEditorScreen
import com.eternia.ai.rpg.worlds.ui.WorldListScreen
import com.eternia.ai.rpg.agent.ui.AgentScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = "home"
) {
    NavHost(navController, startDestination) {
        // Main navigation
        composable("home") {
            EterniaTheme {
                HomeScreen()
            }
        }

        composable("library") {
            EterniaTheme {
                LibraryScreen()
            }
        }

        composable("discover") {
            EterniaTheme {
                DiscoverScreen()
            }
        }

        composable("rpg") {
            EterniaTheme {
                RpgScreen()
            }
        }

        composable("settings") {
            EterniaTheme {
                SettingsScreen()
            }
        }

        composable("agent") {
            EterniaTheme {
                AgentScreen()
            }
        }

        composable("import-export") {
            EterniaTheme {
                ImportExportScreen()
            }
        }

        // Persona nested graph
        navigation(startDestination = "persona_list", route = "personas") {
            composable("persona_list") {
                EterniaTheme {
                    PersonaListScreen()
                }
            }
            composable(
                route = "persona_editor/{personaId?}",
                arguments = listOf(navArgument("personaId") { type = navType.StringType; nullable = true })
            ) { backStackEntry ->
                val personaId = backStackEntry.arguments?.getString("personaId")
                val viewModel = hiltViewModel<PersonaViewModel>()
                EterniaTheme {
                    PersonaEditorScreen(viewModel = viewModel, initialPersonaId = personaId)
                }
            }
            composable("persona_detail/{personaId}") { backStackEntry ->
                val personaId = backStackEntry.arguments?.getString("personaId") ?: ""
                EterniaTheme {
                    PersonaDetailScreen(personaId = personaId)
                }
            }
        }

        // Character nested graph
        navigation(startDestination = "character_list", route = "characters") {
            composable("character_list") {
                EterniaTheme {
                    CharacterListScreen()
                }
            }
            composable("character_editor/{characterId?}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId")
                EterniaTheme {
                    CharacterEditorScreen(initialCharacterId = characterId)
                }
            }
            composable("character_detail/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                EterniaTheme {
                    CharacterDetailScreen(characterId = characterId)
                }
            }
            composable("character_import") {
                EterniaTheme {
                    CharacterImportScreen()
                }
            }
        }

        // Chat/Roleplay nested graph
        navigation(startDestination = "chat_list", route = "chat") {
            composable("chat_list") {
                EterniaTheme {
                    ChatListScreen()
                }
            }
            composable(
                route = "chat_session/{conversationId}",
                arguments = listOf(navArgument("conversationId") { type = navType.StringType })
            ) { backStackEntry ->
                val conversationId = backStackEntry.arguments?.getString("conversationId") ?: ""
                EterniaTheme {
                    ChatSessionScreen(conversationId = conversationId)
                }
            }
            composable("group_chat/{conversationId}") { backStackEntry ->
                val conversationId = backStackEntry.arguments?.getString("conversationId") ?: ""
                EterniaTheme {
                    GroupChatScreen(conversationId = conversationId)
                }
            }
        }

        // World/Lorebook nested graph
        navigation(startDestination = "world_list", route = "worlds") {
            composable("world_list") {
                EterniaTheme {
                    WorldListScreen()
                }
            }
            composable("world_editor/{worldId?}") { backStackEntry ->
                val worldId = backStackEntry.arguments?.getString("worldId")
                EterniaTheme {
                    WorldEditorScreen(initialWorldId = worldId)
                }
            }
            composable("lorebook_list/{worldId?}") { backStackEntry ->
                val worldId = backStackEntry.arguments?.getString("worldId")
                EterniaTheme {
                    LorebookListScreen(worldId = worldId)
                }
            }
            composable("lorebook_editor/{lorebookId?}") { backStackEntry ->
                val lorebookId = backStackEntry.arguments?.getString("lorebookId")
                EterniaTheme {
                    LorebookEditorScreen(initialLorebookId = lorebookId)
                }
            }
        }

        // Reader nested graph
        navigation(startDestination = "novel_list", route = "reader") {
            composable("novel_list") {
                EterniaTheme {
                    NovelListScreen()
                }
            }
            composable("novel_reader/{novelId}") { backStackEntry ->
                val novelId = backStackEntry.arguments?.getString("novelId") ?: ""
                EterniaTheme {
                    NovelReaderScreen(novelId = novelId)
                }
            }
            composable("novel_editor/{novelId?}") { backStackEntry ->
                val novelId = backStackEntry.arguments?.getString("novelId")
                EterniaTheme {
                    NovelEditorScreen(initialNovelId = novelId)
                }
            }
        }

        // Visual Novel nested graph
        navigation(startDestination = "vn_list", route = "visual_novel") {
            composable("vn_list") {
                EterniaTheme {
                    VisualNovelListScreen()
                }
            }
            composable("vn_player/{vnId}") { backStackEntry ->
                val vnId = backStackEntry.arguments?.getString("vnId") ?: ""
                EterniaTheme {
                    VisualNovelPlayerScreen(vnId = vnId)
                }
            }
            composable("vn_editor/{vnId?}") { backStackEntry ->
                val vnId = backStackEntry.arguments?.getString("vnId")
                EterniaTheme {
                    VisualNovelEditorScreen(initialVnId = vnId)
                }
            }
        }

        // RPG nested graph
        navigation(startDestination = "rpg_dashboard", route = "rpg") {
            composable("rpg_dashboard") {
                EterniaTheme {
                    RpgDashboardScreen()
                }
            }
            composable("character_sheet/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                EterniaTheme {
                    CharacterSheetScreen(characterId = characterId)
                }
            }
            composable("inventory/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                EterniaTheme {
                    InventoryScreen(characterId = characterId)
                }
            }
            composable("skills/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                EterniaTheme {
                    SkillsScreen(characterId = characterId)
                }
            }
            composable("quests/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                EterniaTheme {
                    QuestsScreen(characterId = characterId)
                }
            }
            composable("combat/{combatId}") { backStackEntry ->
                val combatId = backStackEntry.arguments?.getString("combatId") ?: ""
                EterniaTheme {
                    CombatScreen(combatId = combatId)
                }
            }
        }
    }
}
