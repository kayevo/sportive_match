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
        // TODO generate teams.
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
