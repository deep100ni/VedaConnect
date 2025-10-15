package com.DeepSoni.vedaconnect.data

// Data Classes
data class Mantra(
    val id: String,
    val name: String,
    val mandalaNumber: Int,
    val suktaNumber: Int,
    val mantraNumber: Int,
    val sanskrit: String,
    val transliteration: String,
    val translation: String,
    val preview: String,
    val audioUrl : String
)

data class Mandala(
    val id: String,
    val name: String,
    val mandalaNumber: Int,
    val sanskrit: String,
    val transliteration: String,
    val translation: String,
    val preview: String
)

data class Sukta(
    val id: String,
    val name: String,
    val mandalaNumber: Int,
    val suktaNumber: Int,
    val sanskrit: String,
    val transliteration: String,
    val translation: String,
    val preview: String,
    val audioUrl : String? = null
)

data class QuizResult(
    val correctAnswers: Int,
    val totalQuestions: Int,
    val pointsEarned: Int,
    val totalScore: Int
)

data class LeaderboardEntry(
    val name: String,
    val rank: Int,
    val points: Int,
    val isCurrentUser: Boolean = false,
    val medal: Medal? = null
)

enum class Medal {
    GOLD, SILVER, BRONZE
}