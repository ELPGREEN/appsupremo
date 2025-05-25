package Supremo.Tribunal.Popula.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import Supremo.Tribunal.Popula.model.GameState

@Dao
interface GameDao {
    @Insert
    suspend fun insert(state: GameState)

    @Query("SELECT * FROM GameState LIMIT 1")
    suspend fun getState(): GameState?

    @Query("UPDATE GameState SET playerName = :playerName, dificuldade = :dificuldade, apoioPopular = :apoioPopular, respeitoInstitucional = :respeitoInstitucional, influenciaPolitica = :influenciaPolitica, relacaoImprensa = :relacaoImprensa, relacaoGoverno = :relacaoGoverno, relacaoONGs = :relacaoONGs, casosJulgados = :casosJulgados, investigationsDone = :investigationsDone, maxInvestigations = :maxInvestigations, orcamento = :orcamento, custoManutencao = :custoManutencao")
    suspend fun updateState(
        playerName: String,
        dificuldade: String,
        apoioPopular: Int,
        respeitoInstitucional: Int,
        influenciaPolitica: Int,
        relacaoImprensa: Int,
        relacaoGoverno: Int,
        relacaoONGs: Int,
        casosJulgados: Int,
        investigationsDone: Int,
        maxInvestigations: Int,
        orcamento: Int,
        custoManutencao: Int
    )
}