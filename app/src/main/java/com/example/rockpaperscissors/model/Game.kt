package com.example.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rockpaperscissors.R
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "games")
data class Game (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Long? = null,

    @ColumnInfo(name="result")
    var result: Int,

    @ColumnInfo(name="date")
    var date: Date,

    @ColumnInfo(name = "computer")
    var computer: Int,

    @ColumnInfo(name = "player")
    var player: Int

) : Parcelable  {
    companion object    {
        val GAME_DRAWABLES = arrayOf(R.drawable.rock,R.drawable.paper,R.drawable.scissors)
    }
}