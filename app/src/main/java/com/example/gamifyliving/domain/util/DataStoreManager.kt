package com.example.gamifyliving.domain.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_datastore")

    companion object {
        val COINS = intPreferencesKey("coins")
    }

    val coins: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[COINS] ?: 0
        }

    suspend fun updateCoins(updatedCoins: Int) {
        context.dataStore.edit { preferences ->
            preferences[COINS] = updatedCoins
        }
    }

}
