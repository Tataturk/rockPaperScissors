package com.example.rockpaperscissors.database

import android.content.Context
import com.example.rockpaperscissors.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()

    suspend fun addGame(game: Game) = gameDao.addGame(game)

    suspend fun deleteAllGames() = gameDao.deleteAllGames()

    suspend fun deleteGame(game: Game) = gameDao.deleteGame(game)

    suspend fun getLosses(): Int =  gameDao.getLosses()

    suspend fun getDraws(): Int = gameDao.getDraws()

    suspend fun getWins(): Int = gameDao.getWins()
}