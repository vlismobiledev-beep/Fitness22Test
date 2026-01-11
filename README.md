# Project Context: Fitness22Test

## Overview
Fitness22Test is an Android application designed to display and manage workout plans. It follows modern Android development practices, including:
- **UI Framework**: Jetpack Compose
- **Architecture**: MVI (Model-View-Intent) pattern with ViewModels
- **Dependency Injection**: Hilt
- **Asynchronous Programming**: Kotlin Coroutines and StateFlow
- **Data Handling**: Kotlinx Serialization

## Key Components

### UI Layer (`com.vlis.fitness22test.ui`)
- **`WorkoutPlanScreen`**: The main entry point for the workout plan feature. It uses a `Scaffold` with a top bar and displays a list of workout days, a summary of the selected day, and a list of exercises.
- **`WorkoutPlanViewModel`**: Manages the UI state (`WorkoutUiState`) and handles user intents (`WorkoutIntentState`).
- **Composables**: 
    - `WorkoutDaysTabs`: A horizontal scrollable row of tabs representing different days.
    - `WorkoutDay`: Displays the details of a specific day's workout.
    - `ExcerciseRow`: Represents a single exercise item with an image, name, sets/reps, and muscle group icon.
    - `InfoChip`: Reusable filter chips for workout metadata (e.g., muscles, duration).

### Domain Layer (`com.vlis.fitness22test.domain`)
- **`WorkoutPlan`**: Contains a list of daily workout models.
- **`DailyWorkoutModel`**: Represents a day's workout, including the day number, list of exercises, and a summary (exercises, calories, minutes).
- **`ExerciseModel`**: Details of an individual exercise (name, thumbnail, muscle group, sets, reps, weight).
- **`MuscleGroup`**: Enum representing different muscle groups (e.g., LEGS, GLUTES, LATS).

### Infrastructure & Resources
- **Dependency Injection**: Hilt is used for providing the `WorkoutPlanViewModel` and other dependencies.
- **Version Catalog**: Project dependencies are managed in `gradle/libs.versions.toml`.
- **Localization**: Strings are extracted to `res/values/strings.xml` and `res/values/plurals.xml` for better maintainability and potential multi-language support.
- **Themes**: Custom typography (`AppTypography`), colors (`AppColors`), and spacing (`Spacing`) are defined in `com.vlis.fitness22test.ui.theme`.

## Recent Changes
- Added Kotlin Serialization, Hilt, Coroutines, and ViewModel for Compose to the version catalog and project dependencies.
- Refactored `WorkoutScreenComposables.kt` to extract hardcoded strings into `strings.xml`.
- Implemented MVI pattern for the workout plan screen.
- Integrated `DrawableResolver` for dynamic image loading based on resource names.
