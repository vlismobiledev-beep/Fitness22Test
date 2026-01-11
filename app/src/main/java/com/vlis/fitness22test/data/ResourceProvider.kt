package com.vlis.fitness22test.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {

    fun provideAsset(name: String): String {
        return context.assets.open(name).bufferedReader().use { it.readText() }
    }
}