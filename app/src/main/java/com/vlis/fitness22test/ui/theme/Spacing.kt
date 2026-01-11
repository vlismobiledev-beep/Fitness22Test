package com.vlis.fitness22test.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Spacers {
    private val spacerXxxsModifier = Modifier.size(Spacing.xxxs)
    private val spacerXxsModifier = Modifier.size(Spacing.xxs)
    val spacerXsModifier = Modifier.size(Spacing.xs)
    val spacerSModifier = Modifier.size(Spacing.s)
    val spacerMModifier = Modifier.size(Spacing.m)
    val spacerLModifier = Modifier.size(Spacing.l)
    val spacerXlModifier = Modifier.size(Spacing.xl)
    val spacerXxlModifier = Modifier.size(Spacing.xxl)
    val spacerXxl2Modifier = Modifier.size(Spacing.xxl2)
    val spacerXxl3Modifier = Modifier.size(Spacing.xxl3)

    val spacerXXL4Modifier = Modifier.size(Spacing.xxl4)
    val spacerXXL8Modifier = Modifier.size(Spacing.xxl8)

    @Composable
    fun Xxs() {
        Spacer(modifier = spacerXxsModifier)
    }

    @Composable
    fun Xxxs() {
        Spacer(modifier = spacerXxxsModifier)
    }

    @Composable
    fun Xs() {
        Spacer(modifier = spacerXsModifier)
    }

    @Composable
    fun S() {
        Spacer(modifier = spacerSModifier)
    }

    @Composable
    fun M() {
        Spacer(modifier = spacerMModifier)
    }

    @Composable
    fun L() {
        Spacer(modifier = spacerLModifier)
    }

    @Composable
    fun Xl() {
        Spacer(modifier = spacerXlModifier)
    }

    @Composable
    fun XXl() {
        Spacer(modifier = spacerXxlModifier)
    }

    @Composable
    fun XXL4() {
        Spacer(modifier = spacerXXL4Modifier)
    }

    @Composable
    fun XXl2() {
        Spacer(modifier = spacerXxl2Modifier)
    }

    @Composable
    fun XXL8() {
        Spacer(modifier = spacerXXL8Modifier)
    }
}

object Spacing {
    val zero = 0.dp
    val strokeS = 0.5.dp
    val strokeXs = 0.2.dp
    val stroke = 1.dp

    val xxxs = 2.dp
    val xxs = 4.dp
    val xs = 8.dp
    val s = 12.dp
    val m = 16.dp
    val l = 20.dp
    val xl = 24.dp
    val xxl = 28.dp
    val xxl2 = 32.dp
    val xxl3 = 36.dp
    val xxl4 = 40.dp

    val xxl8 = 80.dp
}