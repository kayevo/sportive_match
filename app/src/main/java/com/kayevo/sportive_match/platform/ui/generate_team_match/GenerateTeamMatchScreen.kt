package com.kayevo.sportive_match.platform.ui.generate_team_match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kayevo.sportive_match.platform.navigation.Screen
import com.kayevo.sportive_match.platform.ui.theme.Sportive_matchTheme

@Preview(showBackground = true)
@Composable
fun GenerateTeamMatchScreenPreview() {
    Sportive_matchTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val navController = rememberNavController()
            GenerateTeamMatchScreen(
                navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun GenerateTeamMatchScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Generate random team match",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate(Screen.GenerateTeamMatch.route)
            }
        ) {
            Text("Generate")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}