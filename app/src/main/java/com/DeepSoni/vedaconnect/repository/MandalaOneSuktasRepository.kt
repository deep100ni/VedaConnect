package com.DeepSoni.vedaconnect.repository

import android.content.Context
import android.util.Log
import com.DeepSoni.vedaconnect.data.Sukta
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


object MandalaOneSuktasRepository {

    // IMPORTANT: This URL points to the raw audio files, not the GitHub webpage.
    private const val AUDIO_BASE_URL = "https://raw.githubusercontent.com/c0mpli/rigveda3d/main/public/data/audio/1"

    private var allSuktas: List<Sukta> = emptyList()

    val suktas: List<Sukta>
        get() = allSuktas.filter { it.mandalaNumber == 1 }

    fun getSuktaById(id: String): Sukta? {
        // Ensure the repository is initialized before searching
        if (allSuktas.isEmpty()) {
            Log.w("Repository", "Attempted to get Sukta by ID before initialization.")
            return null
        }
        return allSuktas.find { it.id == id }
    }

    fun initialize(context: Context) {
        if (allSuktas.isNotEmpty()) {
            return
        }

        try {
            val jsonString = context.assets.open("complete_rigveda_all_mandalas.json")
                .bufferedReader()
                .use { it.readText() }

            val jsonParser = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }

            val rigvedaData = jsonParser.decodeFromString<Map<String, Map<String, List<Rik>>>>(jsonString)

            val parsedSukas = rigvedaData.flatMap { (mandalaKey, suktasMap) ->
                val mandalaNumber = Regex("\\d+").find(mandalaKey)?.value?.toIntOrNull()
                if (mandalaNumber == null) {
                    Log.w("Repository", "Could not parse number from mandala key: '$mandalaKey'. Skipping.")
                    return@flatMap emptyList()
                }

                suktasMap.mapNotNull { (suktaKey, riks) ->
                    val suktaNumber = Regex("\\d+").find(suktaKey)?.value?.toIntOrNull()
                    if (suktaNumber == null) {
                        Log.w("Repository", "Could not parse number from sukta key: '$suktaKey'. Skipping.")
                        return@mapNotNull null
                    }

                    if (riks.isEmpty()) {
                        return@mapNotNull null
                    }

                    val sanskritText = riks.joinToString(separator = "\n") { it.samhita.devanagari.text }
                    val transliterationText = riks.joinToString(separator = "\n") { it.padapatha.transliteration.text }
                    val translationText = riks.joinToString(separator = "\n") { it.translation }

                    val previewText = riks.first().translation

                    Sukta(
                        id = "mandala_${mandalaNumber}_sukta_$suktaNumber",
                        name = "Mandala $mandalaNumber, Sukta $suktaNumber",
                        mandalaNumber = mandalaNumber,
                        suktaNumber = suktaNumber,
                        sanskrit = sanskritText,
                        transliteration = transliterationText,
                        translation = translationText,
                        preview = previewText,
                        // If the sukta is in Mandala 1, construct its audio URL with the .mp3 extension
                        audioUrl = if (mandalaNumber == 1) "$AUDIO_BASE_URL/$suktaNumber.mp3" else null
                    )
                }
            }
            allSuktas = parsedSukas

        } catch (e: Exception) {
            Log.e("Repository", "Fatal error initializing MandalaOneSuktasRepository", e)
            allSuktas = emptyList()
        }
    }
}


@Serializable
private data class Rik(
    val samhita: Samhita,
    val padapatha: Padapatha,
    val translation: String
)

@Serializable
private data class Samhita(
    val devanagari: Devanagari
)

@Serializable
private data class Devanagari(
    val text: String = ""
)

@Serializable
private data class Padapatha(
    val transliteration: Transliteration
)

@Serializable
private data class Transliteration(
    val text: String = ""
)