package com.DeepSoni.vedaconnect.Repository

import com.DeepSoni.vedaconnect.Data.Sukta

private const val BASE_AUDIO_URL = "https://raw.githubusercontent.com/c0mpli/rigveda3d/main/public/data/audio"


object MandalaOneSuktasRepository {
    val suktas: List<Sukta> = listOf(
        Sukta(
            id = "sukta1",
            name = "Agni Sukta",
            mandalaNumber = 1,
            suktaNumber = 1,
            sanskrit = "अग्निमीळे पुरोहितं यज्ञस्य देवमृत्विजम्। होतारं रत्नधातमम्॥",
            transliteration = "agnimīḷe purohitaṃ yajñasya devamṛtvijam| hotāraṃ ratnadhātamam||",
            translation = "I praise Agni, the chosen Priest, God, minister of sacrifice, the hotar, lavishest of wealth.",
            preview = "The first hymn of the Rigveda, dedicated to Agni, the fire god.",
            audioUrl = "$BASE_AUDIO_URL/3/62.mp3"
        ),
        Sukta(
            id = "sukta2",
            name = "Vayu Sukta",
            mandalaNumber = 1,
            suktaNumber = 2,
            sanskrit = "वायवा याहि दर्शतेमे सोमा अरंकृताः। तेषां पाहि श्रुधी हवम्॥",
            transliteration = "vāyavā yāhi darśateme somā araṃkṛtāḥ| teṣāṃ pāhi śrudhī havam||",
            translation = "BEAUTIFUL Vāyu, come, for thee these Soma drops have been prepared. Drink of them, hearken to our call.",
            preview = "A hymn to Vayu, the god of wind.",
            audioUrl = "$BASE_AUDIO_URL/3/62.mp3"
        )
    )

    fun searchSuktas(query: String): List<Sukta> {
        return suktas.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.sanskrit.contains(query, ignoreCase = true) ||
                    it.transliteration.contains(query, ignoreCase = true) ||
                    it.translation.contains(query, ignoreCase = true)
        }
    }
}