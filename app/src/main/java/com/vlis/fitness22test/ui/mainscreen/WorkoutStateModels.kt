package com.vlis.fitness22test.ui.mainscreen

import com.vlis.fitness22test.domain.Day
import com.vlis.fitness22test.domain.ExerciseModel
import com.vlis.fitness22test.domain.WorkoutSummary

sealed interface WorkoutUiState {
    data object Loading : WorkoutUiState
    data class Error(val message: String) : WorkoutUiState
    data class Success(val days: List<DailyWorkoutUi>,
                       val summary: WorkoutPlanSummary) : WorkoutUiState
}

data class WorkoutPlanSummary(
    val totalExercises: Int,
    val totalCalories: Int,
    val totalMinutes: Int
)

data class DailyWorkoutUi(
    val day: Day,
    val isCompleted: Boolean,
    val exercises: List<ExerciseModel>,
    val summary: WorkoutSummary,
    val isSelected: Boolean = false,
    val subtitle: String = "Foundations",
    )

