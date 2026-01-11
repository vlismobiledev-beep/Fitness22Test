package com.vlis.fitness22test.domain

import com.vlis.fitness22test.data.WorkoutRepository
import javax.inject.Inject

// GetWorkoutPlanUseCase.kt
class GetWorkoutPlanUseCase @Inject constructor(
    private val repository: WorkoutRepository
) {

    suspend operator fun invoke(): Result<WorkoutPlan> = runCatching {
        repository.getWorkoutPlan()
    }
}