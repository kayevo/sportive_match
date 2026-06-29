package com.kayevo.sportive_match.platform.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object GenerateTeamMatch : Screen("generate_team_match")
}