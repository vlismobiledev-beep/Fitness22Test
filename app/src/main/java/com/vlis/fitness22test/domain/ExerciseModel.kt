package com.vlis.fitness22test.domain

data class WorkoutPlan(
    val list: List<DailyWorkout>
)

data class DailyWorkout(
    val day: WorkoutDay,
    val exercises: List<ExerciseModel>
)

@JvmInline
value class WorkoutDay(val day: Int)

data class ExerciseModel(
    val amountOfSets: Int,
    val id: Int,
    val name: String,
    val thumbnail: String?,
    val muscleGroup: MuscleGroup,
    val repRange: String,
    val weightAmount: String
)