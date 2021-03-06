package com.example.gamifyliving.data.data_source.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_datastore")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        val COINS = intPreferencesKey("coins")
    }

    private val userDataStore = context.dataStore

    val coins: Flow<Int> = userDataStore.data
        .map { preferences ->
            preferences[COINS] ?: 0
        }

    suspend fun update(coins: Int) {
        userDataStore.edit { preferences ->
            preferences[COINS] = coins
        }
    }

}
