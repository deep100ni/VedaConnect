package com.DeepSoni.vedaconnect.repository

// Add this line
import com.DeepSoni.vedaconnect.data.Sukta


object MandalaOneSuktasRepository {

    val suktas: List<Sukta> = listOf(
        // Sukta 1
        Sukta(
            id = "M1S1",
            name = "Sukta 1",
            mandalaNumber = 1,
            suktaNumber = 1,
            sanskrit = """
                अग्निमीळे पुरोहितं यज्ञस्य देवमृत्विजं ।होतारं रत्नधातमं ॥
                अग्निः पूर्वेभिर्ऋषिभिरीड्यो नूतनैरुत ।स देवाँ एह वक्षति ॥
                अग्निना रयिमश्नवत्पोषमेव दिवेदिवे ।यशसं वीरवत्तमं ॥
                अग्ने यं यज्ञमध्वरं विश्वतः परिभूरसि ।स इद्देवेषु गच्छति ॥
                अग्निर्होता कविक्रतुः सत्यश्चित्रश्रवस्तमः ।देवो देवेभिरा गमत् ॥
                यदंग दाशुषे त्वमग्ने भद्रं करिष्यसि ।तवेत्तत्सत्यमंगिरः ॥
                उप त्वाग्ने दिवेदिवे दोषावस्तर्धिया वयं ।नमो भरंत एमसि ॥
                राजंतमध्वराणां गोपामृतस्य दीदिविं ।वर्धमानं स्वे दमे ॥
                स नः पितेव सूनवेऽग्ने सूपायनो भव ।सचस्वा नः स्वस्तये ॥
            """.trimIndent(),
            transliteration = """
                agním ǀ īḷe ǀ puráḥ-hitam ǀ yajñásya ǀ devám ǀ ṛtvíjam ǀhótāram ǀ ratna-dhā́tamam ǁ
                agníḥ ǀ pū́rvebhiḥ ǀ ṛ́ṣi-bhiḥ ǀ ī́ḍyaḥ ǀ nū́tanaiḥ ǀ utá ǀsáḥ ǀ devā́n ǀ ā́ ǀ ihá ǀ vakṣati ǁ
                agnínā ǀ rayím ǀ aśnavat ǀ póṣam ǀ evá ǀ divé-dive ǀyaśásam ǀ vīrávat-tamam ǁ
                ágne ǀ yám ǀ yajñám ǀ adhvarám ǀ viśvátaḥ ǀ pari-bhū́ḥ ǀ ási ǀsáḥ ǀ ít ǀ devéṣu ǀ gacchati ǁ
                agníḥ ǀ hótā ǀ kaví-kratuḥ ǀ satyáḥ ǀ citráśravaḥ-tamaḥ ǀdeváḥ ǀ devébhiḥ ǀ ā́ ǀ gamat ǁ
                yát ǀ aṅgá ǀ dāśúṣe ǀ tvám ǀ ágne ǀ bhadrám ǀ kariṣyási ǀtáva ǀ ít ǀ tát ǀ satyám ǀ aṅgiraḥ ǁ
                úpa ǀ tvā ǀ agne ǀ divé-dive ǀ dóṣā-vastaḥ ǀ dhiyā́ ǀ vayám ǀnámaḥ ǀ bhárantaḥ ǀ ā́ ǀ imasi ǁ
                rā́jantam ǀ adhvarā́ṇām ǀ gopā́m ǀ ṛtásya ǀ dī́divim ǀvárdhamānam ǀ své ǀ dáme ǁ
                sáḥ ǀ naḥ ǀ pitā́-iva ǀ sūnáve ǀ ágne ǀ su-upāyanáḥ ǀ bhava ǀsácasva ǀ naḥ ǀ svastáye ǁ
            """.trimIndent(),
            translation = """
                “I glorifyAgni, the high priest of the sacrifice, the divine, the ministrant, who presents the oblation (to the gods), and is the possessor of great wealth.”
                “May thatAgniwho is to be celebrated by both ancient and modern sages conduct the gods hither.”
                “ThroughAgnithe worshipper obtains that affluence which increases day by day, which is the source of fame and multiplier of mankind.”
                “Agni, the unobstructed sacrifice of which you are on every side the protector, assuredly reaches the gods.”
                “MayAgni, the presenter of oblations, the attainer of knowledge, he who is true, renowned, and divine, come hither with the gods.”
                “Whatever good you may,Agni, bestow upon the giver (of the oblation), that verily,Aṅgirasshall revert to you.”
                “We approach you,Agni, with reverential homage in our thoughts, daily, both morning and evening.”
                “You, the radiant, the protector of sacrifice, the constant illuminator of truth, increasing in your own dwelling.”
                “Agni, be to us easy of access, as is a father to his son; be ever present with us for our good.”
            """.trimIndent(),
            preview = "“I glorifyAgni, the high priest of the sacrifice, the divine, the ministrant, who presents the oblation (to the gods), and is the possessor of great wealth.”"
        ),

        // Sukta 2
        Sukta(
            id = "M1S2",
            name = "Sukta 2",
            mandalaNumber = 1,
            suktaNumber = 2,
            sanskrit = """
                वायवा याहि दर्शतेमे सोमा अरंकृताः ।तेषां पाहि श्रुधी हवं ॥
                वाय उक्थेभिर्जरंते त्वामच्छा जरितारः ।सुतसोमा अहर्विदः ॥
                वायो तव प्रपृंचती धेना जिगाति दाशुषे ।उरूची सोमपीतये ॥
                इंद्रवायू इमे सुता उप प्रयोभिरा गतं ।इंदवो वामुशंति हि ॥
                वायविंद्रश्च चेतथः सुतानां वाजिनीवसू ।तावा यातमुप द्रवत् ॥
                वायविंद्रश्च सुन्वत आ यातमुप निष्कृतं ।मक्ष्वित्था धिया नरा ॥
                मित्रं हुवे पूतदक्षं वरुणं च रिशादसं ।धियं घृताचीं साधंता ॥
                ऋतेन मित्रावरुणावृतावृधावृतस्पृशा ।क्रतुं बृहंतमाशाथे ॥
                कवी नो मित्रावरुणा तुविजाता उरुक्षया ।दक्षं दधाते अपसं ॥
            """.trimIndent(),
            transliteration = """
                vā́yo íti ǀ ā́ ǀ yāhi ǀ darśata ǀ imé ǀ sómāḥ ǀ áram-kṛtāḥ ǀtéṣām ǀ pāhi ǀ śrudhí ǀ hávam ǁ
                vā́yo íti ǀ ukthébhiḥ ǀ jarante ǀ tvā́m ǀ áccha ǀ jaritā́raḥ ǀsutá-somāḥ ǀ ahaḥ-vídaḥ ǁ
                vā́yo íti ǀ táva ǀ pra-pṛñcatī́ ǀ dhénā ǀ jigāti ǀ dāśúṣe ǀurūcī́ ǀ sóma-pītaye ǁ
                índravāyū íti ǀ imé ǀ sutā́ḥ ǀ úpa ǀ práyaḥ-bhiḥ ǀ ā́ ǀ gatam ǀíndavaḥ ǀ vām ǀ uśánti ǀ hí ǁ
                vā́yo íti ǀ índraḥ ǀ ca ǀ cetathaḥ ǀ sutā́nām ǀ vājinīvasū íti vājinī-vasū ǀtáu ǀ ā́ ǀ yātam ǀ úpa ǀ dravát ǁ
                vā́yo íti ǀ índraḥ ǀ ca ǀ sunvatáḥ ǀ ā́ ǀ yātam ǀ úpa ǀ niḥ-kṛtám ǀmakṣú ǀ itthā́ ǀ dhiyā́ ǀ narā ǁ
                mitrám ǀ huve ǀ pūtá-dakṣam ǀ váruṇam ǀ ca ǀ riśā́dasam ǀdhíyam ǀ ghṛtā́cīm ǀ sā́dhantā ǁ
                ṛténa ǀ mitrāvaruṇau ǀ ṛta-vṛdhau ǀ ṛta-spṛśā ǀkrátum ǀ bṛhántam ǀ āśāthe íti ǁ
                kavī́ íti ǀ naḥ ǀ mitrā́váruṇā ǀ tuvi-jātáu ǀ uru-kṣáyā ǀdákṣam ǀ dadhāte íti ǀ apásam ǁ
            """.trimIndent(),
            translation = """
                “Vāyu, plural asant to behold, approach; these libations are prepared for you drink of them; hear our invocation.”
                “Vāyu, your praisers praise you with holy praises having poured outSomajuice, and knowing the (fit) season.”
                “Vāyu, your approving speech comes to the giver (of the libation), and to many (others who invite you) to drink of theSomajuice.”
                “IndraandVāyu, these libations are poured out (for you); come hither with food (for us); verily the drops (of theSomajuice) await you both. ”
                “IndraandVāyu, abiding in the sacrificial rite, you are aware of these libations; come both (then) quickly hither.”
                “VāyuandIndra, come to the rite of the sacrificer, for thus, men, will completion be speedily (attained) by the ceremony.”
                “I invokeMitra, of pure vigour, andVaruṇa, the devourer of foes; the joint accomplishers of the act bestowing water (on the earth).”
                “MitraandVaruṇa, augmenters of water, dispensers of water, you connect this perfect rite with its true (reward).”
                “SapientMitraandVaruṇa, prosper our sacrifice and increase our strength; you are born for the benefit of many, you are the refuge of multitudes.”
            """.trimIndent(),
            preview = "“Vāyu, plural asant to behold, approach; these libations are prepared for you drink of them; hear our invocation.”"
        ),

        // Sukta 3
        Sukta(
            id = "M1S3",
            name = "Sukta 3",
            mandalaNumber = 1,
            suktaNumber = 3,
            sanskrit = """
                अश्विना यज्वरीरिषो द्रवत्पाणी शुभस्पती ।पुरुभुजा चनस्यतं ॥
                अश्विना पुरुदंससा नरा शवीरया धिया ।धिष्ण्या वनतं गिरः ॥
                दस्रा युवाकवः सुता नासत्या वृक्तबर्हिषः ।आ यातं रुद्रवर्तनी ॥
                इंद्रा याहि चित्रभानो सुता इमे त्वायवः ।अण्वीभिस्तना पूतासः ॥
                इंद्रा याहि धियेषितो विप्रजूतः सुतावतः ।उप ब्रह्माणि वाघतः ॥
                इंद्रा याहि तूतुजान उप ब्रह्माणि हरिवः ।सुते दधिष्व नश्चनः ॥
                ओमासश्चर्षणीधृतो विश्वे देवास आ गत ।दाश्वांसो दाशुषः सुतं ॥
                विश्वे देवासो अप्तुरः सुतमा गंत तूर्णयः ।उस्रा इव स्वसराणि ॥
                विश्वे देवासो अस्रिध एहिमायासो अद्रुहः ।मेधं जुषंत वह्नयः ॥
                पावका नः सरस्वती वाजेभिर्वाजिनीवती ।यज्ञं वष्टु धियावसुः ॥
                चोदयित्री सूनृतानां चेतंती सुमतीनां ।यज्ञं दधे सरस्वती ॥
                महो अर्णः सरस्वती प्र चेतयति केतुना ।धियो विश्वा वि राजति ॥
            """.trimIndent(),
            transliteration = """
                áśvinā ǀ yájvarīḥ ǀ íṣaḥ ǀ drávatpāṇī íti drávat-pāṇī ǀ śúbhaḥ ǀ patī íti ǀpúru-bhujā ǀ canasyátam ǁ
                áśvinā ǀ púru-daṃsasā ǀ nárā ǀ śávīrayā ǀ dhiyā́ ǀdhíṣṇyā ǀ vánatam ǀ gíraḥ ǁ
                dásrā ǀ yuvā́kavaḥ ǀ sutā́ḥ ǀ nā́satyā ǀ vṛktá-barhiṣaḥ ǀā́ ǀ yātam ǀ rudravartanī íti rudra-vartanī ǁ
                índra ǀ ā́ ǀ yāhi ǀ citrabhāno íti citra-bhāno ǀ sutā́ḥ ǀ imé ǀ tvā-yávaḥ ǀáṇvībhiḥ ǀ tánā ǀ pūtā́saḥ ǁ
                índra ǀ ā́ ǀ yāhi ǀ dhiyā́ ǀ iṣitáḥ ǀ vípra-jūtaḥ ǀ sutá-vataḥ ǀúpa ǀ bráhmāṇi ǀ vāghátaḥ ǁ
                índra ǀ ā́ ǀ yāhi ǀ tū́tujānaḥ ǀ úpa ǀ bráhmāṇi ǀ hari-vaḥ ǀsuté ǀ dadhiṣva ǀ naḥ ǀ cánaḥ ǁ
                ómāsaḥ ǀ carṣaṇi-dhṛtaḥ ǀ víśve ǀ devāsaḥ ǀ ā́ ǀ gata ǀdāśvā́ṃsaḥ ǀ dāśúṣaḥ ǀ sutám ǁ
                víśve ǀ devā́saḥ ǀ ap-túraḥ ǀ sutám ǀ ā́ ǀ ganta ǀ tū́rṇayaḥ ǀusrā́ḥ-iva ǀ svásarāṇi ǁ
                víśve ǀ devā́saḥ ǀ asrídhaḥ ǀ éhi-māyāsaḥ ǀ adrúhaḥ ǀmédham ǀ juṣanta ǀ váhnayaḥ ǁ
                pāvakā́ ǀ naḥ ǀ sárasvatī ǀ vā́jebhiḥ ǀ vājínī-vatī ǀyajñám ǀ vaṣṭu ǀ dhiyā́-vasuḥ ǁ
                codayitrī́ ǀ sūnṛ́tānām ǀ cétantī ǀ su-matīnā́m ǀyajñám ǀ dadhe ǀ sárasvatī ǁ
                maháḥ ǀ árṇaḥ ǀ sárasvatī ǀ prá ǀ cetayati ǀ ketúnā ǀdhíyaḥ ǀ víśvāḥ ǀ ví ǀ rājati ǁ
            """.trimIndent(),
            translation = """
                “Aśvins, cherishers of pious acts, long-armed, accept with outstretchedhandsthe sacrificial viands.”
                “Aśvins, abounding in mighty acts, guides (of devotion), endowed with fortitude, listen with unaverted minds to our praises.”
                “Aśvins, destroyers of foes, exempt from untruth, leaders in the van of heroes, come to the mixed libations sprinkled on the lopped sacred grass.”
                “Indra, of wonderful splendour, come hither; these libations, ever pure, expressed by the fingers (of the priests) are desirous of you.”
                “Indra, apprehended by the understanding and appreciated by the wise, approach and accept the prayers of the priest as he offers the libation.”
                “FleetIndrawith the tawny coursers, come hither to the prayer (of the priest), and in this libation accept our (proffered) food.”
                “Universal Gods, protectors and supporters of men, bestowers (of rewards) come to the libation of the worshipper.”
                “May the swift-moving universal Gods, the shedders of rain, come to the libation, as the solar rays come diligently tot he days.”
                “May the universal Gods, who are exempt from decay, omniscient, devoid of malice, and bearers of (riches), accept the sacrifice.”
                “MaySarasvatī, the purifier, the bestower of food, the recompenser of worship with wealth, be attracted by our offered viands to our rite.”
                “Sarasvatī, the inspirer of those who delight in truth, the instrumental uctress of the right-minded, has accepted our sacrifice.”
                “Sarasvatī, makes manifest by her acts a mighty river, and (in her own form) enlightens all understandings.”
            """.trimIndent(),
            preview = "“Aśvins, cherishers of pious acts, long-armed, accept with outstretchedhandsthe sacrificial viands.”"
        ),

        // Sukta 4
        Sukta(
            id = "M1S4",
            name = "Sukta 4",
            mandalaNumber = 1,
            suktaNumber = 4,
            sanskrit = """
                सुरूपकृत्नुमूतये सुदुघामिव गोदुहे ।जुहूमसि द्यविद्यवि ॥
                उप नः सवना गहि सोमस्य सोमपाः पिब ।गोदा इद्रेवतो मदः ॥
                अथा ते अंतमानां विद्याम सुमतीनां ।मा नो अति ख्य आ गहि ॥
                परेहि विग्रमस्तृतमिंद्रं पृच्छा विपश्चितं ।यस्ते सखिभ्य आ वरं ॥
                उत ब्रुवंतु नो निदो निरन्यतश्चिदारत ।दधाना इंद्र इद्दुवः ॥
                उत नः सुभगाँ अरिर्वोचेयुर्दस्म कृष्टयः ।स्यामेदिंद्रस्य शर्मणि ॥
                एमाशुमाशवे भर यज्ञश्रियं नृमादनं ।पतयन्मंदयत्सखं ॥
                अस्य पीत्वा शतक्रतो घनो वृत्राणामभवः ।प्रावो वाजेषु वाजिनं ॥
                तं त्वा वाजेषु वाजिनं वाजयामः शतक्रतो ।धनानामिंद्र सातये ॥
                यो रायोवनिर्महान्त्सुपारः सुन्वतः सखा ।तस्मा इंद्राय गायत ॥
            """.trimIndent(),
            transliteration = """
                surūpa-kṛtnúm ǀ ūtáye ǀ sudúghām-iva ǀ go-dúhe ǀjuhūmási ǀ dyávi-dyavi ǁ
                úpa ǀ naḥ ǀ sávanā ǀ ā́ ǀ gahi ǀ sómasya ǀ soma-pāḥ ǀ piba ǀgo-dā́ḥ ǀ ít ǀ revátaḥ ǀ mádaḥ ǁ
                átha ǀ te ǀ ántamānām ǀ vidyā́ma ǀ su-matīnā́m ǀmā́ ǀ naḥ ǀ áti ǀ khyaḥ ǀ ā́ ǀ gahi ǁ
                párā ǀ ihi ǀ vígram ǀ ástṛtam ǀ índram ǀ pṛccha ǀ vipaḥ-cítam ǀyáḥ ǀ te ǀ sákhi-bhyaḥ ǀ ā́ ǀ váram ǁ
                utá ǀ bruvantu ǀ naḥ ǀ nídaḥ ǀ níḥ ǀ anyátaḥ ǀ cit ǀ ārata ǀdádhānāḥ ǀ índre ǀ ít ǀ dúvaḥ ǁ
                utá ǀ naḥ ǀ su-bhágān ǀ aríḥ ǀ vocéyuḥ ǀ dasma ǀ kṛṣṭáyaḥ ǀsyā́ma ǀ ít ǀ índrasya ǀ śármaṇi ǁ
                ā́ ǀ īm ǀ āśúm ǀ āśáve ǀ bhara ǀ yajña-śríyam ǀ nṛ-mā́danam ǀpatayát ǀ mandayát-sakham ǁ
                asyá ǀ pītvā́ ǀ śatakrato íti śata-krato ǀ ghanáḥ ǀ vṛtrā́ṇām ǀ abhavaḥ ǀprá ǀ āvaḥ ǀ vā́jeṣu ǀ vājínam ǁ
                tám ǀ tvā ǀ vā́jeṣu ǀ vājínam ǀ vājáyāmaḥ ǀ śatakrato íti śata-krato ǀdhánānām ǀ indra ǀ sātáye ǁ
                yáḥ ǀ rāyáḥ ǀ avániḥ ǀ mahā́n ǀ su-pāráḥ ǀ sunvatáḥ ǀ sákhā ǀtásmai ǀ índrāya ǀ gāyata ǁ
            """.trimIndent(),
            translation = """
                “Day by day we invoke the doer of good works for our protection, as a good cow for the milking (is called by the milker).”
                “Drinker of theSomajuice, come to our (daily) rites, and drink of the libation; the satisfaction of (you who are) the bestower of riches, is verily (the cause of) the gift of cattle.”
                “We recognize you in the midst of the right-minded, who are nearest to you; come to us; pass us not by to reveal (yourself to others).”
                “Go, worshipper, to the wise and uninjuredIndra, who bestows the best (of blessings) on your friends, and ask him of the (fitness of the) learned (priest who recites his praise).”
                “Let our ministers, earnestly performing his worship, exclaim, Depart your revilers from hence and every other plural ce (where he is adored).”
                “Destroyer of foes, let our enemies say we are prosperous; let men (congratulate us); may we ever abide, in the felicity (derived from the favour) ofIndra.”
                “Offer toIndra, the pervader (of every rite of libation), the juice that is present (at the three ceremonies), the grace of the sacrifice, the exhilarator of mankind, the perfecter of the act, the favourite of (that Indra) who gives happiness (to the offerer).”
                “Having drunk,Śatakratu, of this (Somajuice), you became the slayer of theVṛtras; you defend the warrior in battle.”
                “We offer to you,Śatakratu, the mighty in battle, (sacrificial) food for the acquisition,Indra, of riches.”
                “Sing unto thatIndrawho is the protector of wealth, the mighty, the accomplisher of good deeds, the friend of the offerer of the libation.”
            """.trimIndent(),
            preview = "“Day by day we invoke the doer of good works for our protection, as a good cow for the milking (is called by the milker).”"
        ),

        // Sukta 5
        Sukta(
            id = "M1S5",
            name = "Sukta 5",
            mandalaNumber = 1,
            suktaNumber = 5,
            sanskrit = """
                आ त्वेता नि षीदतेंद्रमभि प्र गायत ।सखायः स्तोमवाहसः ॥
                पुरूतमं पुरूणामीशानं वार्याणां ।इंद्रं सोमे सचा सुते ॥
                स घा नो योग आ भुवत्स राये स पुरंध्यां ।गमद्वाजेभिरा स नः ॥
                यस्य संस्थे न वृण्वते हरी समत्सु शत्रवः ।तस्मा इंद्राय गायत ॥
                सुतपाव्ने सुता इमे शुचयो यंति वीतये ।सोमासो दध्याशिरः ॥
                त्वं सुतस्य पीतये सद्यो वृद्धो अजायथाः ।इंद्र ज्यैष्ठ्याय सुक्रतो ॥
                आ त्वा विशंत्वाशवः सोमास इंद्र गिर्वणः ।शं ते संतु प्रचेतसे ॥
                त्वां स्तोमा अवीवृधन्त्वामुक्था शतक्रतो ।त्वां वर्धंतु नो गिरः ॥
                अक्षितोतिः सनेदिमं वाजमिंद्रः सहस्रिणं ।यस्मिन्विश्वानि पौंस्या ॥
                मा नो मर्ता अभि द्रुहन्तनूनामिंद्र गिर्वणः ।ईशानो यवया वधं ॥
            """.trimIndent(),
            transliteration = """
                ā́ ǀ tú ǀ ā́ ǀ ita ǀ ní ǀ sīdata ǀ índram ǀ abhí ǀ prá ǀ gāyata ǀsákhāyaḥ ǀ stóma-vāhasaḥ ǁ
                puru-támam ǀ purūṇā́m ǀ ī́śānam ǀ vā́ryāṇām ǀíndram ǀ sóme ǀ sácā ǀ suté ǁ
                sáḥ ǀ gha ǀ naḥ ǀ yóge ǀ ā́ ǀ bhuvat ǀ sáḥ ǀ rāyé ǀ sáḥ ǀ púram-dhyām ǀgámat ǀ vā́jebhiḥ ǀ ā́ ǀ sáḥ ǀ naḥ ǁ
                yásya ǀ sam-sthé ǀ ná ǀ vṛṇváte ǀ hárī íti ǀ samát-su ǀ śátravaḥ ǀtásmai ǀ índrāya ǀ gāyata ǁ
                suta-pā́vne ǀ sutā́ḥ ǀ imé ǀ śúcayaḥ ǀ yanti ǀ vītáye ǀsómāsaḥ ǀ dádhi-āśiraḥ ǁ
                tvám ǀ sutásya ǀ pītáye ǀ sadyáḥ ǀ vṛddháḥ ǀ ajāyathāḥ ǀíndra ǀ jyáiṣṭhyāya ǀ sukrato íti su-krato ǁ
                ā́ ǀ tvā ǀ viśantu ǀ āśávaḥ ǀ sómāsaḥ ǀ indra ǀ girvaṇaḥ ǀśám ǀ te ǀ santu ǀ prá-cetase ǁ
                tvā́m ǀ stómāḥ ǀ avīvṛdhan ǀ tvā́m ǀ ukthā́ ǀ śatakrato íti śata-krato ǀtvā́m ǀ vardhantu ǀ naḥ ǀ gíraḥ ǁ
                ákṣita-ūtiḥ ǀ sanet ǀ imám ǀ vā́jam ǀ índraḥ ǀ sahasríṇam ǀyásmin ǀ víśvāni ǀ páuṃsyā ǁ
                mā́ ǀ naḥ ǀ mártāḥ ǀ abhí ǀ druhan ǀ tanū́nām ǀ indra ǀ girvaṇaḥ ǀī́śānaḥ ǀ yavaya ǀ vadhám ǁ
            """.trimIndent(),
            translation = """
                “Hasten hither, friends, offering praises; sit down, and sing repeatedly the praises ofIndra.”
                “When the libation is poured forth, respectively praiseIndra, the discomfiter of many enemies, the lord of many blessings.”
                “May he be to us for the attainment of our objects; may he be to us for the acquisition of riches; may he be to us for the acquisition of knowledge; may he come to us with food.”
                “Sing to thatIndra, whose enemies in combat await not his coursers harnessed in his car.”
                “These pureSomajuices, mixed with curds, are poured out for the satisfaction of the drinker of the libations.”
                “Your,Indra, performer of good works, has suddenly become of augmented vigour for the sake of drinking the libation, and (maintaining) seniority (among the gods).”
                “Indra, who are the object of praises, may these pervadingSomajuices enter into you; may they be propitious for your (attainment of) superior intelligence.”
                “The chants (of theSoma) have magnified you,Śatakratu, the hymns (of the Ṛk) have magnified you; may our praises magnify you.”
                “MayIndra, the unobstructed protector, enjoy these manifold (sacrificial) viands, in which all manly properties abide.”
                “Indra, who are the object of praises, let no men do injury to our person ns; you are mighty, keep off violence.”
            """.trimIndent(),
            preview = "“Hasten hither, friends, offering praises; sit down, and sing repeatedly the praises ofIndra.”"
        )
    )
}