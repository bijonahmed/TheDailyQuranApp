package com.example.data.datasource

import com.example.data.model.Article

object OfflineBlogData {

    val articles: List<Article> = listOf(
        Article(
            id = "art_1",
            title = "The Miraculous Preservation of the Holy Quran",
            category = "Quranic Studies",
            excerpt = "How the Quran has been preserved letter by letter from revelation until today through oral recitation and written codices.",
            content = """The Holy Quran is the literal Word of Allah revealed to Prophet Muhammad (peace be upon him) over a period of 23 years. Unlike previous scriptures which suffered from human alterations, Allah Himself took the responsibility to preserve the Quran:

"Indeed, it is We who sent down the Qur'an and indeed, We will be its guardian." (Surah Al-Hijr 15:9)

From the earliest moments in Mecca and Medina, every verse revealed was immediately memorized by dozens of companions (Sahabah) and transcribed onto parchment, stone tablets, and leather leaves under the direct supervision of the Prophet.

During the caliphate of Abu Bakr As-Siddiq, and later standardized by Uthman ibn Affan, the Quran was compiled into master mushafs with precise orthography that remains identical in every copy in the world today.""",
            url = "https://thedailyquran.com/blog/",
            readTimeMinutes = 4
        ),
        Article(
            id = "art_2",
            title = "Virtues of Reciting Surah Al-Kahf on Fridays",
            category = "Daily Sunnah",
            excerpt = "Discover the immense spiritual blessings and protection against trials promised to whoever recites Surah Al-Kahf.",
            content = """The Messenger of Allah (peace be upon him) said:

"Whoever reads Surah al-Kahf on the day of Jumu'ah, will have a light that will shine from him from one Friday to the next." (Narrated by al-Hakim, Sahih)

Surah Al-Kahf addresses four major trials that humanity faces:
1. Trial of Faith (The Companions of the Cave)
2. Trial of Wealth (The Owner of the Two Gardens)
3. Trial of Knowledge (Musa and Al-Khidr)
4. Trial of Power (Dhul-Qarnayn)

By reflecting upon these stories every Friday, believers are shielded from the greatest deceptions of the worldly life.""",
            url = "https://thedailyquran.com/blog/",
            readTimeMinutes = 3
        ),
        Article(
            id = "art_3",
            title = "The Etiquette and Spiritual Secrets of Dua",
            category = "Spiritual Growth",
            excerpt = "Learn how to approach Allah with humility, praise, sincerity, and conviction to have your supplications answered.",
            content = """Dua is the essence of worship. The Prophet (peace be upon him) said: "Supplication is the essence of worship." (Jami` at-Tirmidhi)

Key etiquettes of making Dua include:
1. Beginning with the praise of Allah and sending blessings upon the Prophet (PBUH).
2. Facing the Qibla and raising hands with humility.
3. Having absolute conviction that Allah hears and will answer in the best way.
4. Supplicating during blessed times, such as the last third of the night, between the Adhan and Iqamah, during prostration (Sujud), and while traveling.""",
            url = "https://thedailyquran.com/blog/",
            readTimeMinutes = 4
        )
    )

    val books: List<Article> = listOf(
        Article(
            id = "book_1",
            title = "Tafsir Ibn Kathir (Abridged)",
            category = "Exegesis",
            excerpt = "The most celebrated and authentic classical commentary on the Holy Quran, explaining verses through other verses and authentic Hadith.",
            content = "Compiled by Imam Ismail Ibn Kathir (d. 774 AH), this monumental work provides profound insights into Quranic verses through rigorous Hadith scholarship and the understandings of the Sahabah.",
            url = "https://thedailyquran.com/books/",
            readTimeMinutes = 6
        ),
        Article(
            id = "book_2",
            title = "Riyad as-Salihin (Gardens of the Righteous)",
            category = "Hadith",
            excerpt = "Imam an-Nawawi's timeless compilation of authentic Hadiths covering ethics, spirituality, worship, and manners.",
            content = "One of the most widely read books in the Islamic world, gathering verses from the Quran alongside relevant Sahih Hadith to guide believers in their daily walk with Allah.",
            url = "https://thedailyquran.com/books/",
            readTimeMinutes = 5
        ),
        Article(
            id = "book_3",
            title = "The Sealed Nectar (Ar-Raheeq Al-Makhtum)",
            category = "Seerah",
            excerpt = "An award-winning, authentic biography of the Prophet Muhammad (PBUH) by Sheikh Safiur-Rahman Mubarakpuri.",
            content = "A comprehensive and gripping chronological journey through the life of the Messenger of Allah, depicting his noble character, trials, victories, and enduring legacy.",
            url = "https://thedailyquran.com/books/",
            readTimeMinutes = 7
        )
    )
}
