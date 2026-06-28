package com.kayevo.sportive_match.domain.generate_team_match

import com.kayevo.sportive_match.platform.ui.generate_team_match.RandomMatch
import com.kayevo.sportive_match.platform.ui.generate_team_match.RandomNumberGenerator
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Before
import kotlin.test.assertEquals

class GenerateTeamMatchUCTest {
    private lateinit var useCase: GenerateTeamMatchUC
    private lateinit var mockRandomNumberGenerator: RandomNumberGenerator

    @Before
    fun setUp() {
        useCase = GenerateTeamMatchUC()
        mockRandomNumberGenerator = mockk()
    }

    @Test
    fun given_no_candidates_when_generate_match_then_return_empty_match() {
        // Given
        val randomNumberGenerator = RandomNumberGenerator()
        val randomInteger = 0
        val candidates = emptyList<String>()
        val emptyRandomMatch = RandomMatch(
            team1 = emptyList(),
            team2 = emptyList(),
            nextCandidates = emptyList()
        )

        every {
            mockRandomNumberGenerator.nextInt(any(), any())
        } returns randomInteger

        // When
        val randomMatch = useCase(
            candidates = candidates,
            random = randomNumberGenerator
        )

        // Then
        assertEquals(expected = emptyRandomMatch, actual = randomMatch)
    }
}