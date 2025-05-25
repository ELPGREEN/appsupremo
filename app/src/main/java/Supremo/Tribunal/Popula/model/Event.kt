package Supremo.Tribunal.Popula.model

data class Event(
    val id: String,
    val texto: String,
    val efeitos: Map<String, Int>,
    val condicao: () -> Boolean = { true },
    val imagem: Int
)

data class CrisisEvent(
    val id: String,
    val texto: String,
    val imagem: Int,
    val opcoes: List<CrisisOption>
)

data class CrisisOption(
    val texto: String,
    val efeitos: Map<String, Int>,
    val resultado: String
)