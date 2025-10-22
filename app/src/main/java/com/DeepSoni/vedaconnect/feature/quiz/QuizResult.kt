package com.DeepSoni.vedaconnect.feature.quiz

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "quiz_scores")

data class QuizResult(
    val points: Int,
    val timestamp: Long = System.currentTimeMillis()
)

class ScoreManager(private val context: Context) {

    private val HIGH_SCORES_KEY = stringPreferencesKey("high_scores")
    private val MAX_SCORES = 5

    private val gson = Gson()

    suspend fun saveScore(newPoints: Int) {
        context.dataStore.edit { preferences ->
            val resultsJson = preferences[HIGH_SCORES_KEY]
            val currentResults = if (resultsJson != null) {
                gson.fromJson<List<QuizResult>>(resultsJson, object : TypeToken<List<QuizResult>>() {}.type)
            } else {
                emptyList()
            }
            val newResult = QuizResult(points = newPoints)
            val updatedResults = (currentResults + newResult)
                .sortedWith(compareByDescending<QuizResult> { it.points }.thenByDescending { it.timestamp })
                .take(MAX_SCORES)
            preferences[HIGH_SCORES_KEY] = gson.toJson(updatedResults)
        }
    }
    val highScoresFlow: Flow<List<QuizResult>> = context.dataStore.data
        .map { preferences ->
            val resultsJson = preferences[HIGH_SCORES_KEY]
            if (resultsJson != null) {
                gson.fromJson<List<QuizResult>>(resultsJson, object : TypeToken<List<QuizResult>>() {}.type)
            } else {
                emptyList()
            }
        }
}