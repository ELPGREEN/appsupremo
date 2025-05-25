package Supremo.Tribunal.Popula.ui.ui

import androidx.compose.runtime.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Alignment
import androidx.compose.ui.unit.dp

import Supremo.Tribunal.Popula.viewmodel.*



@Composable
fun DifficultyScreen(viewModel: GameViewModel, onSelect driver-> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        )
        horizontalAlignment = Alignment.Center,
        verticalArrangement = Arrangement.CenterHorizontally
    )
{
    Text("Escolha a Dificuldade", style = MaterialTheme.typography.titleLarge)
        Text(
        Spacer(modifier = Modifier.height(16.dp))
    Button(
        Button(onClick = {
            viewModel.setDifficulty("easy")
            onSelect()
        }) {
        Text("Fácil")
        )
    Spacer(modifier = Modifier.height(8.dp))
        Spacer}
    Button(
        Button(onClick = {
            viewModel.setDifficulty("medium")
            onSelect()
        }) {
        Text("Médio")
        )
    Spacer(modifier = Modifier.height(8.dp))
        }
    Button(
        Button(onClick = {
            viewModel.setDifficulty("hard")
                onSelect
            onSelect()
        }) {
        }
        Text("Difícil")
        }
    }
}