package com.example.rockpaperscissors.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameRepository = GameRepository(this)
        getStatisticsFromDatabase()
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // inflate the menu to convert it to a thing android understands
        // this will add add items to the action bar if they are present
        menuInflater.inflate(R.menu.menu_main, menu) // https://developer.android.com/guide/topics/ui/menus
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle the action bar item click here which in this case is clearing the game history
        // in the AndroidManifest.xml the parent is specified
        // this will automatically handle clicks on the back button
        return when (item.itemId) {
            R.id.action_history -> {
                startHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startHistoryActivity()  {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
        getStatisticsFromDatabase()
    }

    private fun initViews() {
        ivRock.setOnClickListener { playRPS(0)}
        ivPaper.setOnClickListener { playRPS(1)}
        ivScissors.setOnClickListener { playRPS(2)}
    }

    private fun getStatisticsFromDatabase() {
        mainScope.launch {
            val wins = withContext(Dispatchers.IO) {
                gameRepository.getWins()
            }
            val draws = withContext(Dispatchers.IO) {
                gameRepository.getDraws()
            }
            val losses = withContext(Dispatchers.IO) {
                gameRepository.getLosses()
            }
            //update scoreboard
            this@MainActivity.tvScore.text = "Wins: "+wins+" Draws: "+draws+" Losses:"+losses
        }
    }

    private fun playRPS(choice: Int)    {
        val rock = 0
        val paper = 1
        val scissors = 2



        val lose = 0
        val draw = 1
        val win = 2
        val TBD = 3

        val AIThrow = (0..2).random()
        var result = 3

        if (choice == AIThrow)  {
            result = draw
        } else {
            if (choice == rock) {
                if (AIThrow == paper)   {
                    result = lose
                } else  {
                    result = win
                }
            }

            if (choice == paper)    {
                if (AIThrow == rock)    {result = win}
                else result = lose
            }

            if (choice == scissors) {
                if (AIThrow == rock)    {result = lose}
                else result = win
            }
        }
        //Now change ui
        ivPlayer2.setImageResource((Game.GAME_DRAWABLES[choice]))
        ivComputer2.setImageResource(Game.GAME_DRAWABLES[AIThrow])

        if (result == lose) tvWinner2.text = getString(R.string.computer_win)
        if (result == draw) tvWinner2.text = getString(R.string.draw)
        if (result == win) tvWinner2.text = getString(R.string.player_win)

        //save results
        mainScope.launch{
            val game = Game(result=result,date = Date(),computer = AIThrow,player = choice)
            withContext(Dispatchers.IO) {
                gameRepository.addGame(game)
            }
        }
        getStatisticsFromDatabase()
    }


}
