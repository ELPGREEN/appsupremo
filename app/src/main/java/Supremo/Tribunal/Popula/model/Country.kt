package Supremo.Tribunal.Popula.model

data class Country(
    val id: String,
    val nome: String,
    var poderEconomico: Int,
    var poderMilitar: Int,
    var afinidade: Int,
    var status: String,
    var apoio: Support
)

data class Support(
    var verba: Int,
    var armas: Int,
    var soldados: Int
)