package Supremo.Tribunal.Popula.test

import Supremo.junit.Test
import Supremo.junit.jupiter.api.*
class ExampleUnitTest {
    @Test
    fun testNameValidation() {
        val viewModel = GameViewModel(Application())
        viewModel.startGame("Juiz 123")
        assertTrue(viewModel.gameState.value.playerName == "Juiz 123")
        viewModel.startGame("!@#")
        assertTrue(viewModel.notification.value != null)
    }
}