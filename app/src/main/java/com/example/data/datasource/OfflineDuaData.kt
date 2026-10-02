package com.example.data.datasource

import com.example.data.model.Dua

object OfflineDuaData {

    val categories = listOf("All", "Morning & Evening", "Prayer", "Protection", "Travel", "Forgiveness", "Family")

    val duas: List<Dua> = listOf(
        Dua(
            id = "dua_waking_up",
            category = "Morning & Evening",
            title = "Upon Waking Up",
            arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            transliteration = "Alhamdu lillaahil-lazee ahyaanaa ba'da maa amaatanaa wa ilayhin-nushoor",
            translation = "All praise is for Allah who gave us life after causing us to die, and unto Him is the resurrection.",
            reference = "Sahih al-Bukhari 6312, Muslim 2711"
        ),
        Dua(
            id = "dua_sleeping",
            category = "Morning & Evening",
            title = "Before Going to Sleep",
            arabicText = "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
            transliteration = "Bismikallahumma amootu wa ahyaa",
            translation = "In Your name O Allah, I live and I die.",
            reference = "Sahih al-Bukhari 6324"
        ),
        Dua(
            id = "dua_sayyidul_istighfar",
            category = "Forgiveness",
            title = "Master Supplication for Forgiveness (Sayyidul Istighfar)",
            arabicText = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَهَ إِلَّا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ لَكَ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
            transliteration = "Allaahumma Anta Rabbee laa ilaaha illaa Anta, khalaqtanee wa ana 'abduka, wa ana 'alaa 'ahdika wa wa'dika mastata'tu, a'oozu bika min sharri maa sana'tu, aboo-u laka bini'matika 'alayya, wa aboo-u bizambee faghfir lee fa-innahoo laa yaghfiruz-zunooba illaa Anta",
            translation = "O Allah, You are my Lord, there is no god but You. You have created me and I am Your slave. I abide by Your covenant and promise as best I can. I seek refuge in You from the evil of what I have done. I acknowledge Your favor upon me and I acknowledge my sin, so forgive me, for none forgives sins except You.",
            reference = "Sahih al-Bukhari 6306"
        ),
        Dua(
            id = "dua_protection",
            category = "Protection",
            title = "Protection from Harm (Morning & Evening)",
            arabicText = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            transliteration = "Bismillaahil-lazee laa yadurru ma'as-mihee shay-un fil-ardi wa laa fis-samaa-i wa Huwas-Samee'ul-'Aleem",
            translation = "In the Name of Allah, with whose Name nothing on earth or in the heaven can cause harm, and He is the All-Hearing, the All-Knowing. (Recite 3 times)",
            reference = "Sunan Abi Dawud 5088, Jami` at-Tirmidhi 3388"
        ),
        Dua(
            id = "dua_travel",
            category = "Travel",
            title = "Dua for Travelling",
            arabicText = "سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَٰذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ وَإِنَّا إِلَىٰ رَبِّنَا لَمُنقَلِبُونَ",
            transliteration = "Subhaanal-lazee sakh-khara lanaa haazaa wa maa kunnaa lahoo muqrineen, wa innaaa ilaa Rabbinaa lamunqaliboon",
            translation = "Glory to Him who has subjected this to us, and we could never have it by our efforts. And verily, to our Lord we indeed are to return.",
            reference = "Surah Az-Zukhruf 43:13-14, Sahih Muslim 1342"
        ),
        Dua(
            id = "dua_parents",
            category = "Family",
            title = "Dua for Parents",
            arabicText = "رَّبِّ ارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            transliteration = "Rabbir-hamhumaa kamaa rabbayaanee sagheeraa",
            translation = "My Lord, have mercy upon them as they brought me up [when I was] small.",
            reference = "Surah Al-Isra 17:24"
        ),
        Dua(
            id = "dua_family_peace",
            category = "Family",
            title = "Dua for Spouses and Children",
            arabicText = "رَبَّنَا هَبْ لَنَا مِنْ أَزْوَاجِنَا وَذُرِّيَّاتِنَا قُرَّةَ أَعْيُنٍ وَاجْعَلْنَا لِلْمُتَّقِينَ إِمَامًا",
            transliteration = "Rabbanaa hab lanaa min azwaajinaa wa zurriyyaatinaa qurrata a'yunin waj'alnaa lil-muttaqeena imaamaa",
            translation = "Our Lord, grant us from among our wives and offspring comfort to our eyes and make us an example for the righteous.",
            reference = "Surah Al-Furqan 25:74"
        ),
        Dua(
            id = "dua_entering_mosque",
            category = "Prayer",
            title = "Entering the Mosque",
            arabicText = "اللَّهُمَّ افْتَحْ لِي أَبْوَابَ رَحْمَتِكَ",
            transliteration = "Allaahummaf-tah lee abwaaba rahmatik",
            translation = "O Allah, open for me the doors of Your mercy.",
            reference = "Sahih Muslim 713"
        )
    )

    val dailyDua: Dua = duas[3] // Protection from harm
}
