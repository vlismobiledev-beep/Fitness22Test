package com.vlis.fitness22test.data

import com.vlis.fitness22test.domain.DailyWorkoutModel
import com.vlis.fitness22test.domain.Day
import com.vlis.fitness22test.domain.ExerciseModel
import com.vlis.fitness22test.domain.MuscleGroup
import com.vlis.fitness22test.domain.WorkoutPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepositoryImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val json: Json,
    private val fileName: String
) : WorkoutRepository {

    private var cachedPlan: WorkoutPlan? = null
    private val mutex = Mutex()

    override suspend fun getWorkoutPlan(): WorkoutPlan = withContext(Dispatchers.IO) {
        cachedPlan ?: mutex.withLock {
            cachedPlan ?: run {
                val text = resourceProvider.provideAsset(fileName)
                val response = json.decodeFromString<WorkoutsResponse>(text)

                val workouts = response.workouts?.mapIndexedNotNull { index, dailyDto ->
                    dailyDto?.toDomain(index)
                } ?: emptyList()

                WorkoutPlan(workouts).also { cachedPlan = it }
            }
        }
    }
}

// Extension functions for cleaner mapping
private fun WorkoutsResponse.Workout.toDomain(index: Int): DailyWorkoutModel {
    return DailyWorkoutModel(
        day = Day(this.day ?: (index + 1)),
        exercises = this.workout?.mapNotNull { it?.toDomain() } ?: emptyList()
    )
}

private fun WorkoutsResponse.Workout.Workout.toDomain(): ExerciseModel? {
    // Return null if mandatory fields are missing (Validation)
    val muscleGroup = muscleGroup.mapMuscleGroup() ?: return null

    return ExerciseModel(
        amountOfSets = amountOfSets ?: return null,
        id = exerciseId ?: return null,
        name = exerciseName ?: return null,
        thumbnail = exerciseThumbnail,
        muscleGroup = muscleGroup,
        repRange = repRange ?: "",
        weightAmount = weightAmount ?: ""
    )
}

private fun String?.mapMuscleGroup() = MuscleGroup.entries.firstOrNull { it.id == this }
