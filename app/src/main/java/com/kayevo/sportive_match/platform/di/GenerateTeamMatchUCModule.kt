package com.kayevo.sportive_match.platform.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GenerateTeamMatchUCModule {
    /* Code example
        @Binds
        abstract fun bindUseCase(
            useCase: GenerateTeamMatchUC // Interface implementation
        ): GenerateTeamMatchUCInterface // Interface
     */
}