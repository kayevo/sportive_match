package com.kayevo.sportive_match

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kayevo.sportive_match.platform.navigation.Screen
import com.kayevo.sportive_match.platform.ui.generate_team_match.GenerateTeamMatchScreen
import com.kayevo.sportive_match.platform.ui.generate_team_match.GenerateTeamMatchVM
import com.kayevo.sportive_match.platform.ui.home.HomeScreen
import com.kayevo.sportive_match.platform.ui.theme.Sportive_matchTheme

class MainActivity : ComponentActivity() {
    private val generateTeamMatchVM: GenerateTeamMatchVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sportive_matchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(
                                navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable(Screen.GenerateTeamMatch.route) {
                            GenerateTeamMatchScreen(
                                navController,
                                generateTeamMatchVM,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}