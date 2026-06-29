package com.kayevo.sportive_match.domain.generate_team_match

import com.kayevo.sportive_match.adapter.data.generate_team_match.TeamMatchRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamMatchUC @Inject constructor(private val teamMatchRepo: TeamMatchRepo) {
    operator fun invoke(): Flow<TeamMatch?> = teamMatchRepo.getTeamMatch()
}