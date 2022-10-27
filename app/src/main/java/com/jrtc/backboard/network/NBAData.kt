package com.jrtc.backboard.network

data class TodaysGames (
    val scoreboard: Scoreboard
)

data class Scoreboard (
    val games: List<Game>
)

data class Game (
    val gameId: String,
    val gameStatus: Int,
    val gameStatusText: String,
    val gameTimeUTC: String,
    val homeTeam: Team,
    val awayTeam: Team
)

data class Team (
    val teamId: Int,
    val teamName: String,
    val teamCity: String,
    val teamTricode: String,
    val wins: Int,
    val losses: Int,
    val score: Int,
)