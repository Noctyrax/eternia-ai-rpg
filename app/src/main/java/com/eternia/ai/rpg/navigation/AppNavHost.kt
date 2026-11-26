package com.eternia.ai.rpg.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.nestedGraph
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.personas.PersonaViewModel
import com.eternia.ai.rpg.ui.screens.*

@Composable
fun AppNavHost(
    navController: androidx.navigation.NavHostController,
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

        // Persona nested graph
        navigation(startDestination = "persona_list", route = "personas") {
            composable("persona_list") {
                EterniaTheme {
                    PersonaListScreen()
                }
            }
            composable(
                route = "persona_editor/{personaId?}",
                arguments = listOf(androidx.navigation.navArgument("personaId") { type = androidx.navigation.NavType.StringType; nullable = true })
            ) { backStackEntry ->
                val personaId = backStackEntry.getString()?.getString("personaId")
                val viewModel = hiltViewModel<PersonaViewModel>()
                EterniaTheme {
                    PersonaEditorScreen(viewModel = viewModel, initialPersonaId = personaId)
                }
            }
            composable("persona_detail/{personaId}") { backStackEntry ->
                val personaId = backStackEntry.getString()?.getString("personaId") ?: ""
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
                val characterId = backStackEntry.getString()?.getString("characterId")
                EterniaTheme {
                    CharacterEditorScreen(initialCharacterId = characterId)
                }
            }
            composable("character_detail/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.getString()?.getString("characterId") ?: ""
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
                arguments = listOf(androidx.navigation.navArgument("conversationId") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val conversationId = backStackEntry.getString()?.getString("conversationId") ?: ""
                EterniaTheme {
                    ChatSessionScreen(conversationId = conversationId)
                }
            }
            composable("group_chat/{conversationId}") { backStackEntry ->
                val conversationId = backStackEntry.getString()?.getString("conversationId") ?: ""
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
                val worldId = backStackEntry.getString()?.getString("worldId")
                EterniaTheme {
                    WorldEditorScreen(initialWorldId = worldId)
                }
            }
            composable("lorebook_list/{worldId?}") { backStackEntry ->
                val worldId = backStackEntry.getString()?.getString("worldId")
                EterniaTheme {
                    LorebookListScreen(worldId = worldId)
                }
            }
            composable("lorebook_editor/{lorebookId?}") { backStackEntry ->
                val lorebookId = backStackEntry.getString()?.getString("lorebookId")
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
                val novelId = backStackEntry.getString()?.getString("novelId") ?: ""
                EterniaTheme {
                    NovelReaderScreen(novelId = novelId)
                }
            }
            composable("novel_editor/{novelId?}") { backStackEntry ->
                val novelId = backStackEntry.getString()?.getString("novelId")
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
                val vnId = backStackEntry.getString()?.getString("vnId") ?: ""
                EterniaTheme {
                    VisualNovelPlayerScreen(vnId = vnId)
                }
            }
            composable("vn_editor/{vnId?}") { backStackEntry ->
                val vnId = backStackEntry.getString()?.getString("vnId")
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
                val characterId = backStackEntry.getString()?.getString("characterId") ?: ""
                EterniaTheme {
                    CharacterSheetScreen(characterId = characterId)
                }
            }
            composable("inventory/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.getString()?.getString("characterId") ?: ""
                EterniaTheme {
                    InventoryScreen(characterId = characterId)
                }
            }
            composable("skills/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.getString()?.getString("characterId") ?: ""
                EterniaTheme {
                    SkillsScreen(characterId = characterId)
                }
            }
            composable("quests/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.getString()?.getString("characterId") ?: ""
                EterniaTheme {
                    QuestsScreen(characterId = characterId)
                }
            }
            composable("combat/{combatId}") { backStackEntry ->
                val combatId = backStackEntry.getString()?.getString("combatId") ?: ""
                EterniaTheme {
                    CombatScreen(combatId = combatId)
                }
            }
        }
    }
}