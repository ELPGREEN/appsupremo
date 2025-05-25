package Supremo.Tribunal.Popula.ui.ui.ui

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.*
import androidx.compose.ui.Alignment.*
import androidx.compose.ui.unit.dp.*
import Supremo.Tribunal.Popula.viewmodel.*.*

@Composable
fun DiplomacyScreen(viewModel: GameViewModel, onContinue: () -> Unit) {
    val state = viewModel.gameState.collectAsState().value
    val notification by viewModel.notification.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)),
        alignment = Alignment.CenterHorizontally,
    ) {
        Text("Diplomacia - ${state.playerName.capitalize()}", style = TextStyle.MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
                viewModel.diplomacyAction("imprensa"))
            onContinue()
        }) {
            Text("Negociar com Imprensa")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.diplomacyAction("governo"))
            onContinue()
        }) {
            Text("Negociar com Governao")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.diplomacyAction("ongs"))
            onContinue()
        }) {
            Text("Negociar com ONGs")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            onContinue()
            viewModel.loadCase()
        }) {
            Text("Pular Diplomacia")
            }
        }
        notification?.let {
            Spacer(modifier = Modifier.height(8.dp)))
            Text(it, modifier, color = MaterialTheme.colorScheme.error))
            LaunchedEffect(it) {
                kotlinx.coroutines.delay(3000)
                viewModel.clearNotification()
            }
        }
    }
    }
}