package com.vlis.fitness22test.ui.utils

import android.content.Context
import android.util.Log
import androidx.annotation.DrawableRes

class DrawableResolver(
    private val context: Context
) {

    private val cache = mutableMapOf<String, Int>()

    @DrawableRes
    fun resolve(name: String): Int? {
        val adjName = name.substringBefore('.')

        Log.d("DrawableResolver", "resolving $name, $adjName")
        return cache.getOrPut(name.substringBefore('.')) {
            context.resources.getIdentifier(
                name,
                "drawable",
                context.packageName
            )
        }.takeIf { it != 0 }
    }
}