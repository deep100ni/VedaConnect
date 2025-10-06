package com.DeepSoni.vedaconnect.Data

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
    val audioResId: Int? = null
)