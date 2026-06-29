package com.kayevo.sportive_match.platform.ui.generate_team_match

sealed interface GenerateTeamMatchScreenIntent {
    data class ChangeCandidates(val sentCandidates: String) : GenerateTeamMatchScreenIntent
    data object GenerateTeams : GenerateTeamMatchScreenIntent
}