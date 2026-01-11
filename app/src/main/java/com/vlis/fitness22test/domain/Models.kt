package com.vlis.fitness22test.domain

data class WorkoutPlan(
    val days: List<DailyWorkoutModel>
)

data class DailyWorkoutModel(
    val day: Day,
    val exercises: List<ExerciseModel>,
) {
    val summary: WorkoutSummary = WorkoutSummary(
        exercises.size,
        exercises.size * 100,
        exercises.size * 10
    )
}

data class WorkoutSummary(
    val exercises: Int,
    val cal: Int,
    val minutes: Int
)

@JvmInline
value class Day(val day: Int)

data class ExerciseModel(
    val amountOfSets: Int,
    val id: Int,
    val name: String,
    val thumbnail: String?,
    val muscleGroup: MuscleGroup,
    val repRange: String,
    val weightAmount: String
)