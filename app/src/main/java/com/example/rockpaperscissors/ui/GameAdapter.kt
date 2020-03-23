package com.example.rockpaperscissors.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.game_statistics.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_statistics, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val loss = 0
        val draw = 1
        val win = 2

        fun bind(game: Game) {
            if (game.result==loss)  {itemView.tvWinner.text = itemView.context.getString((R.string.computer_win))}
            if (game.result==draw)  {itemView.tvWinner.text = itemView.context.getString((R.string.draw))}
            if (game.result==win)  {itemView.tvWinner.text = itemView.context.getString((R.string.player_win))}


            //Get image for history from selected move
            itemView.ivComputer.setImageResource(Game.GAME_DRAWABLES[game.computer])
            itemView.ivPlayer.setImageResource(Game.GAME_DRAWABLES[game.player])

            itemView.tvDate.text = game.date.toString()
        }
    }
}