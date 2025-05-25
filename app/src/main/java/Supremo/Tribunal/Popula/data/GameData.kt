package Supremo.Tribunal.Popula.data

import Supremo.Tribunal.Popula.R
import Supremo.Tribunal.Popula.model.*

object GameData {
    val casos = listOf(
        
        Case(
            id = "caso_01",
            titulo = "O Roubo do Século na Fundação Esperança",
            descricao = "Brasília, 16 de março de 2024 – O deputado João Almeida, presidente da Fundação Esperança, é acusado de desviar R\$ 2,3 bilhões destinados a salvar vidas. Imagens mostram malas de dinheiro em seu escritório, enquanto protestos eclodem. A ONG Futuro Global defende Almeida, mas o povo exige justiça.",
            imagem = R.drawable.caso_01_malas_dinheiro,
            provas = listOf(
                "Vídeo clandestino mostra 15 malas de dinheiro no escritório de Almeida, com ele murmurando: \"Isso é só o começo.\"",
                "E-mails criptografados revelam transferências de R\$ 500 milhões para empresas de fachada.",
                "Ex-contador Pedro Costa entrega dossiê com contratos falsos assinados por Almeida."
            ),
            investigacoes = listOf(
                Investigation(
                    acao = "Contratar auditoria independente da PwC",
                    custo = mapOf("apoioPopular" to -5, "relacaoImprensa" to -5),
                    resultado = "A PwC revela: 62% dos fundos foram desviados para as Ilhas Cayman, com Almeida assinando as transações.",
                    novaProva = "Relatório da PwC com extratos bancários."
                ),
                Investigation(
                    acao = "Interrogar ex-contador sob juramento",
                    custo = mapOf("respeitoInstitucional" to -5, "relacaoONGs" to -5),
                    resultado = "Pedro Costa entrega vídeo de Almeida o ameaçando e contratos fraudulentos.",
                    novaProva = "Vídeo e arquivos com a trilha do dinheiro roubado."
                )
            ),
            decisoes = listOf(
                Decision(
                    texto = "Condenar Almeida com pena máxima de 15 anos",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to -20,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -15,
                        "relacaoONGs" to -10
                    ),
                    manchete = "Justiça Vinga o Povo! Almeida Apodrece na Cadeia!",
                    reacaoPopular = "Praças vibram: 'O ladrão caiu!' Fogos iluminam Brasília.",
                    reacaoMidia = "Globo Nacional: 'Um marco contra a impunidade!'"
                ),
                Decision(
                    texto = "Absolver Almeida por insuficiência de provas",
                    efeitos = mapOf(
                        "apoioPopular" to -20,
                        "respeitoInstitucional" to 15,
                        "influenciaPolitica" to 10,
                        "relacaoImprensa" to -15,
                        "relacaoGoverno" to 10,
                        "relacaoONGs" to 10
                    ),
                    manchete = "Vergonha Nacional! Tribunal Libera Almeida!",
                    reacaoPopular = "Caos: manifestantes gritam 'Justiça vendida!'",
                    reacaoMidia = "Jornal do Povo: 'O tribunal cuspiu no povo!'"
                ),
                Decision(
                    texto = "Adiar decisão e exigir nova auditoria",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Enrola! Caso Fica no Limbo!",
                    reacaoPopular = "Memes: 'Tribunal joga para debaixo do tapete!'",
                    reacaoMidia = "Voz do Povo: 'Adiar é proteger os poderosos!'"
                ),
                Decision(
                    texto = "Condenar Almeida com base nas novas provas",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -25,
                        "relacaoImprensa" to 15,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 0
                    ),
                    manchete = "Provas Esmagam Almeida! 12 Anos de Cadeia!",
                    reacaoPopular = "Brasil respira: 'Ninguém está acima da lei!'",
                    reacaoMidia = "Globo: 'Condenação pode causar crise política.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'Almeida roubou a esperança dos famintos!'",
                "Futuro Global: 'Acusações são uma farsa política!'",
                "Rede Social: 'Malas de dinheiro! #PrisãoParaAlmeida'",
                "TV Nacional: 'Escândalo: o povo exige justiça!'"
            )
        )
        Case(
        id = "caso_02",
        titulo = "Corrupção no Porto de Santos",
        descricao = "Santos, 10 de abril de 2024 – O governador de São Paulo é acusado de liderar um esquema de propinas no maior porto do Brasil. Evidências apontam para contêineres de carga usados para lavagem de dinheiro.",
        imagem = R.drawable.caso_02_porto, // Adicione imagem
        provas = listOf(
            "Fotos de contêineres com dinheiro escondido.",
            "Depoimento de um estivador sobre subornos."
        ),
        investigacoes = listOf(
            Investigation(
                acao = "Inspeção surpresa no porto",
                custo = mapOf("orcamento" to -1500, "relacaoGoverno" to -5),
                resultado = "Encontrados R$10 milhões em notas falsas.",
                novaProva = "Relatório de notas falsas."
            )
        ),
        decisoes = listOf(
            Decision(
                texto = "Prender o governador",
                efeitos = mapOf("apoioPopular" to 10, "relacaoGoverno" to -20),
                manchete = "Governador Preso! Porto em Choque!",
                reacaoPopular = "Aplausos nas ruas de Santos.",
                reacaoMidia = "Jornal Nacional: 'Justiça avança!'"
            )
        ),
        midia = listOf(
            "Folha de SP: 'Porto de Santos: ninho de corrupção?'",
            "Rede Social: '#PrisãoNoPorto'"
        )
    )
    // Adicione caso_03 a caso_10
)
    

    val eventosAleatorios = listOf(
        Event(
            id = "protestos",
            texto = "**Noite de Fúria e Cinzas: A Revolta que Paralisou a Capital**\n\nMilhares de pessoas tomaram as ruas, erguendo barricadas de pneus em chamas. Com a hashtag **#ForaJuiz**, manifestantes expressam indignação contra uma decisão judicial.",
            efeitos = mapOf("apoioPopular" to -10, "relacaoImprensa" to -5),
            condicao = { state -> state.relacaoImprensa < 25 || state.apoioPopular < 30 },
            imagem = R.drawable.protestos
        )
        // Adicione outros eventos aleatórios
    )

    val eventosCrise = listOf(
        CrisisEvent(
            id = "crise_judiciaria",
            texto = "**Crise Judicial: Greve Nacional de Magistrados**\n\nUma greve histórica paralisa o Judiciário, liderada por juízes que exigem melhores salários e condições. A população está dividida: alguns apoiam a causa, outros veem a paralisação como abandono do dever. Como Juiz Supremo, sua posição será crucial.\n\nA nação observa enquanto o tribunal, símbolo da justiça, enfrenta sua maior prova. Escolha com cuidado: sua decisão pode fortalecer ou destruir a confiança no sistema judicial.",
            imagem = R.drawable.greve_judiciaria,
            opcoes = listOf(
                CrisisOption(
                    texto = "Apoiar a greve e negociar com os juízes",
                    efeitos = mapOf("apoioPopular" to -10, "respeitoInstitucional" to 15, "relacaoGoverno" to -10),
                    resultado = "Os juízes encerram a greve após negociações tensas, mas o governo promete represálias, acusando você de fraqueza."
                ),
                CrisisOption(
                    texto = "Condenar a greve e exigir retorno imediato",
                    efeitos = mapOf("apoioPopular" to 10, "respeitoInstitucional" to -15, "relacaoGoverno" to 10),
                    resultado = "Os juízes retornam, mas a tensão no Judiciário aumenta. O governo elogia sua firmeza."
                )
                // Adicione outras opções
            )
        )
    )

    val paises = mutableListOf(
        Country(
            id = "auroria",
            nome = "Aurória",
            poderEconomico = 80,
            poderMilitar = 60,
            afinidade = 50,
            status = "neutro",
            apoio = Support(verba = 0, armas = 0, soldados = 0)
        ),
        Country(
            id = "ventara",
            nome = "Ventara",
            poderEconomico = 60,
            poderMilitar = 80,
            afinidade = 50,
            status = "neutro",
            apoio = Support(verba = 0, armas = 0, soldados = 0)
        ),
        Country(
            id = "sylvaris",
            nome = "Sylvaris",
            poderEconomico = 70,
            poderMilitar = 50,
            afinidade = 50,
            status = "neutro",
            apoio = Support(verba = 0, armas = 0, soldados = 0)
        )
    )

    val casosEstrategicos = listOf(
        StrategicCase(
            id = "estrategico_01",
            titulo = "Projeto Estrela: Satélite de Defesa",
            descricao = "Brasília, 1º de janeiro de 2026 – O Ministério da Defesa propõe o Projeto Estrela, um satélite de vigilância para monitorar ameaças externas. ONGs alertam sobre violações de privacidade, enquanto potências estrangeiras ameaçam sanções. Aprovar o projeto pode fortalecer a defesa, mas escalar tensões globais.",
            imagem = R.drawable.estrategico_01_satelite,
            detalhes = listOf(
                "Custo estimado: 500 unidades de verba.",
                "Benefício: Aumenta defesa nacional em 20 pontos.",
                "Risco: Sanções internacionais podem reduzir crescimento e influência global."
            ),
            internacional = true,
            tipo = "militar",
            opcoes = listOf(
                StrategicOption(
                    texto = "Aprovar o Projeto Estrela",
                    efeitos = mapOf(
                        "verba" to -500,
                        "defesa" to 20,
                        "crescimento" to -15,
                        "apoioPopular" to -10,
                        "relacaoONGs" to -15,
                        "influenciaGlobal" to -10
                    ),
                    resultado = "O satélite é lançado, fortalecendo a defesa, mas sanções reduzem o comércio. ONGs e países pacifistas protestam.",
                    recompensa = mapOf("riqueza" to 50000, "verba" to 200)
                ),
                StrategicOption(
                    texto = "Rejeitar o Projeto Estrela",
                    efeitos = mapOf(
                        "verba" to 0,
                        "defesa" to -5,
                        "crescimento" to 10,
                        "apoioPopular" to 5,
                        "relacaoONGs" to 10,
                        "influenciaGlobal" to 5
                    ),
                    resultado = "O projeto é cancelado, agradando ONGs e pacifistas, mas a defesa nacional enfraquece.",
                    recompensa = mapOf("riqueza" to 0, "verba" to 0)
                )
            )
        )
        // Adicione outros casos estratégicos
    )

    val casosLider = listOf(
        StrategicCase(
            id = "lider_01",
            titulo = "Reforma Educacional Nacional",
            descricao = "Brasília, 1º de fevereiro de 2026 – Como Líder Nacional, você propõe uma reforma educacional para universalizar o ensino de qualidade. Professores exigem mais verbas, enquanto o setor privado teme aumento de impostos. Aprovar pode atrair apoio de nações educacionais.",
            imagem = R.drawable.lider_01_educacao,
            detalhes = listOf(
                "Custo estimado: 600 unidades de verba.",
                "Benefício: Aumenta crescimento econômico em 20 pontos e influência global.",
                "Risco: Impostos podem reduzir apoio popular."
            ),
            internacional = true,
            tipo = "economico",
            opcoes = listOf(
                StrategicOption(
                    texto = "Aprovar a reforma com investimento total",
                    efeitos = mapOf(
                        "verba" to -600,
                        "crescimento" to 20,
                        "apoioPopular" to -10,
                        "relacaoImprensa" to 10,
                        "influenciaGlobal" to 15
                    ),
                    resultado = "A reforma eleva a educação, mas impostos geram protestos. Nações educacionais elogiam.",
                    recompensa = mapOf("riqueza" to 50000, "verba" to 200)
                ),
                StrategicOption(
                    texto = "Rejeitar a reforma",
                    efeitos = mapOf(
                        "verba" to 0,
                        "crescimento" to -5,
                        "apoioPopular" to 5,
                        "relacaoImprensa" to -5,
                        "influenciaGlobal" to -5
                    ),
                    resultado = "A reforma é cancelada, mantendo o apoio popular, mas a educação sofre.",
                    recompensa = mapOf("riqueza" to 0, "verba" to 0)
                )
            )
        )
        // Adicione outros casos de líder
    )
}