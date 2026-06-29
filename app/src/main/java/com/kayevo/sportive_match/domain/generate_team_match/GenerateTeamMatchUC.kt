package com.kayevo.sportive_match.domain.generate_team_match

import javax.inject.Inject

class GenerateTeamMatchUC @Inject constructor() {
    operator fun invoke(
        candidates: List<String>,
        random: RandomNumberGenerator,
        teamSize: Int = 5
    ): TeamMatch {
        val candidatesPool = candidates.toMutableList()
        val team1 = mutableListOf<String>()
        val team2 = mutableListOf<String>()
        val candidatesCount = minOf(candidatesPool.size, teamSize * 2)

        repeat(candidatesCount) { count ->
            val randomIndex = random.nextInt(0, candidatesPool.size)
            val candidate = candidatesPool.removeAt(randomIndex)

            if (count % 2 == 0) team1.add(candidate)
            else team2.add(candidate)
        }

        return TeamMatch(
            candidates = candidates.toList(),
            team1 = team1,
            team2 = team2,
            nextCandidates = candidatesPool
        )
    }
}