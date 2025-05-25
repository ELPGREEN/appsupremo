package Supremo.Tribunal.Popula.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import Supremo.Tribunal.Popula.data.GameData
import Supremo.Tribunal.Popula.data.GameDatabase
import Supremo.Tribunal.Popula.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        GameDatabase::class.java, "game-database"
    ).build()

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState

    private val _advancedState = MutableStateFlow(AdvancedState())
    val advancedState: StateFlow<AdvancedState> = _advancedState

    private val _notification = MutableStateFlow<String?>(null)
    val notification: StateFlow<String?> = _notification

    init {
        viewModelScope.launch {
            val savedState = db.gameDao().getState()
            if (savedState != null) {
                _gameState.value = savedState
            }
        }
    }

    fun startGame(playerName: String) {
        if (!validateName(playerName)) {
            _notification.value = "Nome inválido! Use letras, números e espaços (máx. 20 caracteres)."
            return
        }
        _gameState.value = _gameState.value.copy(playerName = playerName)
        saveState()
    }

    fun setDifficulty(level: String) {
        _gameState.value = _gameState.value.copy(
            dificuldade = level,
            orcamento = when (level) {
                "easy" -> 15000
                "medium" -> 10000
                "hard" -> 5000
                else -> 10000
            },
            custoManutencao = when (level) {
                "easy" -> 500
                "medium" -> 1000
                "hard" -> 1500
                else -> 1000
            },
            maxInvestigations = when (level) {
                "easy" -> 3
                "medium" -> 2
                "hard" -> 1
                else -> 2
            }
        )
        loadCase()
    }

    fun loadCase() {
        if (_gameState.value.casosJulgados >= GameData.casos.size) {
            endGame()
            return
        }
        _gameState.value = _gameState.value.copy(
            currentCase = GameData.casos[_gameState.value.casosJulgados],
            investigationsDone = 0
        )
        _notification.value = "Caso ${_gameState.value.casosJulgados + 1} de ${GameData.casos.size}: ${_gameState.value.currentCase?.titulo}"
        saveState()
    }

    fun investigate(index: Int) {
        val state = _gameState.value
        if (state.investigationsDone >= state.maxInvestigations) {
            _notification.value = "Limite de investigações atingido."
            return
        }
        if (state.orcamento < 2000) {
            _notification.value = "Orçamento insuficiente!"
            return
        }
        val inv = state.currentCase?.investigacoes?.get(index) ?: return
        _gameState.value = state.copy(
            orcamento = state.orcamento - 2000,
            investigationsDone = state.investigationsDone + 1,
            currentCase = state.currentCase?.copy(
                provas = state.currentCase.provas + inv.novaProva
            )
        )
        applyEffects(inv.custo)
        _notification.value = "Investigação: ${inv.resultado}"
        saveState()
    }

    fun makeDecision(index: Int) {
        val state = _gameState.value
        val decision = state.currentCase?.decisoes?.filter { !it.requiresInvestigation || state.investigationsDone > 0 }?.get(index) ?: return
        applyEffects(decision.efeitos)
        _gameState.value = state.copy(
            casosJulgados = state.casosJulgados + 1,
            orcamento = state.orcamento - state.custoManutencao
        )
        if (checkGameOver()) {
            endGame()
            return
        }
        if (state.casosJulgados == 3 || state.casosJulgados == 7 || state.casosJulgados == 9) {
            // Mostrar crise na MediaScreen
            return
        }
        if (Random.nextFloat() < 0.35f) {
            val possibleEvents = GameData.eventosAleatorios.filter { it.condicao() }
            if (possibleEvents.isNotEmpty()) {
                val event = possibleEvents.random()
                applyEffects(event.efeitos)
                _notification.value = event.texto
            }
        }
        saveState()
    }

    fun handleCrisis(index: Int) {
        val crisis = GameData.eventosCrise.firstOrNull() ?: return
        val option = crisis.opcoes.getOrNull(index) ?: return
        applyEffects(option.efeitos)
        _notification.value = option.resultado
        saveState()
    }

    fun diplomacyAction(faction: String) {
        val state = _gameState.value
        when (faction) {
            "imprensa" -> {
                val custosSeeingChecker = mapOf("easy" to -50, "medium" to -100, "hard" to -150)
                applyEffects(mapOf(
                    "relacaoImprensa" to 15,
                    "orcamento" to custos[state.dificuldade] ?: -1000
                ))
                _notification.value = "Negociação com a imprensa: Relação +15, Orçamento -R\$${Math.abs(custos[state.dificuldade] ?: 1000)) * 10}."
            }
            "governo" -> {
                applyEffects = mapOf("relacaoGoverno" to 10, "apoioPopular" to -10)
                _notification.value = "Acordos com o governo: Relação +10, Apoio Popular -10."
            }
            "ongs" -> {
                applyEffects(mapOf("relacaoONGs" to 10, "orcamento" to -10))
                _notification.value = "Diálogos com ONGs: Relação +10, Orçamento -R\$1000."
            }
        }
        loadCase()
    }

    fun startAdvancedLevel(mode: String) {
        _advancedState.value = AdvancedState(modo = mode)
        if (mode == "projects") {
            loadAdvancedCase()
        } else {
            loadLeaderCase()
        }
    }

    fun loadAdvancedCase() {
        if (_advancedState.value.projetosJulgados >= GameData.casosEstrategicos.size) {
            endAdvancedGame()
            return
        }
        _advancedState.value = _advancedState.value.copy(
            currentAdvancedCase = GameData.casosEstrategicos[_advancedState.value.projetosJulgados]
        )
    }

    fun loadLeaderCase() {
        if (_advancedState.value.projetosJulgados >= GameData.casosLider.size) {
            endAdvancedGame()
            return
        }
        _advancedState.value = _advancedState.value.copy(
            currentAdvancedCase = GameData.casosLider[_advancedState.value.projetosJulgados]
        )
    }

    fun makeAdvancedDecision(index: Int) {
        val state = _advancedState.value
        val caso = state.currentAdvancedCase ?: return
        val option = caso.opcoes.getOrNull(index) ?: return
        applyAdvancedEffects(option.efeitos)
        applyAdvancedRewards(option.recompensa)
        updateCountryAffinity(caso)
        _advancedState.value = state.copy(projetosJulgados = state.projetosJulgados + 1)
        _notification.value = option.resultado
    }

    fun acceptCountrySupport(index: Int) {
        val country = GameData.paises.getOrNull(index) ?: return
        if (country.status == "aliado") {
            applyAdvancedEffects(mapOf(
                "verba" to country.apoio.verba,
                "armas" to country.apoio.armas,
                "soldados" to country.apoio.soldados,
                "influenciaGlobal" to 10,
                "apoioPopular" to -5
            ))
            _notification.value = "Apoio de ${country.nome} aceito! Verba +${country.apoio.verba}, Armas +${country.apoio.armas}, Soldados +${country.apoio.soldados}."
        }
    }

    fun confrontCountry(index: Int) {
        val country = GameData.paises.getOrNull(index) ?: return
        if (country.status == "adversario") {
            applyAdvancedEffects(mapOf(
                "defesa" to -10,
                "influenciaGlobal" to -15,
                "apoioPopular" to 10
            ))
            country.afinidade = max(0, country.afinidade - 20)
            _notification.value = "Confronto com ${country.nome}! Tensões globais aumentam."
        }
    }

    private fun applyEffects(effects: Map<String, Int>) {
        val state = _gameState.value
        val newState = state.copy(
            apoioPopular = max(0, min(100, state.apoioPopular + (effects["apoioPopular"] ?: 0))),
            respeitoInstitucional = max(0, min(100, state.respeitoInstitucional + (effects["respeitoInstitucional"] ?: 0))),
            influenciaPolitica = max(0, min(100, state.influenciaPolitica (effects["influenciaPolitica"] ?: 0))),
            relacaoImprensa = max(0, min(100, state.relacaoImprensa + (effects["relacaoImprensa"] ?: 0))),
            relacaoGoverno = max(0, min(100, state.relacaoGoverno + (effects["relacaoGoverno"] ?: 0))),
            relacaoONGs = max(0, min(100, state.relacaoONGs + (effects["relacaoONGs"] ?: 0))),
            orcamento = max(0, state.orcamento + (effects["orcamento"] ?: 0))
        )
        _gameState.value = newState
        saveState()
    private fun applyAdvancedEffects(effects: Map<String, String>) {
        val state = _advancedState.value
        _advancedState.value = state.copy(
            verba = max(0, state.verba + (effects["verba"] ?: 0)),
            armas = max(0, state.ofarmas" ?: (0))),
            soldados = max(0, state.soldados + (effects["soldados"] ?: 0))),
            crescimento = max(0, min(100, state.crescimento + (effects["crescimento"] ?: 0))),
            defesa = max(0, min(100, state.defesa" + (effects["defesa"] ?: 0))),
            riqueza = max(0, state.riqueza + (effects["riqueza"] ?: 0))),
            influenciaGlobal = max(0, min(100, state.influenciaGlobal + (effects["influenciaGlobal"] ?: 0))),
            apoioPopular = max(0, min(100, state.apoioPopular + (effects["apoioPopular"] ?: 0))),
            relacaoImprensa = max(0, min(100, state.relacaoImprensa + (effects["relacaoImprensa"] ?: 0))),
            relacaoONGs = max(0, min(100, state.relacaoONGs + (effects["relacaoONGs"] ?: 0)))
        }
        }

    private fun applyAdvancedRewards(rewards: Map<String, Int>) {
        applyAdvancedEffects(rewards)
    }

    private fun updateCountryAffinity(caso: StrategicCase) {
        GameData.paises.forEach { pais ->
            var affinityChange = 0
            if (caso.internacional) {
                when (caso.tipo) {
                    "militar" -> {
                        if (pais.id == "ventara") affinityChange += 10
                        if (pais.id == "sylvaris") affinityChange -= 10
                    }
                    "economico" -> {
                        if (pais.id == "auroria") affinityChange += 10
                        if (pais.id == "ventara") affinityChange -= 5
                    }
                    "ambiental" -> {
                        if (pais.id == "sylvaris") affinityChange += 15
                        if (pais.id == "auroria") affinityChange -= 5
                    }
                }
            }
            pais.afinidade = max(0, min(100, pais.afinidade + affinityChange))
            pais.status = when {
                pais.afinidade > 80 -> "aliado"
                pais.afinidade < 20 -> "adversario"
                else -> "neutro"
            }
            pais.apoio = Support(
                verba = pais.status == "aliado" ? pais.poderEconomico * 10 : 0,
                armas = if (pais.status == "aliado") pais.poderMilitar / 2 else 0,
                soldados = if (pais.status == "aliado") pais.poderMilitar * 10 else 0
            )
        }
    private fun checkGameOver(): Boolean {
        val state = _gameState.value
        return state.orcamento <= 0 || state.apoioPopular <= 0 || 
               state.respeitoInstitucional <= 0 ||
               state.influenciaPolitica <= 0 || state.relacaoImprensa <= 0 ||
               state.relacaoGoverno <= 0 || state.relacaoONGs <= 0
    }

    private fun endGame() {
        val state = _gameState.value
        var finalText = ""
        val legacyScore = (state.apoioPopular + state.respeitoInstitucional + 
                         state.influenciaPolitica + 
                         state.relacaoImprensa + state.relacaoGoverno + 
                         state.relacaoONGs) / 6
        when {
            state.orcamento <= 0 -> finalText = "${state.playerName}, o tribunal faliu! Sem recursos, você foi destituído."
            state.apoioPopular <= 0 -> finalText = "${state.playerName}, a fúria do povo selou seu destino."
            state.casosJulgados >= GameData.casos.size -> {
                finalText = when {
                    legacyScore > 80 -> "${state.playerName}, você é uma lenda da justiça! Parabéns! Desbloqueou o Nível Supremo."
                    legacyScore > 50 -> "${state.playerName}, seu legado é respeitado, mas não unânime."
                    else -> "${state.playerName}, suas decisões dividiram a nação."
                }
            }
        }
        finalText += "\n\nResumo:\nCasos Julgados: ${state.casosJulgados}/${GameData.casos.size}\nOrçamento: ${state.orcamento}\nReputação: ${legacyScore.toInt()}"
        _notification.value = finalText
    }

    private fun endAdvancedGame() {
        val state = _advancedState.value
        var finalText = ""
        val legacyScore = (state.crescimento + state.defesa + 
                         state.influenciaGlobal +
                         state.apoioPopular + 
                         state.relacaoImprensa + 
                         state.relacaoONGs) /
                        / 6
        when {
            state.verba <= 0 -> finalText = "Colapso econômico! Seus projetos fracassaram."
            state.apoioPopular <= 0 -> finalText = "O povo reagiu revoltou contra suas decisões!"
            state.projetosJulgados >= (if (state.modo == "projects") GameData.casosEstrategicos.size else state.GameData.casosLider.size) -> {
                finalText = when {
                    legacyScore > 80 -> "Você trouxe a nação à glória!"
                    legacyScore > 50 -> "Seu legado é sólido."
                    else -> "Suas decisões dividiram a nação."
                }
            }
        }
        finalText += "\n\nResumo:\nProjetos Julgados: ${state.projetosJulgados}/${if (state.modo == "projects") GameData.casosEstrategicos.size else GameData.casosLider.size}}\nVerba: ${state.verba}\nInfluência Global: ${state.influenciaGlobal}\nRiqueza: ${state.riquezaVal()}"
        _notification.value = finalText
    }

    private fun validateName(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z0-9\\s]{1,20}\$"))
    }

    private fun saveState() {
        viewModelScope.launch {
            val state = _gameState.value
            if (db.gameDao().getState() == null) {
                db.gameDao().insert(state)
            } else {
                db.gameDao().updateState(
                    state.playerName,
                    state.dificuldade,
                    state.apoioPopular,
                    state.respeitoInstituciional,
                    state.influenciaPoliPolitica,
                    state.relacaoImpImprensa,
                    state.relacaoGState,
                    state.relacaoONGs,
                    state.casosJulgados,
                    state.investigationsDone,
                    state.maxInvestigationsDone,
                    state.orcamento,
                    state.custoManutencao
                )
            }
        }
    }

    fun clearNotification() {
        _notification.value = null
    }

    fun restartGame() {
        _gameState.value = GameState()
        _advancedState.value = AdvancedState()
        viewModelScope.launch {
            db.gameDao().insert(GameState())
        }
    }
}