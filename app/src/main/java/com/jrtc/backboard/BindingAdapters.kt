package com.jrtc.backboard

import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.jrtc.backboard.network.Game
import com.jrtc.backboard.network.Player
import com.jrtc.backboard.network.Post
import com.jrtc.backboard.ui.games.GameListAdapter
import com.jrtc.backboard.ui.highlights.HighlightListAdapter
import com.jrtc.backboard.ui.tweets.TweetListAdapter
import java.text.DecimalFormat

/**
 * Updates the data shown in the games [RecyclerView].
 */
@BindingAdapter("gamesListData")
fun bindGamesRecyclerView(recyclerView: RecyclerView, data: List<Game>?) {
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}

/**
 * Updates the data shown in the tweets [RecyclerView].
 */
@BindingAdapter("tweetsListData")
fun bindTweetsRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as TweetListAdapter
    adapter.submitList(data)
}

/**
 * Updates the data shown in the highlights [RecyclerView].
 */
@BindingAdapter("highlightsListData")
fun bindHighlightRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as HighlightListAdapter
    adapter.submitList(data)
}

/**
 * Updates the data shown in the boxscore [ConstraintLayout].
 */
@BindingAdapter(value = ["boxscoreData", "teamType"], requireAll = true)
fun bindBoxscoreStats(
    constraintLayout: ConstraintLayout,
    players: List<Player>?,
    isAwayTeam: Boolean
) {
    // Gets the corresponding table id based on home or away team.
    val tableFixed =
        if (isAwayTeam)
            constraintLayout.findViewById<TableLayout>(R.id.away_team_fixed_player_table_layout)
        else
            constraintLayout.findViewById(R.id.home_team_fixed_player_table_layout)
    if (tableFixed.childCount == 1) {
        val tableScroll =
            if (isAwayTeam)
                constraintLayout.findViewById<TableLayout>(R.id.away_team_scrollable_stats_table_layout)
            else
                constraintLayout.findViewById(R.id.home_team_scrollable_stats_table_layout)

        // Sets the stats for each player's row
        players?.forEach {
            // Extracts the minutes digit value from the field and checks if they played
            val minutesValue = it.statistics.minutesCalculated.filter(Char::isDigit).toInt()
            if (minutesValue > 0) {
                val fixedRow = TableRow(tableFixed.context)
                val scrollRow = TableRow(tableScroll.context)
                val linearLayout = LinearLayout(tableFixed.context)

                val playerName = MaterialTextView(linearLayout.context)
                playerName.text = it.nameI
                // Sets the right and bottom margins for the player column
                playerName.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                (playerName.layoutParams as LinearLayout.LayoutParams).setMargins(
                    0,
                    0,
                    12,
                    12
                )
                linearLayout.addView(playerName)

                val minutes = MaterialTextView(fixedRow.context)
                minutes.text = minutesValue.toString()
                // Sets the bottom margin for the entire row
                minutes.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                (minutes.layoutParams as TableRow.LayoutParams).setMargins(
                    0,
                    0,
                    0,
                    12
                )
                val points = MaterialTextView(fixedRow.context)
                points.text = it.statistics.points.toString()
                val rebounds = MaterialTextView(fixedRow.context)
                rebounds.text = it.statistics.reboundsTotal.toString()
                val assists = MaterialTextView(fixedRow.context)
                assists.text = it.statistics.assists.toString()
                val steals = MaterialTextView(fixedRow.context)
                steals.text = it.statistics.steals.toString()
                val blocks = MaterialTextView(fixedRow.context)
                blocks.text = it.statistics.blocks.toString()
                val blocksReceived = MaterialTextView(fixedRow.context)
                blocksReceived.text = it.statistics.blocksReceived.toString()
                val offensiveRebounds = MaterialTextView(fixedRow.context)
                offensiveRebounds.text = it.statistics.reboundsOffensive.toString()
                val defensiveRebounds = MaterialTextView(fixedRow.context)
                defensiveRebounds.text = it.statistics.reboundsDefensive.toString()
                val fieldGoalsMade = MaterialTextView(fixedRow.context)
                fieldGoalsMade.text = it.statistics.fieldGoalsMade.toString()
                val fieldGoalsAttempted = MaterialTextView(fixedRow.context)
                fieldGoalsAttempted.text = it.statistics.fieldGoalsAttempted.toString()
                val fieldGoalsPercentage = MaterialTextView(fixedRow.context)
                fieldGoalsPercentage.text = convertDecimalToPercent(
                    it.statistics.fieldGoalsAttempted,
                    it.statistics.fieldGoalsPercentage
                )
                val threePointersMade = MaterialTextView(fixedRow.context)
                threePointersMade.text = it.statistics.threePointersMade.toString()
                val threePointersAttempted = MaterialTextView(fixedRow.context)
                threePointersAttempted.text = it.statistics.threePointersAttempted.toString()
                val threePointersPercentage = MaterialTextView(fixedRow.context)
                threePointersPercentage.text = convertDecimalToPercent(
                    it.statistics.threePointersAttempted,
                    it.statistics.threePointersPercentage
                )
                val freeThrowsMade = MaterialTextView(fixedRow.context)
                freeThrowsMade.text = it.statistics.freeThrowsMade.toString()
                val freeThrowsAttempted = MaterialTextView(fixedRow.context)
                freeThrowsAttempted.text = it.statistics.freeThrowsAttempted.toString()
                val freeThrowsPercentage = MaterialTextView(fixedRow.context)
                freeThrowsPercentage.text = convertDecimalToPercent(
                    it.statistics.freeThrowsAttempted,
                    it.statistics.freeThrowsPercentage
                )
                val personalFouls = MaterialTextView(fixedRow.context)
                personalFouls.text = it.statistics.foulsPersonal.toString()
                val turnovers = MaterialTextView(fixedRow.context)
                turnovers.text = it.statistics.turnovers.toString()
                val plusMinus = MaterialTextView(fixedRow.context)
                plusMinus.text = it.statistics.plusMinusPoints.toInt().toString()

                // Adds the new views to the layout
                fixedRow.addView(linearLayout)
                scrollRow.addView(minutes)
                scrollRow.addView(points)
                scrollRow.addView(rebounds)
                scrollRow.addView(assists)
                scrollRow.addView(steals)
                scrollRow.addView(blocks)
                scrollRow.addView(blocksReceived)
                scrollRow.addView(offensiveRebounds)
                scrollRow.addView(defensiveRebounds)
                scrollRow.addView(fieldGoalsMade)
                scrollRow.addView(fieldGoalsAttempted)
                scrollRow.addView(fieldGoalsPercentage)
                scrollRow.addView(threePointersMade)
                scrollRow.addView(threePointersAttempted)
                scrollRow.addView(threePointersPercentage)
                scrollRow.addView(freeThrowsMade)
                scrollRow.addView(freeThrowsAttempted)
                scrollRow.addView(freeThrowsPercentage)
                scrollRow.addView(personalFouls)
                scrollRow.addView(turnovers)
                scrollRow.addView(plusMinus)
                tableFixed.addView(fixedRow)
                tableScroll.addView(scrollRow)
            }
        }
    }
}

/**
 * Converts a decimal to a percent with rounding. Returns "-" if the player didn't attempt
 * any shots.
 */
private fun convertDecimalToPercent(attempted: Int, decimal: Double): String {
    return if (attempted == 0) {
        "-"
    } else {
        DecimalFormat("##%").format(decimal)
    }
}
