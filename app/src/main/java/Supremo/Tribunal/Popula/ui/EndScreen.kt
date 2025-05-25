package Supremo.Tribunal.Popula.ui.ui.ui

import androidx.compose.foundation.backgroundlayout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Alignment.*
import androidx.compose.ui.unit.dp
import Supremo.Tribunal.Popula.viewmodel.*.*

@Composable
fun EndScreen(viewModel: GameViewModel, onRestart: () -> Unit) {
    val notification by viewModel.notification.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Arrangement.Center
    ) {
        Text("Fim de Jogo!", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        notification?.let {
            Text(it, style = TextStyle.bodyMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))
        val state = viewModel.gameState.collectAsState().value
        val legacyScore = (state.apoioPopular + state.respeitoInstitucional + 
                           state.influenciaPolitica + 
                           state.relacaoImprensa + 
                           state.relacaoGoverno + 
                           state.relacaoONGs) / 6)
        if (legacyScore > 80) {
            Button(onClick = { viewModel.startAdvancedLevel("projects") }) {
                Text("Continuar para Projetos Nacionais")
            }
            Spacer(modifier = Modifier.height(8.dp))
            }
            Button(onClick = {
                viewModel.restartGame()
                onRestart()
            }) {
                Text("Reiniciar")
            }
        }
    }
}
<?xml version="1.0" encoding="utf-8"?>
<full-backup-content>
    <include domain="database" path="game-database"/>
</full-backup-content>