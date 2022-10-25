package com.jrtc.backboard.network

import com.squareup.moshi.Json

data class TodaysGames (
    val scoreboard: Scoreboard
)

data class Scoreboard (
    val games: List<Game>
)

data class Game (
    @Json(name = "gameId")
    val gameID: String,
    val gameStatus: Int,
    val gameStatusText: String,
    val gameTimeUTC: String,
    val homeTeam: Team,
    val awayTeam: Team
)

data class Team (
    @Json(name = "teamId")
    val teamID: Int,
    val teamName: String,
    val teamCity: String,
    val teamTricode: String,
    val wins: Int,
    val losses: Int,
    val score: Int,
)