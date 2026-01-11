package com.vlis.fitness22test.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlis.fitness22test.domain.GetWorkoutPlanUseCase
import com.vlis.fitness22test.domain.WorkoutPlan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutPlanViewModel @Inject constructor(
    val useCase: GetWorkoutPlanUseCase
) : ViewModel() {
    val uiState: StateFlow<WorkoutUiState>
        field : MutableStateFlow<WorkoutUiState> = MutableStateFlow(WorkoutUiState.Loading)

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            useCase()
                .onSuccess { plan ->
                    uiState.value = plan.toUiState()
                }
                .onFailure {
                    uiState.value = WorkoutUiState.Error(it.message ?: "Unknown error")
                }
        }
    }

    private fun WorkoutPlan.toUiState(): WorkoutUiState.Success {
        val daysUi = days.mapIndexed { i,day ->
            DailyWorkoutUi(
                day = day.day,
                exercises = day.exercises,
                isSelected = i == 0,
                isCompleted = i == 0,
                summary = day.summary
            )
        }

        val summary = WorkoutPlanSummary(
            totalExercises = days.sumOf { it.exercises.size },
            totalCalories = days.sumOf { it.exercises.size * 100 },
            totalMinutes = days.sumOf { it.exercises.size * 10 }
        )

        return WorkoutUiState.Success(
            days = daysUi,
            summary = summary
        )
    }

    fun handleIntent(intentState: WorkoutIntentState){
        when(intentState){
            is WorkoutIntentState.OnDaySelected -> {
                val currentState = uiState.value
                if (currentState is WorkoutUiState.Success) {
                    val updatedDays = currentState.days.map { dayUi ->
                        dayUi.copy(isSelected = dayUi.day == intentState.day)
                    }

                    uiState.value = currentState.copy(days = updatedDays)
                }
            }
            is WorkoutIntentState.OnStartWorkoutClicked -> {

            }
        }
    }
}