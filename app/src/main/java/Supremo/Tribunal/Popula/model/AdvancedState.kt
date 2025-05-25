package Supremo.Tribunal.Popula.model

data class AdvancedState(
    var verba: Int = 1000,
    var armas: Int = 100,
    var soldados: Int = 1000,
    var crescimento: Int = 50,
    var defesa: Int = 50,
    var riqueza: Int = 0,
    var influenciaGlobal: Int = 50,
    var apoioPopular: Int = 50,
    var relacaoImprensa: Int = 50,
    var relacaoONGs: Int = 50,
    var projetosJulgados: Int = 0,
    var currentAdvancedCase: StrategicCase? = null,
    var modo: String? = null
)