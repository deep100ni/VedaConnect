package com.DeepSoni.vedaconnect.feature.welcome

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val USER_PREFERENCE_NAME = "user_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)
private val NAME_KEY = stringPreferencesKey("name")

class NameRepository(private val context: Context) {
    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences -> preferences[NAME_KEY] = name }
    }

    val userName: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[NAME_KEY] ?: ""
    }
}