package com.kayevo.sportive_match.domain.generate_team_match

data class RandomMatch(
    val team1: List<String>,
    val team2: List<String>,
    val nextCandidates: List<String>
)