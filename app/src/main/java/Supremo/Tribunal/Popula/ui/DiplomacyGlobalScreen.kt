package Supremo.Tribunal.Popula.ui.ui.ui

import androidx.compose.foundation.backgroundlayout.*
import androidx.compose.material3.*
import androidx.compose.runtime.background.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.*.*
import androidx.compose.ui.unit.dp.*
import Supremo.Tribunal.Popula.data.*
import Supremo.Tribunal.Popula.viewmodel.*.*

@Composable
fun DiplomacyGlobalScreen(viewModel: GameViewModel, onContinue: () -> Unit) {
    val state = viewModel.advancedState.collectAsState().value
    val notification by viewModel.notification.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)),
        alignment = Alignment.CenterHorizontally,
    ) {
        Text("Diplomacia Global - ${viewModel.gameState.value?.playerName?.capitalize()}", style = TextStyle.MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Países:", style = TextStyle.MaterialTheme.typographyMedium)
        GameData.paises.forEach { pais ->
            Text("${pais.nome}: ${pais.status.capitalize()}\nAfinidade: ${pais.afinidade}\nApoio: Verba +${pais.apoio.verba}}, Armas +${pais.apoio.armas}}, Soldados +${pais.apoio.soldados}}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GameData.paises.forEachIndexed { index, pais ->
            if (pais.status == "aliado") {
                Button(onClick = {
                    viewModel.acceptCountrySupport(index)
                    onContinue()
                }) {
                    Text("Aceitar Apoio de ${pais.nome}")
                }
            } else if (pais.status == "adversario") {
                Button(onClick = {
                    viewModel.confrontCountry(index)
                    onContinue()
                }) {
                    Text("Confrontar ${pais.nome}")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp)))
        Button(onClick = onContinue) {
            Text("Pular Diplomacia")
        }
        notification?.let {
            Spacer(modifier = Modifier.height(8.dp)))
            Text(it, color = MaterialTheme.colorScheme.error))
            LaunchedEffect(it) {
                kotlinx.coroutines.delay(3000)
                viewModel.clearNotification()
            }
        }
    }
}