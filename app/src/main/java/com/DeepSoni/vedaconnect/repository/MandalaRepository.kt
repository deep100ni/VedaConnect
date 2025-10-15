package com.DeepSoni.vedaconnect.repository

import com.DeepSoni.vedaconnect.data.Mandala

object MandalaRepository {

    val mandalas = listOf(
        Mandala(
            id = "21",
            name = "Rig Veda Mandala 1",
            mandalaNumber = 1,
            sanskrit = "प्रथमं मण्डलम्",
            transliteration = "Prathamaṃ Maṇḍalam",
            translation = "The First Book/Mandala. Contains hymns primarily to Agni and Indra.",
            preview = "Explores hymns to Agni, Indra, and other deities."
        ),
        Mandala(
            id = "22",
            name = "Rig Veda Mandala 2",
            mandalaNumber = 2,
            sanskrit = "द्वितीयं मण्डलम्",
            transliteration = "Dvitīyaṃ Maṇḍalam",
            translation = "The Second Book/Mandala. Attributed to the Rishi Gritsamada.",
            preview = "Predominantly hymns to Indra and Agni by Gritsamada."
        ),
        Mandala(
            id = "23",
            name = "Rig Veda Mandala 3",
            sanskrit = "तृतीयं मण्डलम्",
            mandalaNumber = 3,
            transliteration = "Tṛtīyaṃ Maṇḍalam",
            translation = "The Third Book/Mandala. Attributed to the Rishi Vishvamitra.",
            preview = "Includes the famous Gayatri Mantra (though a full Mandala summary)."
        ),
        Mandala(
            id = "24",
            name = "Rig Veda Mandala 4",
            mandalaNumber = 4,
            sanskrit = "चतुर्थं मण्डलम्",
            transliteration = "Caturthaṃ Maṇḍalam",
            translation = "The Fourth Book/Mandala. Attributed to the Rishi Vamadeva.",
            preview = "Hymns primarily to Indra, Agni, and the Ashvins."
        ),
        Mandala(
            id = "25",
            name = "Rig Veda Mandala 5",
            mandalaNumber = 5,
            sanskrit = "पञ्चमं मण्डलम्",
            transliteration = "Pañcamaṃ Maṇḍalam",
            translation = "The Fifth Book/Mandala. Attributed to the Atri family.",
            preview = "Dedicated to various deities including Agni, Indra, and the Maruts."
        ),
        Mandala(
            id = "26",
            name = "Rig Veda Mandala 6",
            mandalaNumber = 6,
            sanskrit = "षष्ठं मण्डलम्",
            transliteration = "Ṣaṣṭhaṃ Maṇḍalam",
            translation = "The Sixth Book/Mandala. Attributed to the Bharadvaja family.",
            preview = "Contains hymns focusing on Indra, Agni, and Pushan."
        ),
        Mandala(
            id = "27",
            name = "Rig Veda Mandala 7",
            mandalaNumber = 7,
            sanskrit = "सप्तमं मण्डलम्",
            transliteration = "Saptamaṃ Maṇḍalam",
            translation = "The Seventh Book/Mandala. Attributed to the Rishi Vasishtha.",
            preview = "Features hymns to Agni, Indra, Varuna, and the Maruts."
        ),
        Mandala(
            id = "28",
            name = "Rig Veda Mandala 8",
            mandalaNumber = 8,
            sanskrit = "अष्टमं मण्डलम्",
            transliteration = "Aṣṭamaṃ Maṇḍalam",
            translation = "The Eighth Book/Mandala. Diverse authorship, often Kanva family.",
            preview = "A collection of hymns, many by the Kanva priests."
        ),
        Mandala(
            id = "29",
            name = "Rig Veda Mandala 9",
            mandalaNumber = 9,
            sanskrit = "नवमं मण्डलम्",
            transliteration = "Navamaṃ Maṇḍalam",
            translation = "The Ninth Book/Mandala. Exclusively dedicated to Soma Pavamana.",
            preview = "All hymns in this mandala are dedicated to Soma, the purifying deity."
        ),
        Mandala(
            id = "30",
            name = "Rig Veda Mandala 10",
            mandalaNumber = 10,
            sanskrit = "दशमं मण्डलम्",
            transliteration = "Daśamaṃ Maṇḍalam",
            translation = "The Tenth Book/Mandala. Contains hymns of diverse content, including philosophical ones.",
            preview = "Includes hymns like Purusha Sukta and Nasadiya Sukta."
        )
    )

    fun searchMandalas(query: String): List<Mandala> {
        if (query.isBlank()) return mandalas
        return mandalas.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.transliteration.contains(query, ignoreCase = true) ||
                    it.sanskrit.contains(query, ignoreCase = true) ||
                    it.preview.contains(query, ignoreCase = true) ||
                    it.mandalaNumber.toString() == query
        }
    }
}