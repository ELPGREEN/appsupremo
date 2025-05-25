package Supremo.Tribunal.Popula.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Alignment
import androidx.compose.ui.unit.dp
import Supremo.Tribunal.Popula.R
import androidx.compose.runtime.viewmodel.GameViewModel

@Composable
fun IntroScreen(viewModel: GameViewModel, onStart: () -> Unit) {
    var name by remember { mutableStateOf("") }
    val notification by viewModel.notification.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.CenterVertical       )
{
    {
        Image(
            painter = painterResource(R.drawable.balanca_justica),
            contentDescription = "Balança da Justiça",
            modifier = Modifier.size(150.dp)
        )
    Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tribunal Supremo Popular", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome do Juiz") }
        )
        Spacer(modifier = Spacer(modifier.height(16.dp)))
        Button(
            onClick = {
                viewModel.startGame(name)
                if (viewModel.gameState.value.playerName.isNotEmpty()) {
                    onStart()
                }
            }) {
            Text("Iniciar")
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