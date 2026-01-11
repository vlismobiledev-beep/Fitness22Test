package com.vlis.fitness22test.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

class ResourceProvider (@ApplicationContext private val context: Context) {

    fun provideAsset(name: String): String {
        return context.assets.open(name).bufferedReader().use { it.readText() }
    }
}