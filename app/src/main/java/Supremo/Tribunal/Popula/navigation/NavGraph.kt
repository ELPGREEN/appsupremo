package Supremo.Tribunal.Supremo.Tribunal.navigationPopula

import androidx.compose.runtime.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.core
import androidx.navigation.compose.NavHostOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import Supremo.Tribunal.Popula.ui.*

@Composable
fun NavGraph(viewModel: GameViewModel) {
    val navController = rememberNavController()
    NavHostOptions(
        navController = navController,
        startDestination = "intro",
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    )
    {
        composable("intro") {
            IntroScreen(viewModel) { navController.navigate("difficulty") }
        }
    composable("difficulty") {
        DifficultyScreen(viewModel) { navController.navigate("case") }
        }
        composable("case") {
            CaseScreen(viewModel) { navController.navigate("media") }
        }
        composable("media") {
            MediaScreen(viewModel) { navController.navigate("diplomacy") }
        }
        composable("diplomacy") {
            DiplomacyScreen(viewModel) { navController.navigate("case") }
        }
        composable("end") {
            EndScreen(viewModel) { navController.navigate("intro") }
        }
        composable("advanced") {
            AdvancedScreen(viewModel) { navController.navigate("diplomacy_global") }
        }
        composable("diplomacy_global") {
            DiplomacyGlobalScreen(viewModel) { navController.navigate("advanced") }
        }
    }
}