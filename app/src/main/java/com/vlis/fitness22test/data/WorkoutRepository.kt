package com.vlis.fitness22test.data

import com.vlis.fitness22test.domain.WorkoutPlan

interface WorkoutRepository {
   suspend fun getWorkoutPlan() : WorkoutPlan
}