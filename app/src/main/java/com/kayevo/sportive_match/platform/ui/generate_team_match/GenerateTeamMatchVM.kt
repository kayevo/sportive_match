package com.kayevo.sportive_match.platform.ui.generate_team_match


import androidx.lifecycle.ViewModel
import com.kayevo.sportive_match.domain.generate_team_match.GenerateTeamMatchUC
import com.kayevo.sportive_match.domain.generate_team_match.RandomNumberGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class GenerateTeamMatchVM @Inject constructor(
    private val generateTeamMatchUseCase: GenerateTeamMatchUC
) : ViewModel() {
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

    fun processIntent(intent: GenerateTeamMatchScreenIntent) {
        when (intent) {
            is GenerateTeamMatchScreenIntent.ChangeCandidates -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        sentCandidates = intent.sentCandidates,
                        candidates = splitCandidates(intent.sentCandidates)
                    )
                }
            }

            GenerateTeamMatchScreenIntent.GenerateTeams -> {
                val randomMatch = generateTeamMatchUseCase(
                    candidates = _uiState.value.candidates,
                    random = RandomNumberGenerator()
                )

                _uiState.update { currentState ->
                    currentState.copy(
                        team1 = randomMatch.team1,
                        team2 = randomMatch.team2,
                        nextCandidates = randomMatch.nextCandidates
                    )
                }
            }
        }
    }

    private fun splitCandidates(candidates: String): List<String> = candidates
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
}