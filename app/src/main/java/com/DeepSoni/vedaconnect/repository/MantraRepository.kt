package com.DeepSoni.vedaconnect.repository

import com.DeepSoni.vedaconnect.data.Mantra

object MantraRepository {

    // Base URL for audio files, assuming a structured path like /<mandala_number>/<sukta_number>.mp3
    private const val BASE_AUDIO_URL = "https://raw.githubusercontent.com/c0mpli/rigveda3d/main/public/data/audio"

    val mantras = listOf(
        // --- Popular Mantras with Audio ---

        Mantra(
            id = "1",
            name = "Gayatri Mantra",
            mandalaNumber = 3,
            suktaNumber = 62,
            mantraNumber = 10,
            sanskrit = "ॐ भूर् भुवः स्वः\nतत्सवितुर्वरेण्यम्\nभर्गो देवस्य धीमहि\nधियो यो नः प्रचोदयात्",
            transliteration = "Oṃ Bhūr Bhuvaḥ Svaḥ\nTat Savitur Vareṇyam\nBhargo Devasya Dhīmahi\nDhiyo Yo Naḥ Prachodayāt",
            translation = "We meditate on the glory of the Creator who has created the universe, who is worthy of worship, who is the embodiment of knowledge and light, who removes all sins and ignorance. May he enlighten our intellect.",
            preview = "The fundamental Vedic mantra for enlightenment.",
            audioUrl = "$BASE_AUDIO_URL/3/62.mp3"
        ),
        Mantra(
            id = "2",
            name = "Purusha Sukta (Verse 1)",
            mandalaNumber = 10,
            suktaNumber = 90,
            mantraNumber = 1,
            sanskrit = "सहस्रशीर्षा पुरुषः\nसहस्राक्षः सहस्रपात्\nस भूमिं विश्वतो वृत्वा\nअत्यतिष्ठद्दशाङ्गुलम्",
            transliteration = "Sahasraśīrṣā puruṣaḥ\nSahasrākṣaḥ sahasrapāt\nSa bhūmim viśvato vṛtvā\nAtyatiṣṭhad daśāṅgulam",
            translation = "The Cosmic Being has a thousand heads, a thousand eyes, and a thousand feet. Having pervaded the universe on all sides, he extends beyond it by ten fingers' breadth.",
            preview = "Describes the cosmic man from whom all existence sprang.",
            audioUrl = "$BASE_AUDIO_URL/10/90.mp3"
        ),
        Mantra(
            id = "3",
            name = "Agni Sukta (Rig Veda 1.1.1)",
            mandalaNumber = 1,
            suktaNumber = 1,
            mantraNumber = 1,
            sanskrit = "अग्निमीळे पुरोहितं\nयज्ञस्य देवमृत्विजम्\nहोतारं रत्नधातमम्",
            transliteration = "Agnim īḷe puróhitaṃ\nYajñásya devám ṛtvíjam\nHótāraṃ ratnadhātamam",
            translation = "I laud Agni, the household priest, the divine minister of sacrifice, the invoker, bestowing most wealth.",
            preview = "The opening hymn of the Rig Veda, praising Agni.",
            audioUrl = "$BASE_AUDIO_URL/1/1.mp3"
        ),
        Mantra(
            id = "4",
            name = "Nasadiya Sukta (Hymn of Creation)",
            mandalaNumber = 10,
            suktaNumber = 129,
            mantraNumber = 1,
            sanskrit = "नासदासीन्नो सदासीत्तदानीं\nनासीद्रजो नो व्योमा परो यत्\nकिमावरीवः कुह कस्य शर्मन्\nअम्भः किमासीद्गहनं गभीरम्",
            transliteration = "Násadāsīn nó sádāsīttadā́nīṃ\nNā́sīdrájo nó vyòmā paró yát\nKím āvarīvaḥ kúha káṣya śármann\nÁmbhaḥ kím āsīd gáhanaṃ gabhīrám",
            translation = "Then even nothingness was not, nor existence. There was no air then, nor the heavens beyond it. What covered it? Where was it? In whose keeping? Was there then cosmic water, in depths unfathomed?",
            preview = "A philosophical hymn questioning creation.",
            audioUrl = "$BASE_AUDIO_URL/10/129.mp3"
        ),
        Mantra(
            id = "5",
            name = "Vayu Sukta (Rig Veda 1.134.1)",
            mandalaNumber = 1,
            suktaNumber = 134,
            mantraNumber = 1,
            sanskrit = "वायवा याहि दर्शतेमे\nसोमाः अरंकृताः\nतेषां पाहि श्रुधी हवम्",
            transliteration = "Vāyavā yāhi darśatáme\nSómaḥ araṃkṛtā́ḥ\nTéṣāṃ pāhi śrudhī hávam",
            translation = "Come hither, Vayu, manifest! These Soma juices are prepared. Drink of them and listen to our call.",
            preview = "A hymn dedicated to Vayu, the god of wind.",
            audioUrl = "$BASE_AUDIO_URL/1/134.mp3"
        ),
        Mantra(
            id = "6",
            name = "Varuna Sukta (Rig Veda 1.25.1)",
            mandalaNumber = 1,
            suktaNumber = 25,
            mantraNumber = 1,
            sanskrit = "यत्किंचेदं वरुण दैव्ये जने\nअभिद्रोहं मनुष्याश्चरामसि\nअचित्ती यत्तव धर्मा युयोपिम\nमा नस्तस्मादेनसो देव रीरिषः",
            transliteration = "Yatkíṃ cedáṃ varuṇa daívyā́ jane\nAbhidróhaṃ manuṣyā́ścarāmasi\nAcíttī yáttáva dhármā yuyopíma\nMā́ nas tásmādenaso deva rīriṣaḥ",
            translation = "Whatever sin we mortals have committed against the divine folk, O Varuna, whenever we have violated your laws through thoughtlessness, O God, do not punish us for that sin.",
            preview = "A hymn seeking forgiveness and guidance from Varuna.",
            audioUrl = "$BASE_AUDIO_URL/1/25.mp3"
        ),
        Mantra(
            id = "7",
            name = "Surya Sukta (Rig Veda 1.50.1)",
            mandalaNumber = 1,
            suktaNumber = 50,
            mantraNumber = 1,
            sanskrit = "उद्वयं तमसस्परि\nज्योतिः पश्यन्त उत्तरम्\nदेवं देवत्रा सूर्यमगन्म\nज्योतिरुत्तमम्",
            transliteration = "Úd vayáṃ támasas pári\nJyótih páśyanta uttaram\nDeváṃ devátrā súryam áganma\nJyótir úttamam",
            translation = "We have arisen from darkness, seeing the higher light. We have reached the Sun, the god among gods, the highest light.",
            preview = "An invocation to Surya, the Sun god, for light and knowledge.",
            audioUrl = "$BASE_AUDIO_URL/1/50.mp3"
        ),
        Mantra(
            id = "8",
            name = "Indra Sukta (Rig Veda 1.32.1 - Vrtraha)",
            mandalaNumber = 1,
            suktaNumber = 32,
            mantraNumber = 1,
            sanskrit = "इन्द्रस्य नु वीर्याणि\nप्र वोचं यानि चकार प्रथमानि वज्रभृत्\nअहन्नहिमन्वपस्ततर्द\nप्र वक्षणा अभिनत्पर्वतानाम्",
            transliteration = "Índrasya nú vī́ryāṇi\nPrá vocaṃ yā́ni cakā́ra práthamā́ni vajrabhr̥t\nÁhannáhim ánvapás tatarda\nPrá vakṣaṇā́ abhinat parvatā́nām",
            translation = "Now I shall proclaim the heroic deeds of Indra, which the wielder of the thunderbolt performed first. He slew the dragon and released the waters, he split open the bellies of mountains.",
            preview = "Celebrates Indra's victory over the demon Vṛtra.",
            audioUrl = "$BASE_AUDIO_URL/1/32.mp3"
        ),
        Mantra(
            id = "9",
            name = "Soma Pavamana Sukta (Rig Veda 9.1.1)",
            mandalaNumber = 9,
            suktaNumber = 1,
            mantraNumber = 1,
            sanskrit = "अभि त्यं देवमध्वरं\nहोत्रे चोदाति सुक्रतुः\nसोमः पवित्रे अत्यः",
            transliteration = "Ábhi tyáṃ devám ádhvaraṃ\nHótre codā́ti sukratúḥ\nSómaḥ pavítre átyaḥ",
            translation = "He who is wise and skillful urges the divine priest forward for the sacrifice. Soma flows through the filter.",
            preview = "A hymn to Soma, the purifying divine drink.",
            audioUrl = "$BASE_AUDIO_URL/9/1.mp3"
        ),
        Mantra(
            id = "10",
            name = "Rudra Sukta (Rig Veda 1.114.1)",
            mandalaNumber = 1,
            suktaNumber = 114,
            mantraNumber = 1,
            sanskrit = "इमा रुद्राय तवसे\nकपर्दिने क्षयद्वीराय\nप्र भरामहे मतीः\nयथा शमसद्द्विपदे चतुष्पदे",
            transliteration = "Imā́ rudrā́ya távase\nKapardíne kṣayádvīrāya\nPrá bharāmahe matī́ḥ\nYáthā śám asad dvípadé catúṣpade",
            translation = "To the mighty Rudra with braided hair, the destroyer of heroes, we offer these prayers, so that he may bring peace to both two-footed and four-footed beings.",
            preview = "An invocation to Rudra for protection and welfare.",
            audioUrl = "$BASE_AUDIO_URL/9/1.mp3"
        ),

        // --- Additional Mantras with Audio ---

        Mantra(
            id = "11",
            name = "Ganapati Atharvashirsha (Mantra)",
            mandalaNumber = 0, // Not from Rig Veda Mandalas directly
            suktaNumber = 0,
            mantraNumber = 0,
            sanskrit = "ॐ नमस्ते गणपतये\nत्वमेव प्रत्यक्षं तत्त्वमसि\nत्वमेव केवलं कर्तासि\nत्वमेव केवलं धर्तासि\nत्वमेव केवलं हर्तासि",
            transliteration = "Oṃ Namaste Gaṇapataye\nTvameva Pratyakṣaṁ Tattvamasi\nTvameva Kevalaṁ Kartāsi\nTvameva Kevalaṁ Dhartāsi\nTvameva Kevalaṁ Hartāsi",
            translation = "Salutations to Lord Ganapati. You are the visible reality. You alone are the creator, sustainer, and destroyer.",
            preview = "A popular prayer dedicated to Lord Ganesha.",
            audioUrl = "$BASE_AUDIO_URL/9/1.mp3"
        ),
        Mantra(
            id = "12",
            name = "Maha Mrityunjaya Mantra",
            mandalaNumber = 7, // Associated with Rig Veda Mandala 7, Sukta 59
            suktaNumber = 59,
            mantraNumber = 12,
            sanskrit = "ॐ त्र्यम्बकं यजामहे सुगन्धिं पुष्टिवर्धनम्\nउर्वारुकमिव बन्धनान् मृत्योर्मुक्षीय मामृतात्",
            transliteration = "Oṃ Tryambakaṁ Yajamahe Sugandhiṁ Puṣṭivardhanam\nUrvarukamiva Bandhanān Mṛtyormukṣīya Mā'mṛtāt",
            translation = "We worship the three-eyed one, who is fragrant and nourishes all. May he liberate us from death for the sake of immortality, just as the cucumber is severed from its bondage to the creeper.",
            preview = "A powerful mantra for health, longevity, and liberation.",
            audioUrl = "$BASE_AUDIO_URL/7/59.mp3"
        ),
        Mantra(
            id = "13",
            name = "Shanti Mantra (Om Sarvesham)",
            mandalaNumber = 0,
            suktaNumber = 0,
            mantraNumber = 0,
            sanskrit = "ॐ सर्वेषां स्वस्तिर् भवतु\nसर्वेषां शान्तिर् भवतु\nसर्वेषां पूर्णं भवतु\nसर्वेषां मङ्गलं भवतु",
            transliteration = "Oṃ Sarveṣāṁ Svastir Bhavatu\nSarveṣāṁ Śāntir Bhavatu\nSarveṣāṁ Pūrṇaṁ Bhavatu\nSarveṣāṁ Maṅgalaṁ Bhavatu",
            translation = "May there be well-being for all. May there be peace for all. May there be fullness for all. May there be auspiciousness for all.",
            preview = "A universal prayer for peace and welfare.",
            audioUrl = "$BASE_AUDIO_URL/9/1.mp3"
        )
    )

    fun searchMantras(query: String): List<Mantra> {
        if (query.isBlank()) return mantras
        return mantras.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.transliteration.contains(query, ignoreCase = true) ||
                    it.sanskrit.contains(query, ignoreCase = true) ||
                    it.preview.contains(query, ignoreCase = true) ||
                    it.mandalaNumber.toString() == query
        }
    }
}