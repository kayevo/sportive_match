package com.kayevo.sportive_match.adapter.data.generate_team_match

import com.kayevo.sportive_match.domain.generate_team_match.TeamMatch
import kotlinx.coroutines.flow.Flow

interface TeamMatchRepo {
    fun getTeamMatch(): Flow<TeamMatch?>

    fun setTeamMatch(teamMatch: TeamMatch)
}