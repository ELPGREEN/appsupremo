package Supremo.Tribunal.Popula.ui.components

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*

@Composable
fun SnackbarHost(notification: String?) {
    val snackbarHostState = remember { SnackbarHostState() }

    notification?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnack(it)
        }
    }

    SnackHost(hostState = snackbarHostState)
}