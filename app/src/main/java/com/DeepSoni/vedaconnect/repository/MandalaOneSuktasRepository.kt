package com.DeepSoni.vedaconnect.repository

// Add this line
import com.DeepSoni.vedaconnect.data.Sukta
import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


// Repository object to hold and manage the Sukta data
object MandalaOneSuktasRepository {

    private var isInitialized = false
    var suktas: List<Sukta> = emptyList()
        private set // Prevents modification from outside

    private val jsonParser = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    /**
     * Initializes the repository by loading and parsing Sukta data from the assets JSON.
     * This should be called only once, preferably from your Application class or a ViewModel.
     *
     * @param context The application context needed to access assets.
     */
    fun initialize(context: Context) {
        if (isInitialized) return

        // Read the JSON file from the assets folder
        val jsonString = context.assets.open("suktas/complete_rigveda_all_mandalas.json").bufferedReader().use {
            it.readText()
        }

        // Parse the raw JSON into our intermediate data classes
        val parsedData = jsonParser.decodeFromString<Map<String, Map<String, List<Rik>>>>(jsonString)

        // Map the parsed data into the final List<Sukta>
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
                    preview = riks.firstOrNull()?.translation ?: "No preview available."
                )
            }
        }
        isInitialized = true
    }
}


// region Intermediate Data Classes for JSON Parsing
// These classes perfectly match the structure of your JSON file.

@Serializable
private data class Rik(
    val rik_number: Int,
    val samhita: Samhita,
    val padapatha: Padapatha,
    val translation: String
)

@Serializable
private data class Samhita(
    val devanagari: Devanagari
)

@Serializable
private data class Padapatha(
    val transliteration: Transliteration
)

@Serializable
private data class Devanagari(
    val text: String
)

@Serializable
private data class Transliteration(
    val text: String
)
// endregion