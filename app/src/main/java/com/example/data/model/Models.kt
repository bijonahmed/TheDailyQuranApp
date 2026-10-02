package com.example.data.model

data class Surah(
    val number: Int,
    val nameEnglish: String,
    val nameArabic: String,
    val englishTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String // "Meccan" or "Medinan"
)

data class Ayah(
    val numberInSurah: Int,
    val numberInQuran: Int = 0,
    val textArabic: String,
    val translationEnglish: String,
    val transliteration: String = "",
    val audioUrl: String = "",
    val juzNumber: Int = 1
)

data class Juz(
    val number: Int,
    val nameArabic: String,
    val nameEnglish: String,
    val startSurahNumber: Int,
    val startSurahName: String,
    val startAyah: Int,
    val endSurahNumber: Int,
    val endSurahName: String,
    val endAyah: Int
)

data class Hadith(
    val id: String,
    val collection: String, // "Sahih al-Bukhari", "Sahih Muslim", "40 Hadith Nawawi"
    val hadithNumber: String,
    val narrator: String,
    val arabicText: String,
    val englishText: String,
    val grade: String,
    val reference: String
)

data class Dua(
    val id: String,
    val category: String, // "Morning & Evening", "Prayer", "Protection", "Travel", "Forgiveness", "Family"
    val title: String,
    val arabicText: String,
    val transliteration: String,
    val translation: String,
    val reference: String,
    val occasions: String = ""
)

data class IslamicName(
    val id: String,
    val nameEnglish: String,
    val nameArabic: String,
    val gender: String, // "Boy", "Girl", "Unisex"
    val meaning: String,
    val origin: String = "Arabic"
)

data class PrayerTimes(
    val fajr: String,
    val sunrise: String,
    val dhuhr: String,
    val asr: String,
    val maghrib: String,
    val isha: String,
    val nextPrayerName: String,
    val nextPrayerCountdown: String,
    val city: String = "Makkah (Estimated)",
    val calculationMethod: String = "Muslim World League"
)

data class IslamicDate(
    val hijriDay: Int,
    val hijriMonthName: String,
    val hijriYear: Int,
    val gregorianFormatted: String,
    val specialOccasion: String? = null
)

data class Prophet(
    val id: Int,
    val order: Int,
    val nameEnglish: String,
    val nameArabic: String,
    val title: String,
    val mentionedCount: Int,
    val description: String,
    val keySurahReference: String
)

data class KidsQuiz(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class KidsLetter(
    val letter: String,
    val nameArabic: String,
    val nameEnglish: String,
    val transliteration: String,
    val exampleWord: String,
    val exampleMeaning: String
)

data class Article(
    val id: String,
    val title: String,
    val category: String,
    val excerpt: String,
    val content: String,
    val url: String = "https://thedailyquran.com/blog/",
    val readTimeMinutes: Int = 3
)

data class Reciter(
    val id: String,
    val name: String,
    val subtext: String,
    val baseUrlAyah: String
)
