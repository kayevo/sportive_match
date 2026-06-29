package com.kayevo.sportive_match.platform.ui.generate_team_match

data class GenerateTeamMatchUiState(
    val sentCandidates: String,
    val candidates: List<String>,
    val team1: List<String>,
    val team2: List<String>,
    val nextCandidates: List<String>,
)