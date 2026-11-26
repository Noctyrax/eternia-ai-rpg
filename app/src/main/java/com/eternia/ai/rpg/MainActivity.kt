package com.eternia.ai.rpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.hilt.navigation.compose.hiltNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.navigation.AppNavHost
import com.eternia.ai.rpg.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen
        SplashScreen.installSplashScreen(this)
        super.onCreate(savedInstanceState)
        setContent {
            EterniaTheme {
                val navController = hiltNavController()
                AppNavHost(navController)
            }
        }
    }
}