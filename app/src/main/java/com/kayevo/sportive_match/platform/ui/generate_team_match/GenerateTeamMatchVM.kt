package com.kayevo.sportive_match.platform.ui.generate_team_match


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GenerateTeamMatchVM : ViewModel() {
    private val _uiState = MutableStateFlow(
        GenerateTeamMatchUiState(
            "",
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
        )
    )
    val uiState: StateFlow<GenerateTeamMatchUiState> = _uiState

    fun processIntent(intent: GenerateTeamMatchIntent) {
        when (intent) {
            is GenerateTeamMatchIntent.ChangeCandidates -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        sentCandidates = intent.sentCandidates,
                        candidates = splitCandidates(intent.sentCandidates)
                    )
                }
            }

            GenerateTeamMatchIntent.GenerateTeams -> {
                generateTeams()
            }

        }
    }

    private fun splitCandidates(candidates: String): List<String> = candidates
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    private fun generateTeams() {
        val team1 = mutableListOf<String>()
        val team2 = mutableListOf<String>()
        val nextCandidates = mutableListOf<String>()
        var addInTeam1 = true

        _uiState.value.candidates.forEach { candidate ->
            if (team2.size <= 4) {
                if (addInTeam1) team1.add(candidate)
                else team2.add(candidate)

                addInTeam1 = !addInTeam1
            } else {
                nextCandidates.add(candidate)
            }
        }


        _uiState.update { currentState ->
            currentState.copy(
                team1 = team1,
                team2 = team2,
                nextCandidates = nextCandidates
            )
        }
    }
}

data class GenerateTeamMatchUiState(
    val sentCandidates: String,
    val candidates: List<String>,
    val team1: List<String>,
    val team2: List<String>,
    val nextCandidates: List<String>,
)

sealed interface GenerateTeamMatchIntent {
    data class ChangeCandidates(val sentCandidates: String) : GenerateTeamMatchIntent
    data object GenerateTeams : GenerateTeamMatchIntent
}
