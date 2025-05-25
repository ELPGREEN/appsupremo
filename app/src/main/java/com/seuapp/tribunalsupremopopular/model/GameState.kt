package com.seuapp.tribunalsupremopopular.model

import android.view.View

data class GameState(
    var playerName: String = "",
    var dificuldade: String = "",
    var apoioPopular: Int = 50,
    var respeitoInstitucional: Int = 50,
    var influenciaPolitica: Int = 50,
    var relacaoImprensa: Int = 50,
    var relacaoGoverno: Int = 50,
    var relacaoONGs: Int = 50,
    var casosJulgados: Int = 0,
    var currentCase: Case? = null,
    var investigationsDone: Int = 0,
    var maxInvestigations: Int = 2,
    var orcamento: Int = 10000,
    var custoManutencao: Int = 1000
)

data class EventoAleatorio(
    val id: String,
    val texto: String,
    val efeitos: Map<String, Int>,
    val condicao: (() -> Boolean)? = null,
    val imagem: String
)

data class EventoCrise(
    val id: String,
    val texto: String,
    val imagem: String,
    val opcoes: List<OpcaoCrise>
)

data class OpcaoCrise(
    val texto: String,
    val efeitos: Map<String, Int>,
    val resultado: String
)

data class Case(
    val id: String,
    val titulo: String,
    val descricao: String,
    val imagem: String,
    val provas: MutableList<String>,
    val investigacoes: List<Investigacao>,
    val decisoes: List<Decisao>,
    val midia: List<String>
)

data class Investigacao(
    val acao: String,
    val custo: Map<String, Int>,
    val resultado: String,
    val novaProva: String
)

data class Decisao(
    val texto: String,
    val efeitos: Map<String, Int>,
    val manchete: String,
    val reacaoPopular: String,
    val reacaoMidia: String,
    val requiresInvestigation: Boolean = false
)
fun View.pulse() {
    animate().scaleX(1.1f).scaleY(1.1f).setDuration(500).withEndAction {
        animate().scaleX(1f).scaleY(1f).setDuration(500).start()
    }.start()
}