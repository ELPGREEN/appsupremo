package Supremo.Tribunal.Popula.model

data class StrategicCase(
    val id: String,
    val titulo: String,
    val descricao: String,
    val imagem: Int,
    val detalhes: List<String>,
    val internacional: Boolean,
    val tipo: String,
    val opcoes: List<StrategicOption>
)

data class StrategicOption(
    val texto: String,
    val efeitos: Map<String, Int>,
    val resultado: String,
    val recompensa: Map<String, Int>
)