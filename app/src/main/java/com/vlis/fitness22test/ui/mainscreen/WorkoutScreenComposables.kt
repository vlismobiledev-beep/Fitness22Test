package com.vlis.fitness22test.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlis.fitness22test.R
import com.vlis.fitness22test.domain.ExerciseModel
import com.vlis.fitness22test.domain.MuscleGroup
import com.vlis.fitness22test.domain.WorkoutSummary
import com.vlis.fitness22test.ui.icons.BootstrapLightning
import com.vlis.fitness22test.ui.theme.AppColors
import com.vlis.fitness22test.ui.theme.AppShapes
import com.vlis.fitness22test.ui.theme.AppTypography
import com.vlis.fitness22test.ui.theme.Spacers
import com.vlis.fitness22test.ui.theme.Spacing
import com.vlis.fitness22test.ui.utils.DrawableResolver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutPlanScreen(viewModel: WorkoutPlanViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val resolver = remember {
        DrawableResolver(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.my_workout),
                        style = AppTypography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
    ) {

        val modDef = Modifier
            .padding(it)
            .padding(horizontal = Spacing.xs)

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when (val state = uiState) {
            is WorkoutUiState.Loading -> Loading(
                modDef
                    .fillMaxSize()
            )

            is WorkoutUiState.Error -> Error(
                state.message, modDef
                    .fillMaxSize()
            )

            is WorkoutUiState.Success -> {
                // Tabs
                WorkoutSummaryChips(
                    modDef
                )

                val selectedDay = state.days.first { it.isSelected }

                Box(
                    modDef.fillMaxSize()
                ) {
                    WorkoutDay(
                        selectedDay, resolver,
                        Modifier.fillMaxSize()
                    )

                    Button(
                        {
                            viewModel.handleIntent(WorkoutIntentState.OnStartWorkoutClicked)
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .widthIn(min = 250.dp)
                            .padding(bottom = Spacing.xxl4)
                    ) {
                        Text(
                            if (selectedDay.isCompleted)
                                stringResource(R.string.start_workout)
                            else stringResource(R.string.redo_workout)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(message: String, modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Text(
            message,
            style = AppTypography.headlineMedium,
            textAlign = TextAlign.Center,
            color = AppColors.error
        )
    }
}


@Composable
fun WorkoutSummaryChips(
    modifier: Modifier = Modifier
) {

    Row {
        InfoChip(
            stringResource(R.string.schedule),
            false,
            {}
        )

    }
}

@Composable
fun InfoChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(isSelected, onClick, modifier = modifier, label = {
        Text(text, style = AppTypography.labelSmall)
    })
}

@Composable
fun WorkoutDaysTabs(modifier: Modifier = Modifier) {

}

@Composable
fun WorkoutDay(
    model: DailyWorkoutUi,
    resolver: DrawableResolver,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text("Week 1/5 - Foundations")
        Text(
            if (model.isCompleted)
                "Workout completed".uppercase() else "Upcoming workout".uppercase()
        )

        Card(Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {
                WorkoutDaySummary(model.summary, Modifier.align(Alignment.CenterHorizontally))
                Spacers.M()
                val itemMod = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Spacing.xxs)
                LazyColumn(Modifier.fillMaxSize()) {
                    items(model.exercises.size) {
                        ExcerciseRow(model.exercises[it], resolver, itemMod)
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutDaySummary(summary: WorkoutSummary, modifier: Modifier = Modifier) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SummaryRow(
            title = pluralStringResource(
                R.plurals.exercise_count,
                summary.exercises,
                summary.exercises
            ),
            vector = BootstrapLightning
        )
        Spacers.Xxs()
        SummaryRow(
            title = stringResource(R.string.minutes_short_ct, summary.minutes),
            vector = BootstrapLightning
        )
        Spacers.Xxs()
        SummaryRow(
            title = stringResource(
                R.string.calories_short_ct,
                summary.cal
            ),
            vector = BootstrapLightning
        )
    }
}

@Composable
fun SummaryRow(
    title: String,
    vector: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Icon(
            imageVector = vector,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
        Spacers.Xxs()
        Text(title, style = AppTypography.bodyLarge)
    }
}

@Composable
fun ExcerciseRow(
    model: ExerciseModel,
    resolver: DrawableResolver,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        DynamicImage(
            model.thumbnail,
            resolver,
            Modifier
                .size(50.dp)
                .background(Color.Unspecified, AppShapes.small)
        )
        Spacers.Xs()
        Column(modifier = Modifier.weight(1f)) {
            Text(model.name, style = AppTypography.bodyLarge, maxLines = 2)
            Spacers.Xxs()
            val sets = pluralStringResource(
                R.plurals.set_count,
                model.amountOfSets,
                model.amountOfSets
            )
            val reps = stringResource(
                R.string.reps_count,
                model.repRange
            )

            val weight = if (model.weightAmount.isNotBlank()) {
                stringResource(
                    R.string.lb_ct, model.weightAmount
                )
            } else ""

            Text(
                "${sets}${reps}${weight}",
                style = AppTypography.bodySmall,
                maxLines = 1
            )
        }
        Spacers.Xs()
        Image(
            painterResource(model.muscleGroup.getImgRes()),
            contentDescription = model.name,
            modifier = Modifier
                .size(Spacing.xxl3)
                .background(Color.Unspecified, CircleShape)
        )
    }
}

fun MuscleGroup.getImgRes(): Int {
    return when (this) {
        MuscleGroup.LEGS -> R.drawable.muscle_groups_1
        MuscleGroup.GLUTES -> R.drawable.muscle_groups_3
        MuscleGroup.LATS -> R.drawable.muscle_groups_2
    }
}

@Composable
fun DynamicImage(
    imageName: String?,
    resolver: DrawableResolver,
    modifier: Modifier = Modifier,
    placeholderImgRes: Int = R.drawable.workout,
    contentDescription: String? = null,
) {
    if (imageName.isNullOrBlank()) {
        Image(
            painter = painterResource(placeholderImgRes),
            contentDescription = contentDescription,
            modifier
        )
    } else {
        val resId = remember(imageName) {
            resolver.resolve(imageName)
        }

        if (resId != null) {
            Image(
                painter = painterResource(resId),
                contentDescription = contentDescription,
                modifier
            )
        }
    }
}