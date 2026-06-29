package com.kayevo.sportive_match.platform.ui.generate_team_match


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.sportive_match.domain.generate_team_match.GenerateTeamMatchUC
import com.kayevo.sportive_match.domain.generate_team_match.GetTeamMatchUC
import com.kayevo.sportive_match.domain.generate_team_match.RandomNumberGenerator
import com.kayevo.sportive_match.domain.generate_team_match.SetTeamMatchUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class GenerateTeamMatchVM @Inject constructor(
    private val getTeamMatch: GetTeamMatchUC,
    private val generateTeamMatch: GenerateTeamMatchUC,
    private val setTeamMatch: SetTeamMatchUC
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

    init {
        val getTeamMatchJob = viewModelScope.launch {
            getTeamMatch().collect { randomMatch ->
                randomMatch?.let {
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
    }

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
                // TODO move to IO thread.
                val randomMatch = generateTeamMatch(
                    candidates = _uiState.value.candidates,
                    random = RandomNumberGenerator()
                )

                setTeamMatch(randomMatch)
            }
        }
    }

    private fun splitCandidates(candidates: String): List<String> = candidates
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
}