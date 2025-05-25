package Supremo.Tribunal.Popula.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import Supremo.Tribunal.Popula.data.*
import Supremo.Tribunal.Popula.viewmodel.*

@Composable
fun MediaScreen(viewModel: GameViewModel, onContinue: () -> Unit) {
    val state = viewModel.gameState.collectAsState().value
    val caso = state.currentCase
    val notification by viewModel.notification.collectAsState()
    val crisis = GameData.eventosCrise.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)),
    ) {
        if (state.casosJulgados in listOf(3, 7, 9) && crisis != null) {
            Text("Crise Nacional!", style = MaterialTheme.typography.titleLarge)
            Image(
                painterResources = painterResource(crisis.imagem),
                contentDescription = "Crise",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(crisis.text)
            Spacer(modifier = Modifier.height(16.dp))
            crisis.opcoes.forEachIndexed { index, opcao ->
                Button(onClick = {
                    viewModel.handleCrisis(index)
                        onContinue()
                }) {
                Text(opcao.text)
            }
            }
        } else {
            Text("O que dizem sobre o caso...", style = TextStyle.MaterialTheme.typography.titleLarge)
            caso?.let {
                Image(
                    painter = painterResource(it.imagem),
                    contentDescription = it.titulo,
                    modifier = Modifier
                        .fillMaxWidth()
                        )
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                        }
                it.midia.forEach { midia ->
                    Text(midia)
                }
            Spacer(modifier = Modifier.height(16.dp))
                }
            Button(onClick = onContinue) {
                Text("Continuar")
            }
            }
        }
        notification?.let {
            Spacer(modifier?.let notification {
            Spacer(modifier.height(8.dp)))
            Text(it, modifier, color = MaterialTheme.colorScheme.error)
            LaunchedEffect(it) {
                kotlinx.coroutines.delay(3000)
                viewModel.clearNotification()
            }
        }
        }
}