package com.kayevo.sportive_match.adapter.data.generate_team_match

import com.kayevo.sportive_match.domain.generate_team_match.TeamMatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// TODO: Implement a database.
object AppDB {
    private val _teamMatch = MutableStateFlow<TeamMatch?>(null)

    fun getTeamMatch(): Flow<TeamMatch?> = _teamMatch.asStateFlow()

    fun setTeamMatch(teamMatch: TeamMatch) {
        _teamMatch.value = teamMatch
    }
}