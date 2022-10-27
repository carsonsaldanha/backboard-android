package com.jrtc.backboard.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.databinding.GamesListItemBinding
import com.jrtc.backboard.getTeamDrawableLogo
import com.jrtc.backboard.network.Game

class GameListAdapter(private val clickListener: GameListener) :
    ListAdapter<Game, GameListAdapter.GameViewHolder>(DiffCallback) {

    class GameViewHolder(var binding: GamesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: GameListener, game: Game) {
            binding.game = game
            binding.clickListener = clickListener
            binding.awayTeamLogoImageView.setImageResource(getTeamDrawableLogo(game.awayTeam.teamID))
            binding.homeTeamLogoImageView.setImageResource(getTeamDrawableLogo(game.homeTeam.teamID))
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Game>() {

        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameID == newItem.gameID
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameStatus == newItem.gameStatus &&
                    oldItem.gameStatusText == newItem.gameStatusText &&
                    oldItem.gameTimeUTC == newItem.gameTimeUTC &&
                    oldItem.homeTeam == newItem.homeTeam &&
                    oldItem.awayTeam == newItem.awayTeam
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(
            GamesListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(clickListener, game)
    }
}

class GameListener(val clickListener: (game: Game) -> Unit) {
    fun onClick(game: Game) = clickListener(game)
}
