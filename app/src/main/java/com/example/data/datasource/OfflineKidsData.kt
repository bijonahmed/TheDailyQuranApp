package com.example.data.datasource

import com.example.data.model.KidsLetter
import com.example.data.model.KidsQuiz

object OfflineKidsData {

    val arabicAlphabet: List<KidsLetter> = listOf(
        KidsLetter("أ", "أَلِف", "Alif", "A", "أَسَد", "Asad (Lion)"),
        KidsLetter("ب", "بَاء", "Baa", "B", "بَاب", "Baab (Door)"),
        KidsLetter("ت", "تَاء", "Taa", "T", "تَمْر", "Tamr (Dates)"),
        KidsLetter("ث", "ثَاء", "Thaa", "Th", "ثَوْب", "Thawb (Robe)"),
        KidsLetter("ج", "جِيم", "Jeem", "J", "جَمَل", "Jamal (Camel)"),
        KidsLetter("ح", "حَاء", "Haa", "H", "حَدِيقَة", "Hadeeqah (Garden)"),
        KidsLetter("خ", "خَاء", "Khaa", "Kh", "خُبْز", "Khubz (Bread)"),
        KidsLetter("د", "دَال", "Daal", "D", "دُعَاء", "Dua (Supplication)"),
        KidsLetter("ذ", "ذَال", "Zhaal", "Dh", "ذَهَب", "Zhahab (Gold)"),
        KidsLetter("ر", "رَاء", "Raa", "R", "رَحْمَة", "Rahmah (Mercy)"),
        KidsLetter("ز", "زَاي", "Zay", "Z", "زَيْتُون", "Zaytoon (Olive)"),
        KidsLetter("س", "سِين", "Seen", "S", "سَلَام", "Salaam (Peace)"),
        KidsLetter("ش", "شِين", "Sheen", "Sh", "شَمْس", "Shams (Sun)"),
        KidsLetter("ص", "صَاد", "Saad", "S", "صَلَاة", "Salah (Prayer)"),
        KidsLetter("ض", "ضَاد", "Daad", "D", "ضَوْء", "Daw' (Light)"),
        KidsLetter("ط", "طَاء", "Taa", "T", "طَيْر", "Tayr (Bird)"),
        KidsLetter("ظ", "ظَاء", "Zhaa", "Zh", "ظِلّ", "Zhill (Shade)"),
        KidsLetter("ع", "عَيْن", "'Ayn", "'A", "عِلْم", "'Ilm (Knowledge)"),
        KidsLetter("غ", "غَيْن", "Ghayn", "Gh", "غَيْمَة", "Ghaymah (Cloud)"),
        KidsLetter("ف", "فَاء", "Faa", "F", "فَجْر", "Fajr (Dawn)"),
        KidsLetter("ق", "قَاف", "Qaaf", "Q", "قُرْآن", "Quran (The Holy Book)"),
        KidsLetter("ك", "كَاف", "Kaaf", "K", "كِتَاب", "Kitaab (Book)"),
        KidsLetter("ل", "لَام", "Laam", "L", "لَيْل", "Layl (Night)"),
        KidsLetter("م", "مِيم", "Meem", "M", "مَسْجِد", "Masjid (Mosque)"),
        KidsLetter("ن", "نُون", "Noon", "N", "نُور", "Noor (Light)"),
        KidsLetter("هـ", "هَاء", "Haa", "H", "هِلَال", "Hilaal (Crescent)"),
        KidsLetter("و", "وَاو", "Waaw", "W", "وُضُوء", "Wudu (Ablution)"),
        KidsLetter("ي", "يَاء", "Yaa", "Y", "يَوْم", "Yawm (Day)")
    )

    val quizzes: List<KidsQuiz> = listOf(
        KidsQuiz(
            id = 1,
            question = "How many Surahs are there in the Holy Quran?",
            options = listOf("100", "114", "120", "30"),
            correctIndex = 1,
            explanation = "The Holy Quran contains exactly 114 Surahs."
        ),
        KidsQuiz(
            id = 2,
            question = "What is the first word revealed to Prophet Muhammad (PBUH)?",
            options = listOf("Iqra (Read)", "Qul (Say)", "Bismillah", "Alhamdulillah"),
            correctIndex = 0,
            explanation = "The first revelation began with 'Iqra' (Read) in Surah Al-'Alaq."
        ),
        KidsQuiz(
            id = 3,
            question = "Which Surah is known as the Heart of the Quran?",
            options = listOf("Surah Al-Fatihah", "Surah Al-Baqarah", "Surah Ya-Sin", "Surah Al-Mulk"),
            correctIndex = 2,
            explanation = "Surah Ya-Sin is known as the heart of the Quran."
        ),
        KidsQuiz(
            id = 4,
            question = "How many daily obligatory prayers (Salah) are there in Islam?",
            options = listOf("3", "4", "5", "7"),
            correctIndex = 2,
            explanation = "Muslims perform 5 daily obligatory prayers: Fajr, Dhuhr, Asr, Maghrib, and Isha."
        ),
        KidsQuiz(
            id = 5,
            question = "In which blessed month was the Holy Quran revealed?",
            options = listOf("Rajab", "Sha'ban", "Ramadan", "Muharram"),
            correctIndex = 2,
            explanation = "The Quran was revealed during the blessed month of Ramadan."
        )
    )
}
