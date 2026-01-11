package com.vlis.fitness22test.di

import com.vlis.fitness22test.data.ResourceProvider
import com.vlis.fitness22test.data.WorkoutRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    fun provideWorkoutRepository(
        resourceProvider: ResourceProvider,
    ): WorkoutRepositoryImpl {
        return WorkoutRepositoryImpl(
            resourceProvider,
            json = Json,
            "workouts.json"
        )
    }
}