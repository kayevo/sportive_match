package com.kayevo.sportive_match.platform.ui.generate_team_match

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kayevo.sportive_match.R
import com.kayevo.sportive_match.adapter.data.generate_team_match.TeamMatchRepoImp
import com.kayevo.sportive_match.domain.generate_team_match.GenerateTeamMatchUC
import com.kayevo.sportive_match.domain.generate_team_match.GetTeamMatchUC
import com.kayevo.sportive_match.domain.generate_team_match.SetTeamMatchUC
import com.kayevo.sportive_match.platform.ui.theme.Sportive_matchTheme

val blueBallEmoji = "\uD83D\uDD35"
val greenBallEmoji = "\uD83D\uDFE2"
val nextEmoji = "⏭"

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun GenerateTeamMatchScreenPreview() {
    Sportive_matchTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val navController = rememberNavController()
            val teamMatchRepository = TeamMatchRepoImp()
            val viewModel = GenerateTeamMatchVM(
                getTeamMatch = GetTeamMatchUC(teamMatchRepository),
                generateTeamMatch = GenerateTeamMatchUC(),
                setTeamMatch = SetTeamMatchUC(teamMatchRepository)
            )

            GenerateTeamMatchScreen(
                navController = navController,
                processIntent = viewModel::processIntent,
                sentCandidates = "Name A, Name B, Name C, Name D, Name E",
                candidates = listOf("Name A", "Name B", "Name C", "Name D", "Name E"),
                team1 = listOf("Name A", "Name B"),
                team2 = listOf("Name C", "Name D"),
                nextCandidates = listOf("Name A", "Name B", "Name C", "Name D", "Name E"),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun GenerateTeamMatchScreen(
    navController: NavController,
    processIntent: (GenerateTeamMatchScreenIntent) -> Unit,
    sentCandidates: String,
    candidates: List<String>,
    team1: List<String>,
    team2: List<String>,
    nextCandidates: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row (modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = "Back button",
                    modifier = Modifier.width(30.dp)
                )
            }

        }

        Row (modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier
            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_handshake),
                contentDescription = "Handshake icon",
                modifier = Modifier
                    .width(50.dp)
                    .padding(10.dp)
            )}

            Text(
                text = "Generate Random \nTeam Match",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = sentCandidates,
            onValueChange = { newSentCandidates ->
                processIntent(GenerateTeamMatchScreenIntent.ChangeCandidates(newSentCandidates))
            },
            label = { Text("Sent candidates") },
            placeholder = {
                Text("John, Mary, Peter, Alice...")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Candidates",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    items(candidates) { candidate ->
                        Text("• $candidate")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TeamCard(
                title = "$blueBallEmoji Team 1",
                players = team1,
                modifier = Modifier.weight(1f)
            )
            TeamCard(
                title = "$greenBallEmoji Team 2",
                players = team2,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "$nextEmoji Next candidates.",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    items(nextCandidates) { player ->
                        Text("• $player")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                processIntent(GenerateTeamMatchScreenIntent.GenerateTeams)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text("Generate Teams")
        }
    }
}

@Composable
private fun TeamCard(
    title: String,
    players: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            players.forEach { player ->
                Text("• $player")
            }
        }
    }
}