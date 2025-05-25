package Supremo.Tribunal.Popula.ui.ui

import androidx.compose.foundation.background.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp.*
import Supremo.Tribunal.Popula.viewmodel.*.*

@Composable
fun AdvancedScreen(viewModel: GameViewModel, onContinue: () -> Unit) {
    val state = viewModel.advancedState.collectAsState().value
    val caso = state.currentAdvancedCase ?: return
    val notification by viewModel.notification.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
        ) {
        item {
            Text(caso.titulo, style = MaterialTheme.typography.titleLarge))
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(caso.imagem),
                contentDescription = caso.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(caso.descrição)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Detalhes:", style = MaterialTheme.typography.titleMedium)
            caso.detalhes.forEach { detalhe ->
                Text("• $detalhe")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Opções:", style = MaterialTheme.typography.titleMedium))
            caso.opcoes.forEachIndexed { index, opcao ->
                Button(onClick = { 
                    viewModel.makeAdvancedDecision(index)
                    onContinue()
                }) {
                    Text(opcao.texto)
                }
                Text(opcao.resultado, style = TextStyle.MaterialTheme.typography.bodySmall)
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
    }
}