package org.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import org.tictactoe.domain.api.model.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private fun cellClickListener(cell: View) {
        val move = cell.tag as Move?
        move?.let { viewModel.play(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.gameState.observe(this, Observer { state ->
            status.text = state.availableMoves.firstOrNull()?.let { move ->
                "Player ${move.player.name()} turn"
            } ?: "Game Over"
            cell_0_0.text = state.previousMoves.find(0, 0)?.player?.name()
            cell_0_0.tag = state.availableMoves.find(0, 0)
            cell_0_1.text = state.previousMoves.find(0, 1)?.player?.name()
            cell_0_1.tag = state.availableMoves.find(0, 1)
            cell_0_2.text = state.previousMoves.find(0, 2)?.player?.name()
            cell_0_2.tag = state.availableMoves.find(0, 2)
            cell_1_0.text = state.previousMoves.find(1, 0)?.player?.name()
            cell_1_0.tag = state.availableMoves.find(1, 0)
            cell_1_1.text = state.previousMoves.find(1, 1)?.player?.name()
            cell_1_1.tag = state.availableMoves.find(1, 1)
            cell_1_2.text = state.previousMoves.find(1, 2)?.player?.name()
            cell_1_2.tag = state.availableMoves.find(1, 2)
            cell_2_0.text = state.previousMoves.find(2, 0)?.player?.name()
            cell_2_0.tag = state.availableMoves.find(2, 0)
            cell_2_1.text = state.previousMoves.find(2, 1)?.player?.name()
            cell_2_1.tag = state.availableMoves.find(2, 1)
            cell_2_2.text = state.previousMoves.find(2, 2)?.player?.name()
            cell_2_2.tag = state.availableMoves.find(2, 2)
        })

        viewModel.gameEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let { gameEvent ->
                if (gameEvent is GAMEOVER) {

                    val text = gameEvent.winner?.let { player ->
                        "Player ${player.name()} wins!"
                    } ?: "It's a draw!"

                    Toast.makeText(this,
                        text,
                        Toast.LENGTH_LONG)
                        .show()
                }
            }
        })

        cell_0_0.setOnClickListener { cellClickListener(it) }
        cell_0_1.setOnClickListener { cellClickListener(it) }
        cell_0_2.setOnClickListener { cellClickListener(it) }
        cell_1_0.setOnClickListener { cellClickListener(it) }
        cell_1_1.setOnClickListener { cellClickListener(it) }
        cell_1_2.setOnClickListener { cellClickListener(it) }
        cell_2_0.setOnClickListener { cellClickListener(it) }
        cell_2_1.setOnClickListener { cellClickListener(it) }
        cell_2_2.setOnClickListener { cellClickListener(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.reset -> {
                viewModel.reset()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

private fun List<Move>.find(row: Int, col: Int): Move? =
    firstOrNull { it.position.col == col && it.position.row == row }

private fun Player.name(): String = symbol