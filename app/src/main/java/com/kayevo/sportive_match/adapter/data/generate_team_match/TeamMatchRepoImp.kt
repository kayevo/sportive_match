package com.kayevo.sportive_match.adapter.data.generate_team_match

import com.kayevo.sportive_match.domain.generate_team_match.TeamMatch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeamMatchRepoImp @Inject constructor(): TeamMatchRepo {
    override fun getTeamMatch(): Flow<TeamMatch?> = AppDB.getTeamMatch()

    override fun setTeamMatch(teamMatch: TeamMatch) {
        AppDB.setTeamMatch(teamMatch)
    }
}