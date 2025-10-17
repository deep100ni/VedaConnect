// In D:/Code/Flutter/VedaConnect/app/src/main/java/com/DeepSoni/vedaconnect/repository/MandalaOneSuktasRepository.kt

package com.DeepSoni.vedaconnect.repository

import android.content.Context
import com.DeepSoni.vedaconnect.data.Sukta
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.io.IOException

object MandalaOneSuktasRepository {

    // This list will hold ALL suktas from the JSON file after initialization.
    private var allSuktas: List<Sukta> = emptyList()

    /**
     * The publicly exposed list of suktas.
     * It now filters the full list to only return suktas for Mandala 1.
     * Your UI code will continue to use this property without any changes.
     */
    val suktas: List<Sukta>
        get() = allSuktas.filter { it.mandalaNumber == 1 }

    /**
     * Initializes the repository by loading data from the JSON asset.
     * This method should be called only once when the app starts.
     */
    fun initialize(context: Context) {
        // Prevent re-initialization
        if (allSuktas.isNotEmpty()) {
            return
        }

        try {
            // 1. Open the JSON file from the assets folder
            val jsonString = context.assets.open("complete_rigveda_all_mandalas.json")
                .bufferedReader()
                .use { it.readText() }

            // 2. Create a JSON parser instance (lenient to avoid crashing on extra fields)
            val jsonParser = Json { ignoreUnknownKeys = true }

            // 3. Decode the JSON string into a list of Sukta objects
            allSuktas = jsonParser.decodeFromString(jsonString)

        } catch (ioException: IOException) {
            // Log the error or handle it as needed
            ioException.printStackTrace()
            // In case of an error, we'll have an empty list
            allSuktas = emptyList()
        }
    }
}