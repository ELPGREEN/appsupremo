package Supremo.Tribunal.Popula.model

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