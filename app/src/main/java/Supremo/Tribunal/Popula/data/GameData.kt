package Supremo.Tribunal.Popula.data

import Supremo.Tribunal.Popula.R
import Supremo.Tribunal.Popula.model.*

object GameData {
    val casos = listOf(
        Case(
            id = "caso_01",
            titulo = "O Roubo do Século na Fundação Esperança",
            descricao = "Porto Alegre, 15 de março de 2024 – A Fundação Esperança, uma ONG que arrecada fundos para crianças carentes, foi saqueada. Evidências apontam para José Almeida, um político influente, como o responsável por desviar R$50 milhões. A mídia está em polvorosa, e a população exige justiça.",
            imagem = R.drawable.caso_01_malas_dinheiro,
            provas = listOf(
                "Registros bancários mostrando transferências para contas offshore ligadas a Almeida.",
                "Vídeo de segurança mostrando Almeida saindo do prédio com malas suspeitas."
            ),
            investigacoes = listOf(
                Investigation(
                    acao = "Contratar auditoria independente da PwC",
                    custo = mapOf("orcamento" to -2000, "relacaoGoverno" to -5),
                    resultado = "A auditoria confirma que R$50 milhões foram desviados em 3 meses.",
                    novaProva = "Relatório da PwC detalhando o esquema."
                ),
                Investigation(
                    acao = "Interrogar testemunhas-chave",
                    custo = mapOf("orcamento" to -500, "relacaoImprensa" to -5),
                    resultado = "Um ex-funcionário confessa ter ajudado Almeida a falsificar documentos.",
                    novaProva = "Depoimento assinado do ex-funcionário."
                )
            ),
            decisoes = listOf(
                Decision(
                    texto = "Condenar Almeida com pena máxima de 15 anos",
                    efeitos = mapOf("apoioPopular" to 15, "influenciaPolitica" to -20, "relacaoGoverno" to -10),
                    manchete = "Justiça Vinga o Povo! Almeida Apodrece na Cadeia!",
                    reacaoPopular = "Multidões celebram nas ruas de Porto Alegre.",
                    reacaoMidia = "Jornal Nacional: 'Um golpe contra a corrupção!'"
                ),
                Decision(
                    texto = "Absolver Almeida por falta de provas conclusivas",
                    efeitos = mapOf("apoioPopular" to -20, "influenciaPolitica" to 10, "relacaoGoverno" to 10),
                    manchete = "Escândalo! Almeida Livre Após Roubo Milionário!",
                    reacaoPopular = "Protestos explodem em frente ao tribunal.",
                    reacaoMidia = "Folha de SP: 'Justiça ou conivência?'"
                )
            ),
            midia = listOf(
                "O Globo: 'Escândalo na Fundação Esperança choca o Brasil!'",
                "Rede Social: '#JustiçaPorEsperança viraliza com 1M de posts.'"
            )
        ),
        Case(
            id = "caso_02",
            titulo = "Corrupção no Porto de Santos",
            descricao = "Santos, 10 de abril de 2024 – O governador de São Paulo é acusado de liderar um esquema de propinas no maior porto do Brasil. Evidências apontam para contêineres de carga usados para lavagem de dinheiro.",
            imagem = R.drawable.caso_02_protestos,
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
                ),
                Investigation(
                    acao = "Grampear telefones de suspeitos",
                    custo = mapOf("orcamento" to -1000, "relacaoImprensa" to -10),
                    resultado = "Gravações revelam conversas entre o governador e traficantes.",
                    novaProva = "Áudio das conversas grampeadas."
                )
            ),
            decisoes = listOf(
                Decision(
                    texto = "Prender o governador",
                    efeitos = mapOf("apoioPopular" to 10, "relacaoGoverno" to -20, "influenciaPolitica" to -15),
                    manchete = "Governador Preso! Porto em Choque!",
                    reacaoPopular = "Aplausos nas ruas de Santos.",
                    reacaoMidia = "Jornal Nacional: 'Justiça avança!'"
                ),
                Decision(
                    texto = "Arquivar o caso por pressão política",
                    efeitos = mapOf("apoioPopular" to -15, "relacaoGoverno" to 15, "influenciaPolitica" to 10),
                    manchete = "Vergonha! Corrupção no Porto Fica Impune!",
                    reacaoPopular = "Manifestações bloqueiam o porto.",
                    reacaoMidia = "Estadão: 'Um tapa na cara da justiça!'"
                )
            ),
            midia = listOf(
                "Folha de SP: 'Porto de Santos: ninho de corrupção?'",
                "Rede Social: '#PrisãoNoPorto'"
            )
        )
    )

    val eventosAleatorios = listOf(
        Event(
            id = "evento_01",
            titulo = "Protestos em Brasília",
            descricao = "Manifestantes tomam as ruas exigindo reformas no judiciário. Como você responde?",
            imagem = R.drawable.protestos,
            opcoes = listOf(
                Decision(
                    texto = "Apoiar as reformas",
                    efeitos = mapOf("apoioPopular" to 10, "relacaoGoverno" to -10),
                    manchete = "Juiz Supremo Apoia Reformas Populares!",
                    reacaoPopular = "Aplausos dos manifestantes.",
                    reacaoMidia = "O Globo: 'Um passo para a mudança?'"
                ),
                Decision(
                    texto = "Ignorar os protestos",
                    efeitos = mapOf("apoioPopular" to -10, "relacaoGoverno" to 5),
                    manchete = "Protestos Ignorados: Tensão Aumenta!",
                    reacaoPopular = "Manifestantes prometem escalar protestos.",
                    reacaoMidia = "Folha: 'Silêncio do Supremo irrita população.'"
                )
            )
        )
    )

    val eventosCrise = listOf(
        Event(
            id = "crise_01",
            titulo = "Greve Judicial",
            descricao = "Juízes entram em greve por melhores salários, paralisando tribunais. Como você lida com a crise?",
            imagem = R.drawable.greve_judiciaria,
            opcoes = listOf(
                Decision(
                    texto = "Apoiar a greve e negociar",
                    efeitos = mapOf("apoioPopular" to -10, "relacaoImprensa" to 5, "orcamento" to -2000),
                    manchete = "Supremo Negocia com Greve Judicial!",
                    reacaoPopular = "População critica gastos extras.",
                    reacaoMidia = "Estadão: 'Solução ou desperdício?'"
                ),
                Decision(
                    texto = "Rejeitar demandas e retomar julgamentos",
                    efeitos = mapOf("apoioPopular" to 5, "relacaoImprensa" to -10),
                    manchete = "Greve Judicial Reprimida!",
                    reacaoPopular = "Juízes prometem resistência.",
                    reacaoMidia = "O Globo: 'Tensão no judiciário!'"
                )
            )
        )
    )

    val casosEstrategicos = listOf(
        StrategicCase(
            id = "estrategico_01",
            titulo = "Projeto Estrela",
            descricao = "Um plano para lançar um satélite de vigilância nacional. Requer alto investimento, mas pode aumentar a segurança.",
            imagem = R.drawable.estrategico_01_satelite,
            decisoes = listOf(
                Decision(
                    texto = "Aprovar o Projeto Estrela",
                    efeitos = mapOf("orcamento" to -5000, "defesaNacional" to 20, "apoioPopular" to 5),
                    manchete = "Brasil Lança Satélite Estrela!",
                    reacaoPopular = "Orgulho nacional cresce.",
                    reacaoMidia = "Jornal Nacional: 'Um salto para a segurança!'"
                ),
                Decision(
                    texto = "Rejeitar o projeto",
                    efeitos = mapOf("orcamento" to 1000, "defesaNacional" to -10, "apoioPopular" to -5),
                    manchete = "Projeto Estrela Cancelado!",
                    reacaoPopular = "Críticas por falta de visão.",
                    reacaoMidia = "Folha: 'Brasil perde chance de avançar.'"
                )
            )
        )
    )

    val casosLider = listOf(
        Case(
            id = "lider_01",
            titulo = "Reforma Educacional",
            descricao = "Uma proposta para reformar o sistema educacional, liderada por um aliado político. Apoiar ou rejeitar?",
            imagem = R.drawable.lider_01_educacao,
            provas = listOf("Plano detalhado da reforma."),
            investigacoes = listOf(
                Investigation(
                    acao = "Analisar impacto financeiro",
                    custo = mapOf("orcamento" to -500),
                    resultado = "A reforma custará R$2 bilhões, mas pode melhorar a educação.",
                    novaProva = "Relatório financeiro da reforma."
                )
            ),
            decisoes = listOf(
                Decision(
                    texto = "Apoiar a reforma",
                    efeitos = mapOf("apoioPopular" to 10, "orcamento" to -2000, "educacao" to 15),
                    manchete = "Educação Ganha Novo Fôlego!",
                    reacaoPopular = "Pais e professores celebram.",
                    reacaoMidia = "O Globo: 'Investimento histórico!'"
                ),
                Decision(
                    texto = "Rejeitar a reforma",
                    efeitos = mapOf("apoioPopular" to -10, "orcamento" to 500),
                    manchete = "Reforma Educacional Rejeitada!",
                    reacaoPopular = "Protestos de estudantes.",
                    reacaoMidia = "Estadão: 'O futuro em risco?'"
                )
            ),
            midia = listOf("Rede Social: '#EducaçãoJá'")
        )
    )

    val paises = listOf(
        Country(
            nome = "Aurória",
            relacao = "aliado",
            imagem = R.drawable.diplomacy_global
        ),
        Country(
            nome = "Ventara",
            relacao = "neutro",
            imagem = R.drawable.diplomacy_background
        ),
        Country(
            nome = "Sylvaris",
            relacao = "hostil",
            imagem = R.drawable.mesa_negociacoes
        )
    )
}