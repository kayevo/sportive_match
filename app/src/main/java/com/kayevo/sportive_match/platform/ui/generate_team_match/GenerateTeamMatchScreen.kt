package com.kayevo.sportive_match.platform.ui.generate_team_match

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kayevo.sportive_match.platform.ui.theme.Sportive_matchTheme

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun GenerateTeamMatchScreenPreview() {
    Sportive_matchTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val navController = rememberNavController()
            GenerateTeamMatchScreen(
                navController,
                GenerateTeamMatchVM(),
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun GenerateTeamMatchScreen(
    navController: NavController,
    viewModel: ViewModel,
    modifier: Modifier = Modifier
) {
    val candidates = "Name A, Name B"
    val team1 = listOf("Name 1", "Name 2")
    val team2 = listOf("Name 3")
    val nextToPlay = listOf("Name 4")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Generate Random Team Match",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = candidates,
            onValueChange = {},
            label = { Text("Candidates") },
            placeholder = {
                Text("John, Mary, Peter, Alice...")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TeamCard(
                title = "🔵 Team 1",
                players = team1,
                modifier = Modifier.weight(1f)
            )

            TeamCard(
                title = "🔴 Team 2",
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
                    text = "⏭ Next to Play",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                nextToPlay.forEach { player ->
                    Text("• $player")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // TODO Generate teams
            },
            modifier = Modifier.fillMaxWidth()
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