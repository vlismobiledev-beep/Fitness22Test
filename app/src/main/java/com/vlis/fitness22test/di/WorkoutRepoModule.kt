package com.vlis.fitness22test.di

import com.vlis.fitness22test.data.WorkoutRepository
import com.vlis.fitness22test.data.WorkoutRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class WorkoutRepoModule {

    @Binds
    abstract fun bindWorkoutRepository(impl: WorkoutRepositoryImpl): WorkoutRepository
}