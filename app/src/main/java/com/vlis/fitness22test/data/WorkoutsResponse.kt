package com.vlis.fitness22test.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutsResponse(
    @SerialName("workouts")
    val workouts: List<Workout?>?
) {
    @Serializable
    data class Workout(
        @SerialName("day")
        val day: Int?,
        @SerialName("workout")
        val workout: List<Workout?>?
    ) {
        @Serializable
        data class Workout(
            @SerialName("amount_of_sets")
            val amountOfSets: Int?,
            @SerialName("exercise_id")
            val exerciseId: Int?,
            @SerialName("exercise_name")
            val exerciseName: String?,
            @SerialName("exercise_thumbnail")
            val exerciseThumbnail: String?,
            @SerialName("muscle_group")
            val muscleGroup: String?,
            @SerialName("muscle_group_image")
            val muscleGroupImage: String?,
            @SerialName("rep_range")
            val repRange: String?,
            @SerialName("weight_amount")
            val weightAmount: String?
        )
    }
}