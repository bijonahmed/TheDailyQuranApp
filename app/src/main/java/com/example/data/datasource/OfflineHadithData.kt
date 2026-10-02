package com.example.data.datasource

import com.example.data.model.Hadith

object OfflineHadithData {

    val hadiths: List<Hadith> = listOf(
        Hadith(
            id = "bukhari_1",
            collection = "Sahih al-Bukhari",
            hadithNumber = "1",
            narrator = "'Umar bin Al-Khattab (RA)",
            arabicText = "إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى، فَمَنْ كَانَتْ هِجْرَتُهُ إِلَى دُنْيَا يُصِيبُهَا أَوْ إِلَى امْرَأَةٍ يَنْكِحُهَا فَهِجْرَتُهُ إِلَى مَا هَاجَرَ إِلَيْهِ.",
            englishText = "The reward of deeds depends upon the intentions and every person will get the reward according to what he has intended. So whoever emigrated for worldly benefits or for a woman to marry, his emigration was for what he emigrated for.",
            grade = "Sahih (Authentic)",
            reference = "Sahih al-Bukhari 1, Book 1, Hadith 1"
        ),
        Hadith(
            id = "bukhari_13",
            collection = "Sahih al-Bukhari",
            hadithNumber = "13",
            narrator = "Anas (RA)",
            arabicText = "لاَ يُؤْمِنُ أَحَدُكُمْ حَتَّى يُحِبَّ لأَخِيهِ مَا يُحِبُّ لِنَفْسِهِ.",
            englishText = "None of you will believe until you love for your brother what you love for yourself.",
            grade = "Sahih (Authentic)",
            reference = "Sahih al-Bukhari 13, Book 2, Hadith 6"
        ),
        Hadith(
            id = "muslim_223",
            collection = "Sahih Muslim",
            hadithNumber = "223",
            narrator = "Abu Malik Al-Ash'ari (RA)",
            arabicText = "الطُّهُورُ شَطْرُ الإِيمَانِ، وَالْحَمْدُ لِلَّهِ تَمْلأُ الْمِيزَانَ، وَسُبْحَانَ اللَّهِ وَالْحَمْدُ لِلَّهِ تَمْلآنِ أَوْ تَمْلأُ مَا بَيْنَ السَّمَاوَاتِ وَالأَرْضِ.",
            englishText = "Cleanliness is half of faith and Alhamdulillah (Praise be to Allah) fills the scale, and SubhanAllah and Alhamdulillah fill what is between the heavens and the earth.",
            grade = "Sahih (Authentic)",
            reference = "Sahih Muslim 223, Book 2, Hadith 1"
        ),
        Hadith(
            id = "bukhari_5027",
            collection = "Sahih al-Bukhari",
            hadithNumber = "5027",
            narrator = "'Uthman bin 'Affan (RA)",
            arabicText = "خَيْرُكُمْ مَنْ تَعَلَّمَ الْقُرْآنَ وَعَلَّمَهُ.",
            englishText = "The best among you (Muslims) are those who learn the Qur'an and teach it.",
            grade = "Sahih (Authentic)",
            reference = "Sahih al-Bukhari 5027, Book 66, Hadith 49"
        ),
        Hadith(
            id = "nawawi_18",
            collection = "40 Hadith Nawawi",
            hadithNumber = "18",
            narrator = "Abu Dharr & Mu'adh (RA)",
            arabicText = "اتَّقِ اللَّهَ حَيْثُمَا كُنْتَ، وَأَتْبِعِ السَّيِّئَةَ الْحَسَنَةَ تَمْحُهَا، وَخَالِقِ النَّاسَ بِخُلُقٍ حَسَنٍ.",
            englishText = "Fear Allah wherever you may be; follow up a bad deed with a good deed and it will wipe it out; and behave well towards the people.",
            grade = "Hasan (Good)",
            reference = "Jami` at-Tirmidhi 1987, 40 Hadith Nawawi 18"
        ),
        Hadith(
            id = "bukhari_6011",
            collection = "Sahih al-Bukhari",
            hadithNumber = "6011",
            narrator = "Abu Hurairah (RA)",
            arabicText = "مَنْ كَانَ يُؤْمِنُ بِاللَّهِ وَالْيَوْمِ الآخِرِ فَلْيَقُلْ خَيْرًا أَوْ لِيَصْمُتْ.",
            englishText = "Whoever believes in Allah and the Last Day should speak good or remain silent.",
            grade = "Sahih (Authentic)",
            reference = "Sahih al-Bukhari 6018, Book 78, Hadith 48"
        ),
        Hadith(
            id = "muslim_2699",
            collection = "Sahih Muslim",
            hadithNumber = "2699",
            narrator = "Abu Hurairah (RA)",
            arabicText = "مَنْ سَلَكَ طَرِيقًا يَلْتَمِسُ فِيهِ عِلْمًا سَهَّلَ اللَّهُ لَهُ بِهِ طَرِيقًا إِلَى الْجَنَّةِ.",
            englishText = "Whoever follows a path in the pursuit of knowledge, Allah will make a path to Paradise easy for him.",
            grade = "Sahih (Authentic)",
            reference = "Sahih Muslim 2699, Book 48, Hadith 42"
        )
    )

    val dailyHadith: Hadith = hadiths[3] // "The best among you are those who learn the Quran and teach it"
}
