package com.DeepSoni.vedaconnect.repository

import android.content.Context
import com.DeepSoni.vedaconnect.data.Sukta
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

// Kelas data perantara untuk penguraian JSON
@kotlinx.serialization.Serializable
private data class Rik(
    val rik_number: Int,
    val samhita: Samhita,
    val padapatha: Padapatha,
    val translation: String
)

@kotlinx.serialization.Serializable
private data class Samhita(
    val devanagari: Devanagari
)

@kotlinx.serialization.Serializable
private data class Padapatha(
    val transliteration: Transliteration
)

@kotlinx.serialization.Serializable
private data class Devanagari(
    val text: String
)

@kotlinx.serialization.Serializable
private data class Transliteration(
    val text: String
)

// Objek repositori untuk menampung dan mengelola data Sukta
object MandalaOneSuktasRepository {

    private var isInitialized = false
    var suktas: List<Sukta> = emptyList()
        private set // Mencegah modifikasi dari luar

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    /**
     * Menginisialisasi repositori dengan memuat dan mengurai data Sukta dari aset JSON.
     * Ini harus dipanggil hanya sekali, sebaiknya dari kelas Aplikasi Anda atau ViewModel.
     *
     * @param context Konteks aplikasi yang diperlukan untuk mengakses aset.
     */
    fun initialize(context: Context) {
        if (isInitialized) return

        // Baca file JSON dari folder aset
        // PERBAIKAN KUNCI: Jalur aset yang salah diperbaiki. Seharusnya hanya nama file.
        val jsonString = context.assets.open("complete_rigveda_all_mandalas.json").bufferedReader().use {
            it.readText()
        }

        // Urai JSON mentah ke dalam kelas data perantara kami
        val parsedData = jsonParser.decodeFromString<Map<String, Map<String, List<Rik>>>>(jsonString)

        // Petakan data yang diurai ke dalam List<Sukta> akhir
        suktas = parsedData.flatMap { (mandalaKey, suktasMap) ->
            val mandalaNumber = mandalaKey.filter { it.isDigit() }.toIntOrNull() ?: 0

            suktasMap.map { (suktaKey, riks) ->
                val suktaNumber = suktaKey.filter { it.isDigit() }.toIntOrNull() ?: 0

                val fullSanskrit = riks.joinToString(separator = "\n") { it.samhita.devanagari.text }
                val fullTransliteration = riks.joinToString(separator = "\n") { it.padapatha.transliteration.text }
                val fullTranslation = riks.joinToString(separator = "\n") { it.translation }

                Sukta(
                    id = "M${mandalaNumber}S$suktaNumber",
                    name = "Sukta $suktaNumber",
                    mandalaNumber = mandalaNumber,
                    suktaNumber = suktaNumber,
                    sanskrit = fullSanskrit,
                    transliteration = fullTransliteration,
                    translation = fullTranslation,
                    preview = riks.firstOrNull()?.translation ?: "Tidak ada pratinjau yang tersedia.",
                    // PERBAIKAN: Setel audioUrl ke null secara default karena tidak ada di JSON Anda
                    audioUrl = null
                )
            }
        }
        isInitialized = true
    }
}