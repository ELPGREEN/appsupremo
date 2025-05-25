package Supremo.Tribunal.Popula.data

import androidx.room.Database
import androidx.room.RoomDatabase
import Supremo.Tribunal.Popula.model.GameState

@Database(entities = [GameState::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}