package com.vlis.fitness22test.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlis.fitness22test.R
import com.vlis.fitness22test.domain.ExerciseModel
import com.vlis.fitness22test.domain.MuscleGroup
import com.vlis.fitness22test.domain.WorkoutSummary
import com.vlis.fitness22test.ui.icons.BootstrapChevronDown
import com.vlis.fitness22test.ui.icons.BootstrapLightning
import com.vlis.fitness22test.ui.icons.MaterialIconsDone
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
            .padding(horizontal = Spacing.s)

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
                Column(modDef.fillMaxSize()) {
                    // Tabs
                    Spacers.S()
                    WorkoutSummaryChips()
                    Spacers.S()
                    WorkoutDaysTabs(
                        state.days,
                        viewModel::handleIntent,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacers.M()
                    val selectedDay = state.days.first { it.isSelected }

                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        WorkoutDay(
                            selectedDay,
                            resolver,
                            Modifier.matchParentSize()
                        )

                        Button(
                            {
                                viewModel.handleIntent(WorkoutIntentState.OnStartWorkoutClicked)
                            },
                            shape = AppShapes.medium,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .widthIn(min = 250.dp)
                                .padding(bottom = Spacing.xxl4),
                            colors = buttonColors(
                                containerColor = AppColors.tertiary,
                                contentColor = AppColors.onPrimary
                            )
                        ) {
                            Text(
                                if (selectedDay.isCompleted)
                                    stringResource(R.string.redo_workout)
                                else stringResource(R.string.start_workout),
                                style = AppTypography.headlineSmall,
                                fontStyle = FontStyle.Italic
                            )
                        }
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
    val mod = Modifier.padding(vertical = Spacing.xxs, horizontal = Spacing.xs)
    Row(modifier.horizontalScroll(rememberScrollState())) {
        InfoChip(
            "Muscles",
            false,
            {},
            mod
        )
        Spacers.Xxs()
        InfoChip(
            "45-60 Min",
            false,
            {},
            mod
        )
        Spacers.Xxs()
        InfoChip(
            stringResource(R.string.schedule),
            false,
            {},
            mod
        )
        Spacers.Xxs()
        InfoChip(
            stringResource(R.string.schedule),
            false,
            {},
            mod
        )
        Spacers.Xxs()
        InfoChip(
            stringResource(R.string.schedule),
            false,
            {},
            mod
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
    FilterChip(
        isSelected,
        onClick,
        modifier = modifier,
        shape = AppShapes.medium,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = AppColors.primaryContainer,
            labelColor = AppColors.primary,
        ),
        label = {
            Text(text, style = AppTypography.bodyMedium)
        },
        border = null,
        trailingIcon = {
            Icon(
                imageVector = BootstrapChevronDown,
                contentDescription = text,
                modifier = Spacers.spacerSModifier
            )
        }
    )
}

@Composable
fun WorkoutDaysTabs(
    days: List<DailyWorkoutUi>,
    onClick: (WorkoutIntentState) -> Unit,
    modifier: Modifier = Modifier
) {
    SecondaryScrollableTabRow(
        days.indexOfFirst { it.isSelected },
        modifier,
        indicator = { },
        divider = {},
        containerColor = Color.Unspecified,
        edgePadding = 0.dp,
    ) {
        days.forEach {
            DayTab(it, onClick)
        }
    }
}

@Composable
fun DayTab(
    model: DailyWorkoutUi,
    onClick: (WorkoutIntentState) -> Unit,
    modifier: Modifier = Modifier
) {
    Tab(
        model.isSelected,
        onClick = { onClick(WorkoutIntentState.OnDaySelected(model.day)) },
        modifier = modifier
            .padding(horizontal = Spacing.xxs)
            .background(
                color = if (model.isSelected) AppColors.primaryContainer else AppColors.onPrimary,
                shape = CircleShape
            )
            .heightIn(36.dp)
            .widthIn(60.dp)
            .clip(CircleShape),
    ) {
        if (model.isCompleted) {
            Icon(
                imageVector = MaterialIconsDone,
                contentDescription = null,
                tint = AppColors.secondary
            )
        } else {
            Text(stringResource(R.string.day_tab, model.day.day), color = AppColors.secondary)
        }
    }
}

@Composable
fun WorkoutDay(
    model: DailyWorkoutUi,
    resolver: DrawableResolver,
    modifier: Modifier = Modifier
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Week 1/5 - Foundations",
            color = AppColors.secondary,
            style = AppTypography.labelSmall
        )
        Text(
            if (model.isCompleted)
                "Workout completed".uppercase() else "Upcoming workout".uppercase(),
            style = AppTypography.headlineLarge,
            fontStyle = FontStyle.Italic,
        )
        Text(
            "Push",
            style = AppTypography.labelMedium
        )
        Spacers.M()

        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = AppColors.primaryContainer)
        ) {
            Column(Modifier
                .fillMaxSize()
                .padding(vertical = Spacing.s, horizontal = Spacing.xs)) {
                WorkoutDaySummary(model.summary, Modifier.align(Alignment.CenterHorizontally))
                Spacers.M()
                val itemMod = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Spacing.xs)
                LazyColumn(Modifier.fillMaxSize()) {
                    items(
                        model.exercises.size,
                        key = { model.exercises[it].id }
                    ) {
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
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = vector,
            contentDescription = title,
            tint = AppColors.onPrimary,
            modifier = Modifier.size(Spacing.m)
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
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        DynamicImage(
            model.thumbnail,
            resolver,
            Modifier
                .size(60.dp)
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
                .size(Spacing.xxl4)
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
            val id = resolver.resolve(imageName)
            if (id == null || id == 0) placeholderImgRes else id
        }

        Image(
            painter = painterResource(resId),
            contentDescription = contentDescription,
            modifier
        )
    }
}