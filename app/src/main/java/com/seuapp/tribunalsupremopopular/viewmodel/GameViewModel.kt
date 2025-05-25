package com.seuapp.tribunalsupremopopular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seuapp.tribunalsupremopopular.model.*
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _state = MutableLiveData(GameState())
    val state: LiveData<GameState> get() = _state

    private val _notification = MutableLiveData<String>()
    val notification: LiveData<String> get() = _notification

    private val casos = listOf(
        Case(
            id = "caso_01",
            titulo = "O Roubo do Século na Fundação Esperança",
            descricao = "Brasília, 16 de março de 2024 – O deputado João Almeida, presidente da Fundação Esperança, é acusado de desviar R$ 2,3 bilhões destinados a salvar vidas. Imagens mostram malas de dinheiro em seu escritório, enquanto protestos eclodem. A ONG Futuro Global defende Almeida, mas o povo exige justiça.",
            imagem = "caso_01_malas_dinheiro",
            provas = mutableListOf(
                "Vídeo clandestino mostra 15 malas de dinheiro no escritório de Almeida, com ele murmurando: \"Isso é só o começo.\"",
                "E-mails criptografados revelam transferências de R$ 500 milhões para empresas de fachada.",
                "Ex-contador Pedro Costa entrega dossiê com contratos falsos assinados por Almeida."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Contratar auditoria independente da PwC",
                    custo = mapOf("apoioPopular" to -5, "relacaoImprensa" to -5),
                    resultado = "A PwC revela: 62% dos fundos foram desviados para as Ilhas Cayman, com Almeida assinando as transações.",
                    novaProva = "Relatório da PwC com extratos bancários."
                ),
                Investigacao(
                    acao = "Interrogar ex-contador sob juramento",
                    custo = mapOf("respeitoInstitucional" to -5, "relacaoONGs" to -5),
                    resultado = "Pedro Costa entrega vídeo de Almeida o ameaçando e contratos fraudulentos.",
                    novaProva = "Vídeo e arquivos com a trilha do dinheiro roubado."
                )
            ),
            decisoes = listOf(
                Decisao(
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
                Decisao(
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
                Decisao(
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
                Decisao(
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
        ),
        Case(
            id = "caso_02",
            titulo = "A Revolta do Bairro Liberdade",
            descricao = "São Paulo, 8 de julho de 2024 – Após a morte de um jovem por policiais, o Bairro Liberdade explode em protestos. A líder comunitária Ana Ribeiro é acusada de incitar saques e violência. A polícia exige prisão, mas ONGs apontam brutalidade policial como a causa.",
            imagem = "caso_02_protestos",
            provas = mutableListOf(
                "Vídeo mostra Ana discursando: \"Não vamos nos calar!\" antes dos saques.",
                "Relatório policial cita 20 lojas destruídas e R\$ 1,5 milhão em prejuízos.",
                "Testemunhas afirmam que policiais atiraram sem motivo, matando o jovem."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Analisar câmeras de segurança",
                    custo = mapOf("relacaoGoverno" to -5, "influenciaPolitica" to -5),
                    resultado = "Imagens mostram policiais atirando sem provocação, mas também Ana incentivando a multidão.",
                    novaProva = "Vídeo de câmeras mostrando o confronto."
                ),
                Investigacao(
                    acao = "Ouvir testemunhas anônimas",
                    custo = mapOf("relacaoImprensa" to -5, "respeitoInstitucional" to -5),
                    resultado = "Testemunhas confirmam abuso policial, mas também dizem que Ana organizou barricadas.",
                    novaProva = "Depoimentos gravados de testemunhas."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Condenar Ana por incitação à violência",
                    efeitos = mapOf(
                        "apoioPopular" to -15,
                        "respeitoInstitucional" to 10,
                        "influenciaPolitica" to 10,
                        "relacaoImprensa" to -10,
                        "relacaoGoverno" to 15,
                        "relacaoONGs" to -15
                    ),
                    manchete = "Líder Presa! Bairro Liberdade Sob Controle!",
                    reacaoPopular = "Protestos: '#AnaInocente' viraliza.",
                    reacaoMidia = "Diário da Ordem: 'Justiça contra o caos!'"
                ),
                Decisao(
                    texto = "Absolver Ana e culpar a polícia",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -15,
                        "influenciaPolitica" to -20,
                        "relacaoImprensa" to 15,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 10
                    ),
                    manchete = "Justiça com o Povo! Polícia Culpada no Liberdade!",
                    reacaoPopular = "Apoio massivo: '#JustiçaParaLiberdade'",
                    reacaoMidia = "Globo: 'Decisão pode inflamar tensões.'"
                ),
                Decisao(
                    texto = "Adiar decisão e investigar abusos",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Tribunal Hesita! Caso Liberdade Sem Resposta!",
                    reacaoPopular = "Frustração: '#JustiçaLenta'",
                    reacaoMidia = "Voz do Povo: 'Adiar é covardia!'"
                ),
                Decisao(
                    texto = "Punir policiais e advertir Ana",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to -5,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -10,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Divide Culpa! Policiais e Ana Punidos!",
                    reacaoPopular = "Apoio misto: '#LiberdadeVive'",
                    reacaoMidia = "Globo: 'Solução tenta apaziguar ânimos.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'Ana é vítima ou criminosa?'",
                "ONGs: 'Polícia mata, Ana apenas gritou!'",
                "Rede Social: '#JustiçaParaLiberdade'"
            )
        ),
        Case(
            id = "caso_03",
            titulo = "O Escândalo da Vacina Falsa",
            descricao = "Rio de Janeiro, 12 de setembro de 2024 – A farmacêutica BioVida é acusada de vender 2 milhões de doses falsas de vacina contra uma nova epidemia. Pacientes morreram, e o CEO, Dr. Carlos Mendes, culpa sabotagem interna. O governo exige punição máxima.",
            imagem = "caso_03_vacina",
            provas = mutableListOf(
                "Laudos mostram que as vacinas eram solução salina, sem princípio ativo.",
                "E-mails internos da BioVida sugerem que Mendes sabia da fraude.",
                "Ex-funcionário acusa Mendes de ordenar a falsificação para lucrar."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Periciar lotes de vacinas",
                    custo = mapOf("relacaoImprensa" to -5, "apoioPopular" to -5),
                    resultado = "Perícia confirma: 90% das vacinas eram falsas, com custo de R\$ 200 milhões.",
                    novaProva = "Relatório pericial detalhando a fraude."
                ),
                Investigacao(
                    acao = "Investigar denunciante anônimo",
                    custo = mapOf("respeitoInstitucional" to -5, "relacaoGoverno" to -5),
                    resultado = "Denunciante entrega gravações de Mendes discutindo lucros da fraude.",
                    novaProva = "Áudios comprometedores de Mendes."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Condenar Mendes e multar BioVida",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 15,
                        "relacaoGoverno" to -10,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Contra a Morte! BioVida e Mendes Punidos!",
                    reacaoPopular = "Apoio: '#VacinaVerdade'",
                    reacaoMidia = "Globo: 'Punição é vitória da saúde!'"
                ),
                Decisao(
                    texto = "Absolver Mendes por falta de provas",
                    efeitos = mapOf(
                        "apoioPopular" to -20,
                        "respeitoInstitucional" to 15,
                        "influenciaPolitica" to 10,
                        "relacaoImprensa" to -15,
                        "relacaoGoverno" to 10,
                        "relacaoONGs" to -10
                    ),
                    manchete = "Escândalo! BioVida Livre, Povo Traído!",
                    reacaoPopular = "Fúria: '#JustiçaVendida'",
                    reacaoMidia = "Jornal do Povo: 'Tribunal protege assassinos!'"
                ),
                Decisao(
                    texto = "Exigir nova perícia",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Adia! Caso BioVida Sem Fim!",
                    reacaoPopular = "Frustração: '#VacinaLenta'",
                    reacaoMidia = "Voz do Povo: 'Adiar é conivência!'"
                ),
                Decisao(
                    texto = "Prender Mendes com base em novas provas",
                    efeitos = mapOf(
                        "apoioPopular" to 25,
                        "respeitoInstitucional" to 0,
                        "influenciaPolitica" to -20,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -15,
                        "relacaoONGs" to 0
                    ),
                    manchete = "Mendes na Cadeia! BioVida Desmascarada!",
                    reacaoPopular = "Festas: '#JustiçaFeita'",
                    reacaoMidia = "Globo: 'Fim de um império criminoso.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'BioVida matou com vacinas falsas!'",
                "BioVida: 'Somos vítimas de sabotagem!'",
                "Rede Social: '#VacinaVerdade'"
            )
        ),
        Case(
            id = "caso_04",
            titulo = "O Desastre do Vale Verde",
            descricao = "Minas Gerais, 15 de novembro de 2024 – Um vazamento químico da mineradora Vale Verde contamina o rio Claro, matando 300 pessoas e destruindo o ecossistema. A ONG Frente Verde é acusada de sabotar a mina, enquanto a Vale Verde nega negligência. O povo exige justiça.",
            imagem = "caso_04_vazamento",
            provas = mutableListOf(
                "Laudos mostram que o vazamento foi causado por falhas de segurança na mina.",
                "Vídeo da Frente Verde mostra ativistas invadindo a mina dias antes.",
                "Relatório interno da Vale Verde admite cortes de manutenção para reduzir custos."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Analisar sistemas de segurança",
                    custo = mapOf("relacaoImprensa" to -5, "influenciaPolitica" to -5),
                    resultado = "Sistemas de segurança estavam desativados por ordem da diretoria.",
                    novaProva = "Documento interno ordenando corte de segurança."
                ),
                Investigacao(
                    acao = "Interrogar ativistas da Frente Verde",
                    custo = mapOf("relacaoONGs" to -5, "respeitoInstitucional" to -5),
                    resultado = "Ativistas confessam sabotagem, mas dizem que foi para expor negligência.",
                    novaProva = "Confissão gravada dos ativistas."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Multar Vale Verde em R\$ 5 bilhões",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -5,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -10,
                        "relacaoONGs" to 15
                    ),
                    manchete = "Justiça para o Vale! Vale Verde Paga R\$ 5 Bi!",
                    reacaoPopular = "'#RioClaroVive' viraliza nas redes.",
                    reacaoMidia = "Terra Viva: 'Multa é o começo!'"
                ),
                Decisao(
                    texto = "Condenar Frente Verde por terrorismo",
                    efeitos = mapOf(
                        "apoioPopular" to -15,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to 15,
                        "relacaoImprensa" to -10,
                        "relacaoGoverno" to 10,
                        "relacaoONGs" to -15
                    ),
                    manchete = "Ativistas na Cadeia! Frente Verde Culpada!",
                    reacaoPopular = "ONGs: 'Culparam os heróis!'",
                    reacaoMidia = "Diário da Ordem: 'Radicalismo punido!'"
                ),
                Decisao(
                    texto = "Exigir investigação federal",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Hesita! Vale Verde no Limbo!",
                    reacaoPopular = "'#ValeVerdeMata' cresce.",
                    reacaoMidia = "Jornal Progressista: 'Adiar é perigoso.'"
                ),
                Decisao(
                    texto = "Multar Vale Verde e prender sabotadores",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to 0,
                        "influenciaPolitica" to -10,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to 0,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Dura! Vale Verde Multada, Ativistas Presos!",
                    reacaoPopular = "'#RioClaroVive' com apoio misto.",
                    reacaoMidia = "Globo: 'Solução equilibrada.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Terra Viva: 'Vale Verde assassinou o rio Claro!'",
                "Diário da Ordem: 'Ativistas destruíram a Vale Verde!'",
                "Rede Social: '#JustiçaAmbiental'"
            )
        ),
        Case(
            id = "caso_05",
            titulo = "Sombra: Herói ou Traidor da Nação?",
            descricao = "1º de maio de 2025 – O hacker Sombra, revelado como Lucas Ferreira, ex-analista do Ministério da Defesa, expôs 50 mil documentos secretos que incriminam a elite do poder. Os arquivos mostram corrupção envolvendo deputados, juízes e o vice-presidente, além de vigilância ilegal contra jornalistas e ativistas. Para milhões, Sombra é um herói; para o governo, um traidor que ameaça a segurança nacional. Com protestos pró-Sombra e pressão internacional, o tribunal decidirá seu destino.",
            imagem = "caso_05_hacker",
            provas = mutableListOf(
                "Documentos vazados em 30/04/2025 mostram propinas de R\$ 50 milhões pagas a deputados por empreiteiras.",
                "Relatórios da inteligência confirmam que Sombra acessou servidores secretos às 3h de 28/04/2025.",
                "Testemunho de Ana Clara, jornalista vigiada, revela ameaças do governo após reportagens críticas."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Rastrear servidores usados por Sombra",
                    custo = mapOf("relacaoImprensa" to -5, "influenciaPolitica" to -5),
                    resultado = "Os servidores revelam que Sombra agiu sozinho, sem laços com potências estrangeiras.",
                    novaProva = "Logs de acesso confirmando que Sombra agiu independentemente."
                ),
                Investigacao(
                    acao = "Ouvir delator anônimo do governo",
                    custo = mapOf("relacaoGoverno" to -10, "respeitoInstitucional" to -5),
                    resultado = "O delator confirma que o governo ordenou vigilância ilegal contra 200 cidadãos.",
                    novaProva = "Gravações do delator detalhando ordens de vigilância."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Condenar Sombra por traição",
                    efeitos = mapOf(
                        "apoioPopular" to -20,
                        "respeitoInstitucional" to 10,
                        "influenciaPolitica" to 15,
                        "relacaoImprensa" to -15,
                        "relacaoGoverno" to 15,
                        "relacaoONGs" to -10
                    ),
                    manchete = "Sombra Preso! Tribunal Pune Traidor da Nação!",
                    reacaoPopular = "Protestos explodem: '#LiberdadeSombra'",
                    reacaoMidia = "Diário da Ordem: 'Segurança nacional protegida!'"
                ),
                Decisao(
                    texto = "Absolver Sombra como denunciante",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -15,
                        "influenciaPolitica" to -20,
                        "relacaoImprensa" to 15,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 15
                    ),
                    manchete = "Sombra Livre! Herói da Verdade Vence!",
                    reacaoPopular = "Multidões celebram: '#SombraVive'",
                    reacaoMidia = "Globo: 'Decisão abala o governo.'"
                ),
                Decisao(
                    texto = "Adiar decisão e investigar governo",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -10,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -10,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Hesita! Caso Sombra no Limbo!",
                    reacaoPopular = "'#JustiçaLenta' viraliza.",
                    reacaoMidia = "Jornal Progressista: 'Adiar é necessário.'"
                ),
                Decisao(
                    texto = "Proteger Sombra e condenar vigilância",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -15,
                        "relacaoONGs" to 10
                    ),
                    manchete = "Sombra Salvo! Vigilância Ilegal Condenada!",
                    reacaoPopular = "'#SombraHerói' ganha força.",
                    reacaoMidia = "Voz do Povo: 'Passo contra corrupção!'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'Sombra é herói!'",
                "Diário da Ordem: 'Sombra é traidor!'",
                "Rede Social: '#LiberdadeSombra'"
            )
        ),
        Case(
            id = "caso_06",
            titulo = "O Escândalo da Privatização da Água",
            descricao = "Salvador, 10 de junho de 2025 – A empresa AquaCorp é acusada de manipular a privatização do sistema de água, cobrando tarifas abusivas e deixando bairros sem abastecimento. O governador, aliado da AquaCorp, defende a privatização, enquanto moradores protestam por água potável. ONGs denunciam corrupção no processo.",
            imagem = "caso_06_agua",
            provas = mutableListOf(
                "Contratos mostram que a AquaCorp pagou R\$ 10 milhões a consultores ligados ao governador.",
                "Relatórios indicam que 40% dos bairros pobres estão sem água há meses.",
                "Vídeo viral mostra executivos da AquaCorp comemorando lucros recordes."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Auditar contratos da privatização",
                    custo = mapOf("relacaoGoverno" to -5, "influenciaPolitica" to -5),
                    resultado = "A auditoria revela cláusulas secretas que favorecem a AquaCorp em detrimento do público.",
                    novaProva = "Documento com cláusulas secretas da privatização."
                ),
                Investigacao(
                    acao = "Entrevistar moradores afetados",
                    custo = mapOf("relacaoImprensa" to -5, "apoioPopular" to -5),
                    resultado = "Moradores relatam ameaças de milícias ligadas à AquaCorp para silenciar protestos.",
                    novaProva = "Depoimentos gravados de moradores."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Anular a privatização e estatizar a água",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 15
                    ),
                    manchete = "Água é do Povo! Privatização Anulada!",
                    reacaoPopular = "Festas nas ruas: '#ÁguaParaTodos' viraliza.",
                    reacaoMidia = "Jornal do Povo: 'Vitória contra a ganância!'"
                ),
                Decisao(
                    texto = "Manter a privatização e multar a AquaCorp",
                    efeitos = mapOf(
                        "apoioPopular" to -15,
                        "respeitoInstitucional" to 10,
                        "influenciaPolitica" to 10,
                        "relacaoImprensa" to -10,
                        "relacaoGoverno" to 15,
                        "relacaoONGs" to -10
                    ),
                    manchete = "AquaCorp Multada, Mas Privatização Segue!",
                    reacaoPopular = "Protestos: '#ÁguaNãoÉMercadoria'",
                    reacaoMidia = "Diário da Ordem: 'Solução mantém estabilidade.'"
                ),
                Decisao(
                    texto = "Adiar decisão e formar comissão",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Empurra Caso da Água com a Barriga!",
                    reacaoPopular = "Frustração: '#JustiçaLenta'",
                    reacaoMidia = "Voz do Povo: 'Comissões são perda de tempo!'"
                ),
                Decisao(
                    texto = "Punir AquaCorp e rever contratos",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -10,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -15,
                        "relacaoONGs" to 10
                    ),
                    manchete = "AquaCorp Punida! Contratos Serão Revistos!",
                    reacaoPopular = "Apoio: '#JustiçaPelaÁgua'",
                    reacaoMidia = "Globo: 'Decisão tenta equilibrar tensões.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'AquaCorp lucra enquanto o povo sofre!'",
                "Diário da Ordem: 'Privatização trouxe eficiência!'",
                "Rede Social: '#ÁguaParaTodos'",
                "ONGs: 'Água é direito, não mercadoria!'"
            )
        ),
        Case(
            id = "caso_07",
            titulo = "A Queda do Ídolo do Futebol",
            descricao = "Rio de Janeiro, 22 de agosto de 2025 – O astro do futebol Gabriel Lima é acusado de sonegar R\$ 80 milhões em impostos, usando empresas offshore. Fãs o defendem como vítima de perseguição, enquanto a Receita Federal exige prisão. A imprensa explora a polêmica, dividindo a nação.",
            imagem = "caso_07_futebol",
            provas = mutableListOf(
                "Extratos mostram transferências de R\$ 50 milhões para contas nas Ilhas Virgens.",
                "E-mails sugerem que Gabriel sabia das operações ilegais.",
                "Testemunha, um ex-contador, afirma que Gabriel ordenou a sonegação."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Rastrear contas offshore",
                    custo = mapOf("relacaoImprensa" to -5, "influenciaPolitica" to -5),
                    resultado = "Rastreamento confirma que Gabriel controlava as contas diretamente.",
                    novaProva = "Registros bancários com assinatura de Gabriel."
                ),
                Investigacao(
                    acao = "Interrogar ex-contador sob proteção",
                    custo = mapOf("respeitoInstitucional" to -5, "relacaoGoverno" to -5),
                    resultado = "Contador entrega documentos que comprovam ordens de Gabriel.",
                    novaProva = "Documentos assinados por Gabriel."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Condenar Gabriel a 5 anos de prisão",
                    efeitos = mapOf(
                        "apoioPopular" to -15,
                        "respeitoInstitucional" to 15,
                        "influenciaPolitica" to 10,
                        "relacaoImprensa" to -10,
                        "relacaoGoverno" to 15,
                        "relacaoONGs" to -5
                    ),
                    manchete = "Ídolo na Cadeia! Gabriel Paga por Sonegação!",
                    reacaoPopular = "Fãs protestam: '#GabrielInocente'",
                    reacaoMidia = "Globo: 'Justiça contra privilégios!'"
                ),
                Decisao(
                    texto = "Absolver Gabriel por falta de provas",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -15,
                        "influenciaPolitica" to -10,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -15,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Gabriel Livre! Justiça Favorece o Ídolo!",
                    reacaoPopular = "Fãs comemoram: '#GabrielVence'",
                    reacaoMidia = "Jornal do Povo: 'Tribunal cede à pressão!'"
                ),
                Decisao(
                    texto = "Exigir nova investigação fiscal",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Caso Gabriel Adiado! Justiça Hesita!",
                    reacaoPopular = "Memes: '#JustiçaFutebol'",
                    reacaoMidia = "Voz do Povo: 'Mais atrasos no caso!'"
                ),
                Decisao(
                    texto = "Multar Gabriel com base em novas provas",
                    efeitos = mapOf(
                        "apoioPopular" to 10,
                        "respeitoInstitucional" to 10,
                        "influenciaPolitica" to 0,
                        "relacaoImprensa" to 5,
                        "relacaoGoverno" to 10,
                        "relacaoONGs" to 0
                    ),
                    manchete = "Gabriel Multado em R\$ 100 Milhões!",
                    reacaoPopular = "Apoio misto: '#JustiçaFeita'",
                    reacaoMidia = "Globo: 'Solução evita crise com fãs.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'Gabriel traiu o Brasil!'",
                "Fãs: 'Gabriel é vítima da Receita!'",
                "Rede Social: '#GabrielInocente'",
                "Globo: 'Sonegação ou perseguição?'"
            )
        ),
        Case(
            id = "caso_08",
            titulo = "O Julgamento da Inteligência Artificial",
            descricao = "São Paulo, 15 de outubro de 2025 – A empresa TechNova é acusada de usar sua IA, Aurora, para manipular eleições com campanhas de desinformação. A TechNova nega, alegando que a IA foi hackeada. Ativistas exigem o banimento da IA, enquanto o governo teme perder investimentos.",
            imagem = "caso_99_ia",
            provas = mutableListOf(
                "Logs mostram que Aurora gerou 10 milhões de postagens falsas em redes sociais.",
                "E-mails internos sugerem que a TechNova lucrou R\$ 200 milhões com campanhas.",
                "Hackers anônimos assumem a autoria, mas sem provas concretas."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Analisar servidores da Aurora",
                    custo = mapOf("relacaoImprensa" to -5, "respeitoInstitucional" to -5),
                    resultado = "Análise revela que a IA foi programada para manipular, sem sinais de hacking.",
                    novaProva = "Código-fonte da Aurora com instruções de manipulação."
                ),
                Investigacao(
                    acao = "Investigar hackers anônimos",
                    custo = mapOf("influenciaPolitica" to -5, "relacaoGoverno" to -5),
                    resultado = "Hackers são rastreados, mas não há evidências de acesso à Aurora.",
                    novaProva = "Relatório negando envolvimento de hackers."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Banir a IA Aurora e multar TechNova",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 15
                    ),
                    manchete = "Fim da Aurora! TechNova Punida!",
                    reacaoPopular = "'#IAControlada' viraliza nas redes.",
                    reacaoMidia = "Jornal do Povo: 'Vitória contra manipulação!'"
                ),
                Decisao(
                    texto = "Absolver TechNova e culpar hackers",
                    efeitos = mapOf(
                        "apoioPopular" to -15,
                        "respeitoInstitucional" to 10,
                        "influenciaPolitica" to 15,
                        "relacaoImprensa" to -10,
                        "relacaoGoverno" to 10,
                        "relacaoONGs" to -15
                    ),
                    manchete = "TechNova Livre! Hackers Culpados!",
                    reacaoPopular = "Protestos: '#IADestrói'",
                    reacaoMidia = "Diário da Ordem: 'Tecnologia protegida!'"
                ),
                Decisao(
                    texto = "Adiar decisão e regular IAs",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Hesita! Caso Aurora Sem Fim!",
                    reacaoPopular = "'#RegulemIA' ganha força.",
                    reacaoMidia = "Voz do Povo: 'Adiar é arriscado!'"
                ),
                Decisao(
                    texto = "Desativar Aurora com base em provas",
                    efeitos = mapOf(
                        "apoioPopular" to 10,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -10,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -10,
                        "relacaoONGs" to 10
                    ),
                    manchete = "Aurora Desligada! TechNova Sob Controle!",
                    reacaoPopular = "'#JustiçaDigital' é celebrada.",
                    reacaoMidia = "Globo: 'Decisão balanceia inovação e ética.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'Aurora manipulou a democracia!'",
                "TechNova: 'Somos vítimas de hackers!'",
                "Rede Social: '#RegulemIA'",
                "ONGs: 'IA é uma ameaça à liberdade!'"
            )
        ),
        Case(
            id = "caso_09",
            titulo = "A Crise da Reforma Agrária",
            descricao = "Mato Grosso, 5 de dezembro de 2025 – O líder do Movimento Terra Livre, José Mendes, é acusado de invadir terras privadas e incitar conflitos que deixaram 10 mortos. Fazendeiros exigem sua prisão, enquanto camponeses o veem como herói da reforma agrária. O governo teme instabilidade no campo.",
            imagem = "caso_09_reforma",
            provas = mutableListOf(
                "Vídeos mostram José liderando ocupações de fazendas.",
                "Relatórios policiais citam armas encontradas com membros do Terra Livre.",
                "Testemunhas afirmam que fazendeiros contrataram milícias para atacar camponeses."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Investigar milícias dos fazendeiros",
                    custo = mapOf("relacaoGoverno" to -5, "influenciaPolitica" to -5),
                    resultado = "Provas confirmam que milícias foram pagas para provocar conflitos.",
                    novaProva = "Gravações de fazendeiros contratando milícias."
                ),
                Investigacao(
                    acao = "Analisar armas do Terra Livre",
                    custo = mapOf("relacaoONGs" to -5, "apoioPopular" to -5),
                    resultado = "Análise revela que armas eram de origem policial, sugerindo armação.",
                    novaProva = "Relatório balístico das armas."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Condenar José por incitação à violência",
                    efeitos = mapOf(
                        "apoioPopular" to -15,
                        "respeitoInstitucional" to 10,
                        "influenciaPolitica" to 15,
                        "relacaoImprensa" to -10,
                        "relacaoGoverno" to 15,
                        "relacaoONGs" to -15
                    ),
                    manchete = "Líder Preso! Terra Livre Derrotada!",
                    reacaoPopular = "Camponeses: '#JoséHerói'",
                    reacaoMidia = "Diário da Ordem: 'Ordem no campo!'"
                ),
                Decisao(
                    texto = "Absolver José e investigar fazendeiros",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -15,
                        "influenciaPolitica" to -20,
                        "relacaoImprensa" to 15,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 15
                    ),
                    manchete = "Justiça com Camponeses! Fazendeiros na Mira!",
                    reacaoPopular = "'#TerraLivreVence' viraliza.",
                    reacaoMidia = "Globo: 'Decisão pode inflamar o campo.'"
                ),
                Decisao(
                    texto = "Adiar decisão e mediar conflitos",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Tribunal Tenta Paz no Campo!",
                    reacaoPopular = "'#JustiçaLenta' cresce.",
                    reacaoMidia = "Voz do Povo: 'Mediação é fraca!'"
                ),
                Decisao(
                    texto = "Punir milícias e advertir José",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to 0,
                        "influenciaPolitica" to -10,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -10,
                        "relacaoONGs" to 10
                    ),
                    manchete = "Justiça Ataca Milícias! José Advertido!",
                    reacaoPopular = "'#ReformaVive' com apoio misto.",
                    reacaoMidia = "Globo: 'Solução busca equilíbrio.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'José Mendes é herói ou bandido?'",
                "Fazendeiros: 'Terra Livre é terrorismo!'",
                "Rede Social: '#ReformaAgrária'",
                "ONGs: 'Camponeses lutam por justiça!'"
            )
        ),
        Case(
            id = "caso_10",
            titulo = "O Colapso da Barragem do Norte",
            descricao = "Pará, 20 de fevereiro de 2026 – Uma barragem da mineradora NorteMinas ruiu, matando 200 pessoas e devastando comunidades indígenas. A empresa culpa chuvas, mas laudos apontam negligência. Líderes indígenas exigem justiça, enquanto o governo protege a NorteMinas por empregos.",
            imagem = "caso_10_barragem",
            provas = mutableListOf(
                "Laudos mostram que a barragem tinha rachaduras ignoradas há dois anos.",
                "E-mails da NorteMinas minimizam alertas de engenheiros.",
                "Líder indígena relata que a empresa despejou rejeitos ilegalmente."
            ),
            investigacoes = listOf(
                Investigacao(
                    acao = "Periciar a barragem destruída",
                    custo = mapOf("relacaoImprensa" to -5, "apoioPopular" to -5),
                    resultado = "Perícia confirma negligência grave, com cortes de manutenção.",
                    novaProva = "Relatório pericial detalhando falhas."
                ),
                Investigacao(
                    acao = "Ouvir engenheiros da NorteMinas",
                    custo = mapOf("relacaoGoverno" to -5, "respeitoInstitucional" to -5),
                    resultado = "Engenheiros confessam que foram pressionados a ignorar riscos.",
                    novaProva = "Depoimentos gravados dos engenheiros."
                )
            ),
            decisoes = listOf(
                Decisao(
                    texto = "Multar NorteMinas em R\$ 10 bilhões",
                    efeitos = mapOf(
                        "apoioPopular" to 20,
                        "respeitoInstitucional" to -10,
                        "influenciaPolitica" to -15,
                        "relacaoImprensa" to 15,
                        "relacaoGoverno" to -20,
                        "relacaoONGs" to 15
                    ),
                    manchete = "Justiça para o Norte! NorteMinas Paga R\$ 10 Bi!",
                    reacaoPopular = "'#JustiçaIndígena' viraliza.",
                    reacaoMidia = "Jornal do Povo: 'Multa histórica!'"
                ),
                Decisao(
                    texto = "Absolver NorteMinas por força maior",
                    efeitos = mapOf(
                        "apoioPopular" to -20,
                        "respeitoInstitucional" to 15,
                        "influenciaPolitica" to 15,
                        "relacaoImprensa" to -15,
                        "relacaoGoverno" to 15,
                        "relacaoONGs" to -15
                    ),
                    manchete = "NorteMinas Livre! Tragédia Culpa da Natureza!",
                    reacaoPopular = "Indígenas: '#JustiçaVendida'",
                    reacaoMidia = "Diário da Ordem: 'Decisão protege economia.'"
                ),
                Decisao(
                    texto = "Exigir nova perícia",
                    efeitos = mapOf(
                        "apoioPopular" to -5,
                        "respeitoInstitucional" to 5,
                        "influenciaPolitica" to -5,
                        "relacaoImprensa" to -5,
                        "relacaoGoverno" to -5,
                        "relacaoONGs" to 5
                    ),
                    manchete = "Justiça Adia Caso da Barragem!",
                    reacaoPopular = "'#JustiçaLenta' cresce.",
                    reacaoMidia = "Voz do Povo: 'Adiar é conivência!'"
                ),
                Decisao(
                    texto = "Fechar NorteMinas com base em provas",
                    efeitos = mapOf(
                        "apoioPopular" to 15,
                        "respeitoInstitucional" to 0,
                        "influenciaPolitica" to -20,
                        "relacaoImprensa" to 10,
                        "relacaoGoverno" to -15,
                        "relacaoONGs" to 10
                    ),
                    manchete = "NorteMinas Fechada! Justiça com Indígenas!",
                    reacaoPopular = "'#VidaIndígena' é celebrada.",
                    reacaoMidia = "Globo: 'Fechamento abala economia local.'",
                    requiresInvestigation = true
                )
            ),
            midia = listOf(
                "Jornal do Povo: 'NorteMinas destruiu o Norte!'",
                "NorteMinas: 'Chuvas causaram o colapso!'",
                "Rede Social: '#JustiçaIndígena'",
                "ONGs: 'Proteger indígenas é proteger a Amazônia!'"
            )
        )
    )

    private val eventosAleatorios = listOf(
        EventoAleatorio(
            id = "protestos",
            texto = "**Noite de Fúria e Cinzas: A Revolta que Paralisou a Capital**\n\nMilhares de pessoas tomaram as ruas, erguendo barricadas de pneus em chamas. Com a hashtag **#ForaJuiz**, manifestantes expressam indignação contra uma decisão judicial.",
            efeitos = mapOf("apoioPopular" to -10, "relacaoImprensa" to -5),
            condicao = { state -> state.relacaoImprensa < 25 || state.apoioPopular < 30 },
            imagem = "protestos"
        ),
        EventoAleatorio(
            id = "elogio_ong",
            texto = "**Um Farol na Tempestade: ONG Reconhece a Coragem do Juiz**\n\nA ONG Justiça Sem Fronteiras exalta o juiz como exemplo de integridade, oferecendo esperança em meio à crise.",
            efeitos = mapOf("respeitoInstitucional" to 10, "relacaoONGs" to 10),
            condicao = { state -> state.relacaoONGs > 75 },
            imagem = "elogio_ong"
        ),
        EventoAleatorio(
            id = "vazamento",
            texto = "**Vazamento Explosivo: Áudios Revelam Conluio**\n\nÁudios sugerem uma aliança entre o juiz e o governo, abalando a confiança pública e desencadeando investigações.",
            efeitos = mapOf("influenciaPolitica" to -15, "relacaoImprensa" to -10),
            condicao = { state -> state.relacaoGoverno > 75 && state.relacaoImprensa < 50 },
            imagem = "vazamento"
        ),
        EventoAleatorio(
            id = "aplausos_imprensa",
            texto = "**Luz na Escuridão: Imprensa Exalta Decisão do Tribunal**\n\nO Jornal do Povo publica editorial elogiando a imparcialidade do juiz, inspirando confiança na justiça.",
            efeitos = mapOf("relacaoImprensa" to 10, "apoioPopular" to 5),
            condicao = { state -> state.relacaoImprensa > 75 },
            imagem = "aplausos_imprensa"
        )
    )

    private val eventosCrise = listOf(
        EventoCrise(
            id = "crise_judiciaria",
            texto = "**Crise Judicial: Greve Nacional de Magistrados**\n\nUma greve histórica paralisa o Judiciário, liderada por juízes que exigem melhores salários e condições. A população está dividida: alguns apoiam a causa, outros veem a paralisação como abandono do dever. Como Juiz Supremo, sua posição será crucial.\n\nA nação observa enquanto o tribunal, símbolo da justiça, enfrenta sua maior prova. Escolha com cuidado: sua decisão pode fortalecer ou destruir a confiança no sistema judicial.",
            imagem = "greve_judiciaria",
            opcoes = listOf(
                OpcaoCrise(
                    texto = "Apoiar a greve e negociar com os juízes",
                    efeitos = mapOf("apoioPopular" to -10, "respeitoInstitucional" to 15, "relacaoGoverno" to -10),
                    resultado = "Os juízes encerram a greve após negociações tensas, mas o governo promete represálias, acusando você de fraqueza."
                ),
                OpcaoCrise(
                    texto = "Condenar a greve e exigir retorno ao trabalho",
                    efeitos = mapOf("apoioPopular" to 10, "respeitoInstitucional" to -15, "relacaoGoverno" to 10),
                    resultado = "A greve termina sob pressão, mas o Judiciário fica ressentido, prometendo resistência interna contra suas decisões."
                ),
                OpcaoCrise(
                    texto = "Ignorar a crise e focar nos casos",
                    efeitos = mapOf("respeitoInstitucional" to -5, "relacaoImprensa" to -5),
                    resultado = "A crise se arrasta, com a mídia acusando o tribunal de covardia. A confiança pública no Judiciário despenca."
                )
            )
        )
    )

    fun startGame(playerName: String) {
        if (!validateName(playerName)) {
            _notification.value = "Nome inválido! Use letras, números e espaços (máximo de 20 caracteres)."
            return
        }
        _state.value = _state.value?.copy(playerName = playerName)
    }

    fun setDifficulty(level: String) {
        _state.value = _state.value?.copy(
            dificuldade = level,
            orcamento = when (level) {
                "fácil" -> 5000
                "médio" -> 10000
                "difícil" -> 15000
                else -> 10000
            },
            custoManutencao = when (level) {
                "fácil" -> 500
                "médio" -> 1000
                "difícil" -> 1500
                else -> 1000
            },
            maxInvestigations = when (level) {
                "fácil" -> 3
                "médio" -> 2
                "difícil" -> 1
                else -> 2
            }
        )
        loadCase()
    }

    private fun validateName(name: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9\\s]{1,20}$")
        return regex.matches(name)
    }

    private fun loadCase() {
        val state = _state.value ?: return
        if (state.casosJulgados >= casos.size) {
            endGame()
            return
        }
        _state.value = state.copy(
            currentCase = casos[state.casosJulgados],
            investigationsDone = 0
        )
        _notification.value = "Caso ${state.casosJulgados + 1} de ${casos.size}: ${state.currentCase?.titulo}"
        triggerRandomEvent()
    }

    fun investigate(index: Int) {
        val state = _state.value ?: return
        if (state.investigationsDone >= state.maxInvestigations) {
            _notification.value = "Limite de investigações atingido para este caso."
            return
        }
        val currentCase = state.currentCase ?: return
        val inv = currentCase.investigacoes[index]
        if (state.orcamento < 1000) {
            _notification.value = "Orçamento insuficiente para realizar a investigação!"
            return
        }
        _state.value = state.copy(
            orcamento = state.orcamento - 2000,
            investigationsDone = state.investigationsDone + 1,
            currentCase = currentCase.copy(provas = currentCase.provas.toMutableList().apply { add(inv.novaProva) })
        )
        applyEffects(inv.custo)
        _notification.value = "Investigação concluída: ${inv.resultado}"
    }

    fun makeDecision(index: Int) {
        val state = _state.value ?: return
        val currentCase = state.currentCase ?: return
        val availableDecisions = currentCase.decisoes.filter { !it.requiresInvestigation || state.investigationsDone > 0 }
        if (index !in availableDecisions.indices) {
            _notification.value = "Decisão inválida!"
            return
        }
        val decision = availableDecisions[index]
        applyEffects(decision.efeitos)
        _state.value = state.copy(
            casosJulgados = state.casosJulgados + 1,
            orcamento = state.orcamento - state.custoManutencao
        )
        if (state.orcamento <= 0 || state.apoioPopular <= 0 || state.respeitoInstitucional <= 0 ||
            state.influenciaPolitica <= 0 || state.relacaoImprensa <= 0 || state.relacaoGoverno <= 0 || state.relacaoONGs <= 0
        ) {
            endGame()
            return
        }
        _notification.value = "${decision.manchete}\nReação Popular: ${decision.reacaoPopular}\nReação da Mídia: ${decision.reacaoMidia}"
        triggerCrisisEvent()
        loadCase()
    }

    fun handleCrisisOption(crisisIndex: Int, optionIndex: Int) {
        val state = _state.value ?: return
        val crisis = eventosCrise.getOrNull(crisisIndex) ?: return
        val option = crisis.opcoes.getOrNull(optionIndex) ?: return
        applyEffects(option.efeitos)
        _notification.value = "${crisis.texto}\n\nDecisão: ${option.texto}\nResultado: ${option.resultado}"
        if (state.orcamento <= 0 || state.apoioPopular <= 0 || state.respeitoInstitucional <= 0 ||
            state.influenciaPolitica <= 0 || state.relacaoImprensa <= 0 || state.relacaoGoverno <= 0 || state.relacaoONGs <= 0
        ) {
            endGame()
            return
        }
        loadCase()
    }

    private fun applyEffects(efeitos: Map<String, Int>) {
        val state = _state.value ?: return
        var newState = state.copy()
        efeitos.forEach { (key, value) ->
            when (key) {
                "orcamento" -> newState = newState.copy(orcamento = max(0, newState.orcamento + value))
                "apoioPopular" -> newState = newState.copy(apoioPopular = max(0, min(100, newState.apoioPopular + value)))
                "respeitoInstitucional" -> newState = newState.copy(respeitoInstitucional = max(0, min(100, newState.respeitoInstitucional + value)))
                "influenciaPolitica" -> newState = newState.copy(influenciaPolitica = max(0, min(100, newState.influenciaPolitica + value)))
                "relacaoImprensa" -> newState = newState.copy(relacaoImprensa = max(0, min(100, newState.relacaoImprensa + value)))
                "relacaoGoverno" -> newState = newState.copy(relacaoGoverno = max(0, min(100, newState.relacaoGoverno + value)))
                "relacaoONGs" -> newState = newState.copy(relacaoONGs = max(0, min(100, newState.relacaoONGs + value)))
            }
        }
        _state.value = newState
    }

    private fun triggerRandomEvent() {
        val state = _state.value ?: return
        val eligibleEvents = eventosAleatorios.filter { it.condicao(state) }
        if (eligibleEvents.isNotEmpty() && Random.nextFloat() < 0.3f) { // 30% de chance
            val event = eligibleEvents.random()
            applyEffects(event.efeitos)
            _notification.value = "${event.texto}\n\nEfeitos aplicados."
        }
    }

    private fun triggerCrisisEvent() {
        val state = _state.value ?: return
        if (state.casosJulgados % 3 == 0 && eventosCrise.isNotEmpty()) { // A cada 3 casos
            val crisis = eventosCrise.random()
            _notification.value = "${crisis.texto}\n\nEscolha uma opção."
            // A UI deve exibir as opções de crise e chamar handleCrisisOption
        }
    }

    private fun endGame() {
        val state = _state.value ?: return
        val legacyScore = (state.apoioPopular + state.respeitoInstitucional + state.influenciaPolitica +
                state.relacaoImprensa + state.relacaoGoverno + state.relacaoONGs) / 6
        val finalText = when {
            state.orcamento <= 0 -> "${state.playerName}, o tribunal faliu! Sem recursos, você foi destituído do cargo."
            state.apoioPopular <= 0 -> "${state.playerName}, a fúria do povo selou seu destino. Multidões invadiram o tribunal."
            else -> "${state.playerName}, sua trajetória foi controversa. Seu legado divide opiniões após ${state.casosJulgados} casos."
        } + "\n\nResumo:\nCasos Julgados: ${state.casosJulgados}/${casos.size}\nOrçamento Restante: ${state.orcamento}\nMédia de Reputação: ${legacyScore.toInt()}"
        _notification.value = finalText
    }
}