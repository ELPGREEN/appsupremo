package Supremo.Tribunal.Popula.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import Supremo.Tribunal.Popula.navigation.*
import Supremo.Tribunal.Popula.ui.theme.*
import Supremo.Tribunal.Popula.viewmodel.*

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                NavGraph(viewModel)
            }
        }
    }
}