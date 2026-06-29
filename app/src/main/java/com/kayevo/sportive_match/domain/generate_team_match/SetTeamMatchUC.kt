package com.kayevo.sportive_match.domain.generate_team_match

import com.kayevo.sportive_match.adapter.data.generate_team_match.TeamMatchRepo
import javax.inject.Inject

class SetTeamMatchUC @Inject constructor(private val teamMatchRepo: TeamMatchRepo) {
    operator fun invoke(teamMatch: TeamMatch) {
        teamMatchRepo.setTeamMatch(teamMatch)
    }
}