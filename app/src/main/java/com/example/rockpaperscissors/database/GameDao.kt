package com.example.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors.model.Game

@Dao
interface GameDao   {

    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun addGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    // Get win loss draw counts

    @Query("SELECT COUNT(*) FROM games WHERE result = 0")
    suspend fun getLosses(): Int

    @Query("SELECT COUNT(*) FROM games WHERE result = 1")
    suspend fun getDraws(): Int

    @Query("SELECT COUNT(*) FROM games WHERE result = 2")
    suspend fun getWins(): Int
}