package Supremo.Tribunal.Popula.model

data class Case(
    val id: String,
    val titulo: String,
    val descricao: String,
    val imagem: Int,
    val provas: List<String>,
    val investigacoes: List<Investigation>,
    val decisoes: List<Decision>,
    val midia: List<String>
)

data class Investigation(
    val acao: String,
    val custo: Map<String, Int>,
    val resultado: String,
    val novaProva: String
)

data class Decision(
    val texto: String,
    val efeitos: Map<String, Int>,
    val manchete: String,
    val reacaoPopular: String,
    val reacaoMidia: String,
    val requiresInvestigation: Boolean = false
)