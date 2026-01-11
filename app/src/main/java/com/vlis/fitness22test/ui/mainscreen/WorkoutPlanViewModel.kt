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
    val uiState: StateFlow<WorkoutState>
        field : MutableStateFlow<WorkoutState> = MutableStateFlow(WorkoutState.Loading)

    private fun loadData() {
        viewModelScope.launch {
            useCase()
                .onSuccess { plan ->
                    uiState.value = WorkoutState.Success(plan)
                }
                .onFailure {
                    uiState.value = WorkoutState.Error(it.message ?: "Unknown error")
                }
        }
    }
}

sealed interface WorkoutState {
    data object Loading : WorkoutState
    data class Error(val message: String) : WorkoutState
    data class Success(val workoutPlan: WorkoutPlan) : WorkoutState
}