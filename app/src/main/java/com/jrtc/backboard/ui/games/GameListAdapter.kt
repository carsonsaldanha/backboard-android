package com.jrtc.backboard.ui.games

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.GamesListItemBinding
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.formatTo
import com.jrtc.backboard.network.getTeamDrawableLogo
import com.jrtc.backboard.network.toDate

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class GameListAdapter(private val clickListener: GameListener) :
    ListAdapter<Game, GameListAdapter.GameViewHolder>(DiffCallback) {

    class GameViewHolder(var binding: GamesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: GameListener, game: Game) {
            binding.game = game
            binding.clickListener = clickListener
            binding.executePendingBindings()

            // Sets the team logos
            binding.awayTeamLogoImageView.setImageResource(getTeamDrawableLogo(game.awayTeam.teamId))
            binding.homeTeamLogoImageView.setImageResource(getTeamDrawableLogo(game.homeTeam.teamId))

            // Displays the game time in the user's current time zone and only displays the score
            // if the game has started or finished
            if (game.gameStatus == 1) {
                binding.gameStatusTextView.text = game.gameTimeUTC.toDate().formatTo("h:mm a")
            } else {
                // Splits the quarter and time on separate lines
                binding.gameStatusTextView.text = game.gameStatusText.replace(" ", "\n")
                binding.awayTeamScoreTextView.text = game.awayTeam.score.toString()
                binding.homeTeamScoreTextView.text = game.homeTeam.score.toString()
            }
            // Updates the score text styling and displays the win symbol for a finished game
            if (game.gameStatus == 3) {
                // Fix for the occasional bug in the API that adds a lot of spaces to "Final" text
                binding.gameStatusTextView.text = game.gameStatusText.replace(" ", "")
                val textColor: ColorStateList = binding.gameStatusTextView.textColors
                // Checks whether away or home team won
                if (game.awayTeam.score > game.homeTeam.score) {
                    binding.homeTeamScoreTextView.setTextColor(textColor)
                    binding.awayTeamWinSymbolLayout.visibility = View.VISIBLE
                } else {
                    binding.awayTeamScoreTextView.setTextColor(textColor)
                    binding.homeTeamWinSymbolLayout.visibility = View.VISIBLE
                }
            }
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameId == newItem.gameId
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameStatus == newItem.gameStatus &&
                    oldItem.gameStatusText == newItem.gameStatusText &&
                    oldItem.gameTimeUTC == newItem.gameTimeUTC &&
                    oldItem.homeTeam == newItem.homeTeam &&
                    oldItem.awayTeam == newItem.awayTeam
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(GamesListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(clickListener, game)
    }

}

class GameListener(val clickListener: (cardView: View, game: Game) -> Unit) {
    fun onClick(cardView: View, game: Game) = clickListener(cardView, game)
}
