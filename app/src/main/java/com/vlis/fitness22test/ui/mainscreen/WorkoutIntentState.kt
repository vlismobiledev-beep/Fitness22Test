package com.vlis.fitness22test.ui.mainscreen

import com.vlis.fitness22test.domain.Day

interface WorkoutIntentState {
    data class OnDaySelected(val day: Day) : WorkoutIntentState
    data object OnStartWorkoutClicked : WorkoutIntentState
}