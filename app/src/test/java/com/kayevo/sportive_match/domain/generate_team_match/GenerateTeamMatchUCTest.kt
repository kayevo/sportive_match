package com.kayevo.sportive_match.domain.generate_team_match

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
    fun test_GIVEN_no_candidates_WHEN_generate_match_THEN_return_empty_match() {
        // Given
        val candidates = emptyList<String>()
        val emptyRandomMatch = RandomMatch(
            team1 = emptyList(),
            team2 = emptyList(),
            nextCandidates = emptyList()
        )

        // When
        val randomMatch = useCase(
            candidates = candidates,
            random = mockRandomNumberGenerator
        )

        // Then
        assertEquals(expected = emptyRandomMatch, actual = randomMatch)
        verify(exactly = 0) { mockRandomNumberGenerator.nextInt(any(), any()) }
    }

    @Test
    fun test_GIVEN_one_candidate_WHEN_generate_match_THEN_return_valid_match() {
        // Given
        val randomInteger = 0
        val candidate = "Candidate 1"
        val candidates = mutableListOf(candidate)
        val expectedTeam1 = listOf(candidate)

        val validMatch = RandomMatch(
            team1 = expectedTeam1,
            team2 = emptyList(),
            nextCandidates = emptyList()
        )

        every {
            mockRandomNumberGenerator.nextInt(any(), any())
        } returns randomInteger

        // When
        val randomMatch = useCase(
            candidates = candidates,
            random = mockRandomNumberGenerator
        )

        // Then
        assertEquals(expected = validMatch, actual = randomMatch)
        verify(exactly = 1) { mockRandomNumberGenerator.nextInt(0, 1) }
    }

    @Test
    fun test_GIVEN_two_candidate_WHEN_generate_match_THEN_return_valid_match() {
        // Given
        val firstRandomInteger = 1
        val secondRandomInteger = 0
        val candidate1 = "Candidate 1"
        val candidate2 = "Candidate 2"
        val candidates = mutableListOf(candidate1, candidate2)
        val expectedTeam1 = listOf(candidate2)
        val expectedTeam2 = listOf(candidate1)

        val validMatch = RandomMatch(
            team1 = expectedTeam1,
            team2 = expectedTeam2,
            nextCandidates = emptyList()
        )

        every {
            mockRandomNumberGenerator.nextInt(0, 2)
        } returns firstRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 1)
        } returns secondRandomInteger

        // When
        val randomMatch = useCase(
            candidates = candidates,
            random = mockRandomNumberGenerator
        )

        // Then
        assertEquals(expected = validMatch, actual = randomMatch)
        verify(exactly = 2) { mockRandomNumberGenerator.nextInt(any(), any()) }
    }

    @Test
    fun test_GIVEN_complete_teams_WHEN_generate_match_THEN_return_valid_match() {
        // Given
        val firstRandomInteger = 3
        val secondRandomInteger = 0
        val thirdRandomInteger = 1
        val fourthRandomInteger = 0
        val candidate1 = "Candidate 1"
        val candidate2 = "Candidate 2"
        val candidate3 = "Candidate 3"
        val candidate4 = "Candidate 4"
        val candidates = mutableListOf(
            candidate1,
            candidate2,
            candidate3,
            candidate4
        )
        val expectedTeam1 = listOf(candidate4, candidate3)
        val expectedTeam2 = listOf(candidate1, candidate2)

        val validMatch = RandomMatch(
            team1 = expectedTeam1,
            team2 = expectedTeam2,
            nextCandidates = emptyList()
        )

        every {
            mockRandomNumberGenerator.nextInt(0, 4)
        } returns firstRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 3)
        } returns secondRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 2)
        } returns thirdRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 1)
        } returns fourthRandomInteger

        // When
        val randomMatch = useCase(
            candidates = candidates,
            random = mockRandomNumberGenerator,
            teamSize = 2
        )

        // Then
        assertEquals(expected = validMatch, actual = randomMatch)
        verify(exactly = 4) { mockRandomNumberGenerator.nextInt(any(), any()) }
    }

    @Test
    fun test_GIVEN_exists_next_candidates_WHEN_generate_match_THEN_return_valid_match() {
        // Given
        val firstRandomInteger = 3
        val secondRandomInteger = 0
        val thirdRandomInteger = 1
        val fourthRandomInteger = 0
        val candidate1 = "Candidate 1"
        val candidate2 = "Candidate 2"
        val candidate3 = "Candidate 3"
        val candidate4 = "Candidate 4"
        val candidate5 = "Candidate 5"
        val candidates = mutableListOf(
            candidate1,
            candidate2,
            candidate3,
            candidate4,
            candidate5
        )
        val expectedTeam1 = listOf(candidate4, candidate3)
        val expectedTeam2 = listOf(candidate1, candidate2)
        val expectedNextCandidates = listOf(candidate5)

        val validMatch = RandomMatch(
            team1 = expectedTeam1,
            team2 = expectedTeam2,
            nextCandidates = expectedNextCandidates
        )

        every {
            mockRandomNumberGenerator.nextInt(0, 5)
        } returns firstRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 4)
        } returns secondRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 3)
        } returns thirdRandomInteger

        every {
            mockRandomNumberGenerator.nextInt(0, 2)
        } returns fourthRandomInteger

        // When
        val randomMatch = useCase(
            candidates = candidates,
            random = mockRandomNumberGenerator,
            teamSize = 2
        )

        // Then
        assertEquals(expected = validMatch, actual = randomMatch)
        verify(exactly = 4) { mockRandomNumberGenerator.nextInt(any(), any()) }
    }
}