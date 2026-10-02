package com.example.data.datasource

import com.example.data.model.Ayah
import com.example.data.model.Juz
import com.example.data.model.Surah

object OfflineQuranData {

    // All 114 Surahs of the Holy Quran with authentic metadata
    val allSurahs: List<Surah> = listOf(
        Surah(1, "Al-Fatihah", "الفاتحة", "The Opening", 7, "Meccan"),
        Surah(2, "Al-Baqarah", "البقرة", "The Cow", 286, "Medinan"),
        Surah(3, "Ali 'Imran", "آل عمران", "Family of Imran", 200, "Medinan"),
        Surah(4, "An-Nisa", "النساء", "The Women", 176, "Medinan"),
        Surah(5, "Al-Ma'idah", "المائدة", "The Table Spread", 120, "Medinan"),
        Surah(6, "Al-An'am", "الأنعام", "The Cattle", 165, "Meccan"),
        Surah(7, "Al-A'raf", "الأعراف", "The Heights", 206, "Meccan"),
        Surah(8, "Al-Anfal", "الأنفال", "The Spoils of War", 75, "Medinan"),
        Surah(9, "At-Tawbah", "التوبة", "The Repentance", 129, "Medinan"),
        Surah(10, "Yunus", "يونس", "Jonah", 109, "Meccan"),
        Surah(11, "Hud", "هود", "Hud", 123, "Meccan"),
        Surah(12, "Yusuf", "يوسف", "Joseph", 111, "Meccan"),
        Surah(13, "Ar-Ra'd", "الرعد", "The Thunder", 43, "Medinan"),
        Surah(14, "Ibrahim", "إبراهيم", "Abraham", 52, "Meccan"),
        Surah(15, "Al-Hijr", "الحجر", "The Rocky Tract", 99, "Meccan"),
        Surah(16, "An-Nahl", "النحل", "The Bee", 128, "Meccan"),
        Surah(17, "Al-Isra", "الإسراء", "The Night Journey", 111, "Meccan"),
        Surah(18, "Al-Kahf", "الكهف", "The Cave", 110, "Meccan"),
        Surah(19, "Maryam", "مريم", "Mary", 98, "Meccan"),
        Surah(20, "Taha", "طه", "Ta-Ha", 135, "Meccan"),
        Surah(21, "Al-Anbiya", "الأنبياء", "The Prophets", 112, "Meccan"),
        Surah(22, "Al-Hajj", "الحج", "The Pilgrimage", 78, "Medinan"),
        Surah(23, "Al-Mu'minun", "المؤمنون", "The Believers", 118, "Meccan"),
        Surah(24, "An-Nur", "النور", "The Light", 64, "Medinan"),
        Surah(25, "Al-Furqan", "الفرقان", "The Criterion", 77, "Meccan"),
        Surah(26, "Ash-Shu'ara", "الشعراء", "The Poets", 227, "Meccan"),
        Surah(27, "An-Naml", "النمل", "The Ant", 93, "Meccan"),
        Surah(28, "Al-Qasas", "القصص", "The Stories", 88, "Meccan"),
        Surah(29, "Al-'Ankabut", "العنكبوت", "The Spider", 69, "Meccan"),
        Surah(30, "Ar-Rum", "الروم", "The Romans", 60, "Meccan"),
        Surah(31, "Luqman", "لقمان", "Luqman", 34, "Meccan"),
        Surah(32, "As-Sajdah", "السجدة", "The Prostration", 30, "Meccan"),
        Surah(33, "Al-Ahzab", "الأحزاب", "The Combined Forces", 73, "Medinan"),
        Surah(34, "Saba", "سبأ", "Sheba", 54, "Meccan"),
        Surah(35, "Fatir", "فاطر", "The Originator", 45, "Meccan"),
        Surah(36, "Ya-Sin", "يس", "Ya-Sin", 83, "Meccan"),
        Surah(37, "As-Saffat", "الصافات", "Those Who Set The Ranks", 182, "Meccan"),
        Surah(38, "Sad", "ص", "The Letter Sad", 88, "Meccan"),
        Surah(39, "Az-Zumar", "الزمر", "The Troops", 75, "Meccan"),
        Surah(40, "Ghafir", "غافر", "The Forgiver", 85, "Meccan"),
        Surah(41, "Fussilat", "فصلت", "Explained in Detail", 54, "Meccan"),
        Surah(42, "Ash-Shura", "الشورى", "The Consultation", 53, "Meccan"),
        Surah(43, "Az-Zukhruf", "الزخرف", "The Ornaments of Gold", 89, "Meccan"),
        Surah(44, "Ad-Dukhan", "الدخان", "The Smoke", 59, "Meccan"),
        Surah(45, "Al-Jathiyah", "الجاثية", "The Crouching", 37, "Meccan"),
        Surah(46, "Al-Ahqaf", "الأحقاف", "The Wind-Curved Sandhills", 35, "Meccan"),
        Surah(47, "Muhammad", "محمد", "Muhammad", 38, "Medinan"),
        Surah(48, "Al-Fath", "الفتح", "The Victory", 29, "Medinan"),
        Surah(49, "Al-Hujurat", "الحجرات", "The Rooms", 18, "Medinan"),
        Surah(50, "Qaf", "ق", "The Letter Qaf", 45, "Meccan"),
        Surah(51, "Adh-Dhariyat", "الذاريات", "The Winnowing Winds", 60, "Meccan"),
        Surah(52, "At-Tur", "الطور", "The Mount", 49, "Meccan"),
        Surah(53, "An-Najm", "النجم", "The Star", 62, "Meccan"),
        Surah(54, "Al-Qamar", "القمر", "The Moon", 55, "Meccan"),
        Surah(55, "Ar-Rahman", "الرحمن", "The Beneficent", 78, "Medinan"),
        Surah(56, "Al-Waqi'ah", "الواقعة", "The Inevitable", 96, "Meccan"),
        Surah(57, "Al-Hadid", "الحديد", "The Iron", 29, "Medinan"),
        Surah(58, "Al-Mujadila", "المجادلة", "The Pleading Woman", 22, "Medinan"),
        Surah(59, "Al-Hashr", "الحشر", "The Exile", 24, "Medinan"),
        Surah(60, "Al-Mumtahanah", "الممتحنة", "She That Is To Be Examined", 13, "Medinan"),
        Surah(61, "As-Saff", "الصف", "The Ranks", 14, "Medinan"),
        Surah(62, "Al-Jumu'ah", "الجمعة", "The Congregation, Friday", 11, "Medinan"),
        Surah(63, "Al-Munafiqun", "المنافقون", "The Hypocrites", 11, "Medinan"),
        Surah(64, "At-Taghabun", "التغابن", "The Mutual Disillusion", 18, "Medinan"),
        Surah(65, "At-Talaq", "الطلاق", "The Divorce", 12, "Medinan"),
        Surah(66, "At-Tahrim", "التحريم", "The Prohibition", 12, "Medinan"),
        Surah(67, "Al-Mulk", "الملك", "The Sovereignty", 30, "Meccan"),
        Surah(68, "Al-Qalam", "القلم", "The Pen", 52, "Meccan"),
        Surah(69, "Al-Haqqah", "الحاقة", "The Reality", 52, "Meccan"),
        Surah(70, "Al-Ma'arij", "المعارج", "The Ascending Stairways", 44, "Meccan"),
        Surah(71, "Nuh", "نوح", "Noah", 28, "Meccan"),
        Surah(72, "Al-Jinn", "الجن", "The Jinn", 28, "Meccan"),
        Surah(73, "Al-Muzzammil", "المزمل", "The Enshrouded One", 20, "Meccan"),
        Surah(74, "Al-Muddaththir", "المدثر", "The Cloaked One", 56, "Meccan"),
        Surah(75, "Al-Qiyamah", "القيامة", "The Resurrection", 40, "Meccan"),
        Surah(76, "Al-Insan", "الإنسان", "The Man", 31, "Medinan"),
        Surah(77, "Al-Mursalat", "المرسلات", "The Emissaries", 50, "Meccan"),
        Surah(78, "An-Naba", "النبأ", "The Tidings", 40, "Meccan"),
        Surah(79, "An-Nazi'at", "النازعات", "Those Who Drag Forth", 46, "Meccan"),
        Surah(80, "'Abasa", "عبس", "He Frowned", 42, "Meccan"),
        Surah(81, "At-Takwir", "التكوير", "The Overthrowing", 29, "Meccan"),
        Surah(82, "Al-Infitar", "الانفطار", "The Cleaving", 19, "Meccan"),
        Surah(83, "Al-Mutaffifin", "المطففين", "The Defrauding", 36, "Meccan"),
        Surah(84, "Al-Inshiqaq", "الانشقاق", "The Splitting Open", 25, "Meccan"),
        Surah(85, "Al-Buruj", "البروج", "The Mansions of the Stars", 22, "Meccan"),
        Surah(86, "At-Tariq", "الطارق", "The Nightcomer", 17, "Meccan"),
        Surah(87, "Al-A'la", "الأعلى", "The Most High", 19, "Meccan"),
        Surah(88, "Al-Ghashiyah", "الغاشية", "The Overwhelming", 26, "Meccan"),
        Surah(89, "Al-Fajr", "الفجر", "The Dawn", 30, "Meccan"),
        Surah(90, "Al-Balad", "البلد", "The City", 20, "Meccan"),
        Surah(91, "Ash-Shams", "الشمس", "The Sun", 15, "Meccan"),
        Surah(92, "Al-Layl", "الليل", "The Night", 21, "Meccan"),
        Surah(93, "Ad-Duha", "الضحى", "The Morning Hours", 11, "Meccan"),
        Surah(94, "Ash-Sharh", "الشرح", "The Relief", 8, "Meccan"),
        Surah(95, "At-Tin", "التين", "The Fig", 8, "Meccan"),
        Surah(96, "Al-'Alaq", "العلق", "The Clot", 19, "Meccan"),
        Surah(97, "Al-Qadr", "القدر", "The Power", 5, "Meccan"),
        Surah(98, "Al-Bayyinah", "البينة", "The Clear Proof", 8, "Medinan"),
        Surah(99, "Az-Zalzalah", "الزلزلة", "The Earthquake", 8, "Medinan"),
        Surah(100, "Al-'Adiyat", "العاديات", "The Courser", 11, "Meccan"),
        Surah(101, "Al-Qari'ah", "القارعة", "The Calamity", 11, "Meccan"),
        Surah(102, "At-Takathur", "التكاثر", "The Rivalry in World Increase", 8, "Meccan"),
        Surah(103, "Al-'Asr", "العصر", "The Declining Day", 3, "Meccan"),
        Surah(104, "Al-Humazah", "الهمزة", "The Traducer", 9, "Meccan"),
        Surah(105, "Al-Fil", "الفيل", "The Elephant", 5, "Meccan"),
        Surah(106, "Quraysh", "قريش", "Quraysh", 4, "Meccan"),
        Surah(107, "Al-Ma'un", "الماعون", "The Small Kindnesses", 7, "Meccan"),
        Surah(108, "Al-Kawthar", "الكوثر", "The Abundance", 3, "Meccan"),
        Surah(109, "Al-Kafirun", "الكافرون", "The Disbelievers", 6, "Meccan"),
        Surah(110, "An-Nasr", "النصر", "The Divine Support", 3, "Medinan"),
        Surah(111, "Al-Masad", "المسد", "The Palm Fiber", 5, "Meccan"),
        Surah(112, "Al-Ikhlas", "الإخلاص", "The Sincerity", 4, "Meccan"),
        Surah(113, "Al-Falaq", "الفلق", "The Daybreak", 5, "Meccan"),
        Surah(114, "An-Nas", "الناس", "Mankind", 6, "Meccan")
    )

    // Complete 30 Juz
    val allJuz: List<Juz> = listOf(
        Juz(1, "الم", "Alif Lam Meem", 1, "Al-Fatihah", 1, 2, "Al-Baqarah", 141),
        Juz(2, "سَيَقُولُ", "Sayaqool", 2, "Al-Baqarah", 142, 2, "Al-Baqarah", 252),
        Juz(3, "تِلْكَ الرُّسُلُ", "Tilka-r-Rusul", 2, "Al-Baqarah", 253, 3, "Ali 'Imran", 92),
        Juz(4, "لَنْ تَنَالُوا", "Lan Tanaaloo", 3, "Ali 'Imran", 93, 4, "An-Nisa", 23),
        Juz(5, "وَالْمُحْصَنَاتُ", "Wal Muhsanat", 4, "An-Nisa", 24, 4, "An-Nisa", 147),
        Juz(6, "لَا يُحِبُّ اللَّهُ", "La Yuhibbullah", 4, "An-Nisa", 148, 5, "Al-Ma'idah", 81),
        Juz(7, "وَإِذَا سَمِعُوا", "Wa Iza Sami'oo", 5, "Al-Ma'idah", 82, 6, "Al-An'am", 110),
        Juz(8, "وَلَوْ أَنَّنَا", "Wa Law Annana", 6, "Al-An'am", 111, 7, "Al-A'raf", 87),
        Juz(9, "قَالَ الْمَلَأُ", "Qalal Malao", 7, "Al-A'raf", 88, 8, "Al-Anfal", 40),
        Juz(10, "وَاعْلَمُوا", "Wa A'lamoo", 8, "Al-Anfal", 41, 9, "At-Tawbah", 92),
        Juz(11, "يَعْتَذِرُونَ", "Ya'taziroon", 9, "At-Tawbah", 93, 11, "Hud", 5),
        Juz(12, "وَمَا مِنْ دَابَّةٍ", "Wa Mamin Da'abba", 11, "Hud", 6, 12, "Yusuf", 52),
        Juz(13, "وَمَا أُبَرِّئُ", "Wa Ma Ubarri'u", 12, "Yusuf", 53, 14, "Ibrahim", 52),
        Juz(14, "رُبَمَا", "Rubama", 15, "Al-Hijr", 1, 16, "An-Nahl", 128),
        Juz(15, "سُبْحَانَ الَّذِي", "Subhanallazi", 17, "Al-Isra", 1, 18, "Al-Kahf", 74),
        Juz(16, "قَالَ أَلَمْ", "Qala Alam", 18, "Al-Kahf", 75, 20, "Taha", 135),
        Juz(17, "اقْتَرَبَ لِلنَّاسِ", "Iqtaraba Lin Nasi", 21, "Al-Anbiya", 1, 22, "Al-Hajj", 78),
        Juz(18, "قَدْ أَفْلَحَ", "Qadd Aflaha", 23, "Al-Mu'minun", 1, 25, "Al-Furqan", 20),
        Juz(19, "وَقَالَ الَّذِينَ", "Wa Qalal Lazina", 25, "Al-Furqan", 21, 27, "An-Naml", 55),
        Juz(20, "أَمَّنْ خَلَقَ", "Amman Khalaqa", 27, "An-Naml", 56, 29, "Al-'Ankabut", 45),
        Juz(21, "اتْلُ مَا أُوحِيَ", "Utlu Ma Oohiya", 29, "Al-'Ankabut", 46, 33, "Al-Ahzab", 30),
        Juz(22, "وَمَنْ يَقْنُتْ", "Wa Manyaqnut", 33, "Al-Ahzab", 31, 36, "Ya-Sin", 27),
        Juz(23, "وَمَا لِيَ", "Wa Maliya", 36, "Ya-Sin", 28, 39, "Az-Zumar", 31),
        Juz(24, "فَمَنْ أَظْلَمُ", "Faman Azlamu", 39, "Az-Zumar", 32, 41, "Fussilat", 46),
        Juz(25, "إِلَيْهِ يُرَدُّ", "Ilaihi Yuraddu", 41, "Fussilat", 47, 45, "Al-Jathiyah", 37),
        Juz(26, "حم", "Ha-Meem", 46, "Al-Ahqaf", 1, 51, "Adh-Dhariyat", 30),
        Juz(27, "قَالَ فَمَا خَطْبُكُمْ", "Qala Fama Khatbukum", 51, "Adh-Dhariyat", 31, 57, "Al-Hadid", 29),
        Juz(28, "قَدْ سَمِعَ اللَّهُ", "Qadd Sami' Allahu", 58, "Al-Mujadila", 1, 66, "At-Tahrim", 12),
        Juz(29, "تَبَارَكَ الَّذِي", "Tabarakallazi", 67, "Al-Mulk", 1, 77, "Al-Mursalat", 50),
        Juz(30, "عَمَّ يَتَسَاءَلُونَ", "'Amma Yatasa'aloon", 78, "An-Naba", 1, 114, "An-Nas", 6)
    )

    // Complete Ayahs for key and everyday Surahs
    private val surahVersesMap: Map<Int, List<Ayah>> = mapOf(
        1 to listOf(
            Ayah(1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "In the name of Allah, the Entirely Merciful, the Especially Merciful.", "Bismillaahir Rahmaanir Raheem", "https://everyayah.com/data/Alafasy_128kbps/001001.mp3", 1),
            Ayah(2, 2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "[All] praise is [due] to Allah, Lord of the worlds -", "Alhamdu lillaahi Rabbil 'aalameen", "https://everyayah.com/data/Alafasy_128kbps/001002.mp3", 1),
            Ayah(3, 3, "الرَّحْمَٰنِ الرَّحِيمِ", "The Entirely Merciful, the Especially Merciful,", "Ar-Rahmaanir-Raheem", "https://everyayah.com/data/Alafasy_128kbps/001003.mp3", 1),
            Ayah(4, 4, "مَالِكِ يَوْمِ الدِّينِ", "Sovereign of the Day of Recompense.", "Maaliki Yawmid-Deen", "https://everyayah.com/data/Alafasy_128kbps/001004.mp3", 1),
            Ayah(5, 5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "It is You we worship and You we ask for help.", "Iyyaaka na'budu wa lyyaaka nasta'een", "https://everyayah.com/data/Alafasy_128kbps/001005.mp3", 1),
            Ayah(6, 6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Guide us to the straight path -", "Ihdinas-Siraatal-Mustaqeem", "https://everyayah.com/data/Alafasy_128kbps/001006.mp3", 1),
            Ayah(7, 7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "The path of those upon whom You have bestowed favor, not of those who have evoked [Your] anger or of those who are astray.", "Siraatal-lazeena an'amta 'alaihim ghayril-maghdoobi 'alaihim wa lad-daaalleen", "https://everyayah.com/data/Alafasy_128kbps/001007.mp3", 1)
        ),
        112 to listOf(
            Ayah(1, 6222, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Say, \"He is Allah, [who is] One,", "Qul Huwallahu Ahad", "https://everyayah.com/data/Alafasy_128kbps/112001.mp3", 30),
            Ayah(2, 6223, "اللَّهُ الصَّمَدُ", "Allah, the Eternal Refuge.", "Allahus-Samad", "https://everyayah.com/data/Alafasy_128kbps/112002.mp3", 30),
            Ayah(3, 6224, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "He neither begets nor is born,", "Lam yalid wa lam yoolad", "https://everyayah.com/data/Alafasy_128kbps/112003.mp3", 30),
            Ayah(4, 6225, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "Nor is there to Him any equivalent.\"", "Wa lam yakul-lahu kufuwan ahad", "https://everyayah.com/data/Alafasy_128kbps/112004.mp3", 30)
        ),
        113 to listOf(
            Ayah(1, 6226, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Say, \"I seek refuge in the Lord of daybreak", "Qul a'oozu bi rabbil-falaq", "https://everyayah.com/data/Alafasy_128kbps/113001.mp3", 30),
            Ayah(2, 6227, "مِن شَرِّ مَا خَلَقَ", "From the evil of that which He created", "Min sharri ma khalaq", "https://everyayah.com/data/Alafasy_128kbps/113002.mp3", 30),
            Ayah(3, 6228, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "And from the evil of darkness when it settles", "Wa min sharri ghasiqin iza waqab", "https://everyayah.com/data/Alafasy_128kbps/113003.mp3", 30),
            Ayah(4, 6229, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "And from the evil of the blowers in knots", "Wa min sharrin-naffaa-saati fil 'uqad", "https://everyayah.com/data/Alafasy_128kbps/113004.mp3", 30),
            Ayah(5, 6230, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "And from the evil of an envier when he envies.\"", "Wa min sharri haasidin iza hasad", "https://everyayah.com/data/Alafasy_128kbps/113005.mp3", 30)
        ),
        114 to listOf(
            Ayah(1, 6231, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Say, \"I seek refuge in the Lord of mankind,", "Qul a'oozu bi rabbin-naas", "https://everyayah.com/data/Alafasy_128kbps/114001.mp3", 30),
            Ayah(2, 6232, "مَلِكِ النَّاسِ", "The Sovereign of mankind,", "Malikin-naas", "https://everyayah.com/data/Alafasy_128kbps/114002.mp3", 30),
            Ayah(3, 6233, "إِلَٰهِ النَّاسِ", "The God of mankind,", "Ilaahin-naas", "https://everyayah.com/data/Alafasy_128kbps/114003.mp3", 30),
            Ayah(4, 6234, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "From the evil of the retreating whisperer -", "Min sharril-waswaasil-khannaas", "https://everyayah.com/data/Alafasy_128kbps/114004.mp3", 30),
            Ayah(5, 6235, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Who whispers into the breasts of mankind -", "Allazee yuwaswisu fee sudoorin-naas", "https://everyayah.com/data/Alafasy_128kbps/114005.mp3", 30),
            Ayah(6, 6236, "مِنَ الْجِنَّةِ وَالنَّاسِ", "From among the jinn and mankind.\"", "Minal-jinnati wan-naas", "https://everyayah.com/data/Alafasy_128kbps/114006.mp3", 30)
        ),
        103 to listOf(
            Ayah(1, 6177, "وَالْعَصْرِ", "By time,", "Wal-'Asr", "https://everyayah.com/data/Alafasy_128kbps/103001.mp3", 30),
            Ayah(2, 6178, "إِنَّ الْإِنسَانَ لَفِي خُسْرٍ", "Indeed, mankind is in loss,", "Innal insaana lafee khusr", "https://everyayah.com/data/Alafasy_128kbps/103002.mp3", 30),
            Ayah(3, 6179, "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "Except for those who have believed and done righteous deeds and advised each other to truth and advised each other to patience.", "Illal-lazeena aamanoo wa 'amilus-saalihaati wa tawaasaw bilhaqqi wa tawaasaw bissabr", "https://everyayah.com/data/Alafasy_128kbps/103003.mp3", 30)
        ),
        108 to listOf(
            Ayah(1, 6205, "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ", "Indeed, We have granted you, [O Muhammad], al-Kawthar.", "Innaaa a'tainaakal-kawthar", "https://everyayah.com/data/Alafasy_128kbps/108001.mp3", 30),
            Ayah(2, 6206, "فَصَلِّ لِرَبِّكَ وَانْحَرْ", "So pray to your Lord and sacrifice [to Him alone].", "Fa salli li rabbika wanhar", "https://everyayah.com/data/Alafasy_128kbps/108002.mp3", 30),
            Ayah(3, 6207, "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ", "Indeed, your enemy is the one cut off.", "Inna shaani'aka huwal abtar", "https://everyayah.com/data/Alafasy_128kbps/108003.mp3", 30)
        ),
        110 to listOf(
            Ayah(1, 6214, "إِذَا جَاءَ نَصْرُ اللَّهِ وَالْفَتْحُ", "When the victory of Allah has come and the conquest,", "Iza jaaa-a nasrul-laahi wal fath", "https://everyayah.com/data/Alafasy_128kbps/110001.mp3", 30),
            Ayah(2, 6215, "وَرَأَيْتَ النَّاسَ يَدْخُلُونَ فِي دِينِ اللَّهِ أَفْوَاجًا", "And you see the people entering into the religion of Allah in multitudes,", "Wa ra-aitan naasa yadkhuloona fee deenil laahi afwajaa", "https://everyayah.com/data/Alafasy_128kbps/110002.mp3", 30),
            Ayah(3, 6216, "فَسَبِّحْ بِحَمْدِ رَبِّكَ وَاسْتَغْفِرْهُ ۚ إِنَّهُ كَانَ تَوَّابًا", "Then exalt [Him] with praise of your Lord and ask forgiveness of Him. Indeed, He is ever Accepting of repentance.", "Fa sabbih bihamdi rabbika wastaghfirh; innahoo kaana tawwaaba", "https://everyayah.com/data/Alafasy_128kbps/110003.mp3", 30)
        ),
        67 to listOf(
            Ayah(1, 5242, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "Blessed is He in whose hand is dominion, and He is over all things competent -", "Tabaarakal lazee biyadihil mulku wa huwa 'alaa kulli shai-in qadeer", "https://everyayah.com/data/Alafasy_128kbps/067001.mp3", 29),
            Ayah(2, 5243, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا ۚ وَهُوَ الْعَزِيزُ الْغَفُورُ", "[He] who created death and life to test you [as to] which of you is best in deed - and He is the Exalted in Might, the Forgiving -", "Allazee khalaqal mawta walhayaata liyabluwakum ayyukum ahsanu 'amalaa; wa huwal 'azeezul ghafoor", "https://everyayah.com/data/Alafasy_128kbps/067002.mp3", 29),
            Ayah(3, 5244, "الَّذِي خَلَقَ سَبْعَ سَمَاوَاتٍ طِبَاقًا ۖ مَّا تَرَىٰ فِي خَلْقِ الرَّحْمَٰنِ مِن تَفَاوُتٍ ۖ فَارْجِعِ الْبَصَرَ هَلْ تَرَىٰ مِن فُطُورٍ", "[And] who created seven heavens in layers. You see no imperfection in the creation of the Most Merciful. So return [your] vision; do you see any breaks?", "Allazee khalaqa sab'a samaawaatin tibaaqam maa taraa fee khalqir rahmaani min tafaawutin farji'il basara hal taraa min futoor", "https://everyayah.com/data/Alafasy_128kbps/067003.mp3", 29),
            Ayah(4, 5245, "ثُمَّ ارْجِعِ الْبَصَرَ كَرَّتَيْنِ يَنقَلِبْ إِلَيْكَ الْبَصَرُ خَاسِئًا وَهُوَ حَسِيرٌ", "Then return [your] vision twice again. [Your] vision will return to you humbled while it is fatigued.", "Summar ji'il basara karrataini yanqalib ilaikal basaru khaasi'anw wa huwa haseer", "https://everyayah.com/data/Alafasy_128kbps/067004.mp3", 29),
            Ayah(5, 5246, "وَلَقَدْ زَيَّنَّا السَّمَاءَ الدُّنْيَا بِمَصَابِيحَ وَجَعَلْنَاهَا رُجُومًا لِّلشَّيَاطِينِ ۖ وَأَعْتَدْنَا لَهُمْ عَذَابَ السَّعِيرِ", "And We have certainly beautified the nearest heaven with stars and have made [from] them what is thrown at the devils and have prepared for them the punishment of the Blaze.", "Wa laqad zayyannas samaaa'ad dunyaa bimasaabeeha wa ja'alnaahaa rujoomal lish shayaateeni wa a'tadnaa lahum 'azaabas sa'eer", "https://everyayah.com/data/Alafasy_128kbps/067005.mp3", 29)
        ),
        97 to listOf(
            Ayah(1, 6126, "إِنَّا أَنزَلْنَاهُ فِي لَيْلَةِ الْقَدْرِ", "Indeed, We sent the Qur'an down during the Night of Decree.", "Innaaa anzalnaahu fee lailatil qadr", "https://everyayah.com/data/Alafasy_128kbps/097001.mp3", 30),
            Ayah(2, 6127, "وَمَا أَدْرَاكَ مَا لَيْلَةُ الْقَدْرِ", "And what can make you know what is the Night of Decree?", "Wa maaa adraaka maa lailatul qadr", "https://everyayah.com/data/Alafasy_128kbps/097002.mp3", 30),
            Ayah(3, 6128, "لَيْلَةُ الْقَدْرِ خَيْرٌ مِّنْ أَلْفِ شَهْرٍ", "The Night of Decree is better than a thousand months.", "Lailatul qadri khairum min alfee shahr", "https://everyayah.com/data/Alafasy_128kbps/097003.mp3", 30),
            Ayah(4, 6129, "تَنَزَّلُ الْمَلَائِكَةُ وَالرُّوحُ فِيهَا بِإِذْنِ رَبِّهِم مِّن كُلِّ أَمْرٍ", "The angels and the Spirit descend therein by permission of their Lord for every matter.", "Tanazzalul malaaa'ikatu war roohu feeha bi izni rabbihim min kulli amr", "https://everyayah.com/data/Alafasy_128kbps/097004.mp3", 30),
            Ayah(5, 6130, "سَلَامٌ هِيَ حَتَّىٰ مَطْلَعِ الْفَجْرِ", "Peace it is until the emergence of dawn.", "Salaamun hiya hattaa matla'il fajr", "https://everyayah.com/data/Alafasy_128kbps/097005.mp3", 30)
        )
    )

    fun getVersesForSurah(surahNumber: Int): List<Ayah> {
        val cached = surahVersesMap[surahNumber]
        if (cached != null) return cached

        val surah = allSurahs.firstOrNull { it.number == surahNumber } ?: allSurahs[0]
        val formattedSurahNum = String.format("%03d", surahNumber)

        // For surahs where full text is fetched via network API, generate clean placeholder sequence
        return (1..surah.numberOfAyahs).map { ayahNum ->
            val formattedAyahNum = String.format("%03d", ayahNum)
            Ayah(
                numberInSurah = ayahNum,
                numberInQuran = 0,
                textArabic = if (ayahNum == 1 && surahNumber != 9) "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ" else "آيَةٌ كَرِيمَةٌ مِنْ سُورَةِ ${surah.nameArabic}",
                translationEnglish = "Surah ${surah.nameEnglish}, Verse $ayahNum: In the name of Allah, the Entirely Merciful, the Especially Merciful.",
                transliteration = "Ayah $ayahNum of Surah ${surah.nameEnglish}",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$formattedSurahNum$formattedAyahNum.mp3",
                juzNumber = 1
            )
        }
    }

    // Daily Verse of the Day
    val dailyVerse: Ayah = Ayah(
        numberInSurah = 28,
        numberInQuran = 1735,
        textArabic = "الَّذِينَ آمَنُوا وَتَطْمَئِنُّ قُلُوبُهُم بِذِكْرِ اللَّهِ ۗ أَلَا بِذِكْرِ اللَّهِ تَطْمَئِنُّ الْقُلُوبُ",
        translationEnglish = "Those who have believed and whose hearts are assured by the remembrance of Allah. Unquestionably, by the remembrance of Allah hearts are assured.",
        transliteration = "Allazeena aamanoo wa tatma'innu quloobuhum bizikril laah; alaa bizikril laahi tatma'innul quloob",
        audioUrl = "https://everyayah.com/data/Alafasy_128kbps/013028.mp3",
        juzNumber = 13
    )
}
