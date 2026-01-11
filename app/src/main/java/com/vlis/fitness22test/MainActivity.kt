package com.vlis.fitness22test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vlis.fitness22test.ui.mainscreen.WorkoutPlanScreen
import com.vlis.fitness22test.ui.theme.Fitness22TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fitness22TestTheme {
                WorkoutPlanScreen()
            }
        }
    }
}
