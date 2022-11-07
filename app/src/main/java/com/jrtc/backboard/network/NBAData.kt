package com.jrtc.backboard.network

import com.jrtc.backboard.R

/**
 * This data class defines [TodaysGamesResponse] which includes the scoreboard.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class TodaysGamesResponse(
    val scoreboard: Scoreboard
)

/**
 * TODO
 */
data class BoxscoreResponse(
    val game: Game
)

/**
 * This data class defines [Scoreboard] which includes the list of games.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Scoreboard(
    val games: List<Game>
)

/**
 * This data class defines [Game] which includes the game id, game status, game time, home team,
 * and away team.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Game(
    val gameId: String,
    val gameStatus: Int,
    val gameStatusText: String,
    val gameTimeUTC: String,
    val homeTeam: Team,
    val awayTeam: Team
)

/**
 * This data class defines [Team] which includes the team id, team name, team city, team tricode,
 * wins, losses, and score.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Team(
    val teamId: Int,
    val teamName: String,
    val teamCity: String,
    val teamTricode: String,
    val wins: Int?,
    val losses: Int?,
    val score: Int,
    val players: List<Player>?
)

data class Player(
    val personId: Int,
    val statistics: Statistics,
    val nameI: String
)

data class Statistics(
    val assists: Int,
    val blocks: Int,
    val blocksReceived: Int,
    val fieldGoalsAttempted: Int,
    val fieldGoalsMade: Int,
    val fieldGoalsPercentage: Double,
    val foulsPersonal: Int,
    val freeThrowsAttempted: Int,
    val freeThrowsMade: Int,
    val freeThrowsPercentage: Double,
    val minutesCalculated: String,
    val plusMinusPoints: Double,
    val points: Int,
    val reboundsDefensive: Int,
    val reboundsOffensive: Int,
    val reboundsTotal: Int,
    val steals: Int,
    val threePointersAttempted: Int,
    val threePointersMade: Int,
    val threePointersPercentage: Double,
    val turnovers: Int
)

/**
 * Returns the drawable resource int id that maps to the specified team id.
 */
fun getTeamDrawableLogo(teamId: Int): Int {
    when (teamId) {
        1610612737 -> return R.drawable.logo_1610612737
        1610612738 -> return R.drawable.logo_1610612738
        1610612739 -> return R.drawable.logo_1610612739
        1610612740 -> return R.drawable.logo_1610612740
        1610612741 -> return R.drawable.logo_1610612741
        1610612742 -> return R.drawable.logo_1610612742
        1610612743 -> return R.drawable.logo_1610612743
        1610612744 -> return R.drawable.logo_1610612744
        1610612745 -> return R.drawable.logo_1610612745
        1610612746 -> return R.drawable.logo_1610612746
        1610612747 -> return R.drawable.logo_1610612747
        1610612748 -> return R.drawable.logo_1610612748
        1610612749 -> return R.drawable.logo_1610612749
        1610612750 -> return R.drawable.logo_1610612750
        1610612751 -> return R.drawable.logo_1610612751
        1610612752 -> return R.drawable.logo_1610612752
        1610612753 -> return R.drawable.logo_1610612753
        1610612754 -> return R.drawable.logo_1610612754
        1610612755 -> return R.drawable.logo_1610612755
        1610612756 -> return R.drawable.logo_1610612756
        1610612757 -> return R.drawable.logo_1610612757
        1610612758 -> return R.drawable.logo_1610612758
        1610612759 -> return R.drawable.logo_1610612759
        1610612760 -> return R.drawable.logo_1610612760
        1610612761 -> return R.drawable.logo_1610612761
        1610612762 -> return R.drawable.logo_1610612762
        1610612763 -> return R.drawable.logo_1610612763
        1610612764 -> return R.drawable.logo_1610612764
        1610612765 -> return R.drawable.logo_1610612765
        1610612766 -> return R.drawable.logo_1610612766
    }
    return R.drawable.logo_nba
}
