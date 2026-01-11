package com.vlis.fitness22test.ui.utils

import android.content.Context
import androidx.annotation.DrawableRes

class DrawableResolver(
    private val context: Context
) {

    private val cache = mutableMapOf<String, Int>()

    @DrawableRes
    fun resolve(name: String): Int? {
        return cache.getOrPut(name) {
            context.resources.getIdentifier(
                name,
                "drawable",
                context.packageName
            )
        }.takeIf { it != 0 }
    }
}