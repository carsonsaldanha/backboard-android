package com.jrtc.backboard.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrtc.backboard.R
import com.jrtc.backboard.databinding.GamesListItemBinding
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

        private fun getTeamDrawableLogo(teamID: Int): Int {
            when (teamID) {
                1610612737 -> {
                    return R.drawable.logo_1610612737
                }
                1610612738 -> {
                    return R.drawable.logo_1610612738
                }
                1610612739 -> {
                    return R.drawable.logo_1610612739
                }
                1610612740 -> {
                    return R.drawable.logo_1610612740
                }
                1610612741 -> {
                    return R.drawable.logo_1610612741
                }
                1610612742 -> {
                    return R.drawable.logo_1610612742
                }
                1610612743 -> {
                    return R.drawable.logo_1610612743
                }
                1610612744 -> {
                    return R.drawable.logo_1610612744
                }
                1610612745 -> {
                    return R.drawable.logo_1610612745
                }
                1610612746 -> {
                    return R.drawable.logo_1610612746
                }
                1610612747 -> {
                    return R.drawable.logo_1610612747
                }
                1610612748 -> {
                    return R.drawable.logo_1610612748
                }
                1610612749 -> {
                    return R.drawable.logo_1610612749
                }
                1610612750 -> {
                    return R.drawable.logo_1610612750
                }
                1610612751 -> {
                    return R.drawable.logo_1610612751
                }
                1610612752 -> {
                    return R.drawable.logo_1610612752
                }
                1610612753 -> {
                    return R.drawable.logo_1610612753
                }
                1610612754 -> {
                    return R.drawable.logo_1610612754
                }
                1610612755 -> {
                    return R.drawable.logo_1610612755
                }
                1610612756 -> {
                    return R.drawable.logo_1610612756
                }
                1610612757 -> {
                    return R.drawable.logo_1610612757
                }
                1610612758 -> {
                    return R.drawable.logo_1610612758
                }
                1610612759 -> {
                    return R.drawable.logo_1610612759
                }
                1610612760 -> {
                    return R.drawable.logo_1610612760
                }
                1610612761 -> {
                    return R.drawable.logo_1610612761
                }
                1610612762 -> {
                    return R.drawable.logo_1610612762
                }
                1610612763 -> {
                    return R.drawable.logo_1610612763
                }
                1610612764 -> {
                    return R.drawable.logo_1610612764
                }
                1610612765 -> {
                    return R.drawable.logo_1610612765
                }
                1610612766 -> {
                    return R.drawable.logo_1610612766
                }
            }
            return 0
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
    ): GameListAdapter.GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameListAdapter.GameViewHolder(
            GamesListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameListAdapter.GameViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(clickListener, game)
    }
}

class GameListener(val clickListener: (game: Game) -> Unit) {
    fun onClick(game: Game) = clickListener(game)
}
