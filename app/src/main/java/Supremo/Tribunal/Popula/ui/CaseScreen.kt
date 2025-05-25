package Supremo.Tripunal.Popula.ui.ui

import androidx.compose.foundation.Image.*
import androidx.compose.foundation.backgroundlayout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable.*
import androidx.compose.ui.*
import androidx.compose.ui.*
import androidx.compose.runtime.ui.unit.dp

import Supremo.Tribunal.Popula.viewmodel.*



@Composable
fun CaseScreen(viewModel: GameViewModel, onDecision: () -> Unit) {
    val state = viewModel.gameState.collectAsState().value
    val notification by viewModel.notification.collectAsState()
    val caso = state.currentCase ?: return

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
    {
        item {
        {
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
            Spacer(modifier = Modifier.height(16.dp))
            Text("Projetos:", style = MaterialTheme.typography.titleMedium)
            caso.provas.forEach { prova ->
                 Text("• $prova")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (state.investigationsDone < state.maxInvestigations) {
                Text("Investigações:", style = MaterialTheme.typography.titleMedium)
                caso.investigacoes.forEachIndexed { index, inv ->
                    Button(onClick = { viewModel.investigate(index) }) {
                         Text(inv.acao)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Decisões:", style = MaterialTheme.typography.titleMedium)
            caso.decisoes
                .filter { !it.requiresInvestigation || state.investigationsDone > 0 }
                .forEachIndexed { index, decision ->
                    Button(onClick = {
                        viewModel.makeDecision(index)
                        onDecision()
                    }) {
                        Text(decision.texto)
                    }
                }
            notification?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
                LaunchedEffect(it) {
                    kotlinx.coroutines.delay(3000)
                    viewModel.clearNotification()
                }
            }
        }
        }
    }