package com.kayevo.sportive_match.platform.di.adapter

import com.kayevo.sportive_match.adapter.data.generate_team_match.TeamMatchRepo
import com.kayevo.sportive_match.adapter.data.generate_team_match.TeamMatchRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {
    // For API calls.
}