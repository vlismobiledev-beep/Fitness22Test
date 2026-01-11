package com.vlis.fitness22test.di

import com.vlis.fitness22test.data.ResourceProvider
import com.vlis.fitness22test.data.WorkoutRepository
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
    fun providesJson() = Json

    @Provides
    fun provideWorkoutRepository(
        resourceProvider: ResourceProvider,
        json: Json
    ): WorkoutRepository {
        return WorkoutRepositoryImpl(
            resourceProvider,
            json = json,
            "workouts.json"
        )
    }
}