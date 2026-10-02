package com.example.data.repository

import com.example.data.datasource.OfflineQuranData
import com.example.data.local.BookmarkEntity
import com.example.data.local.FavoriteEntity
import com.example.data.local.LastReadEntity
import com.example.data.local.QuranDao
import com.example.data.model.Ayah
import com.example.data.model.Juz
import com.example.data.model.Surah
import com.example.data.remote.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class QuranRepository(
    private val quranDao: QuranDao
) {

    fun getSurahs(): Flow<List<Surah>> = flow {
        // Emit authentic offline surahs immediately
        emit(OfflineQuranData.allSurahs)

        // Try updating from remote API in background
        try {
            val response = NetworkClient.quranApiService.getSurahs()
            if (response.code == 200 && response.data.isNotEmpty()) {
                val remoteSurahs = response.data.map { dto ->
                    Surah(
                        number = dto.number,
                        nameEnglish = dto.englishName,
                        nameArabic = dto.name,
                        englishTranslation = dto.englishNameTranslation,
                        numberOfAyahs = dto.numberOfAyahs,
                        revelationType = dto.revelationType
                    )
                }
                emit(remoteSurahs)
            }
        } catch (_: Exception) {
            // Keep verified offline data silently on failure
        }
    }.flowOn(Dispatchers.IO)

    fun getVersesForSurah(surahNumber: Int): Flow<List<Ayah>> = flow {
        // Emit offline cached verses first for instant response
        val initialVerses = OfflineQuranData.getVersesForSurah(surahNumber)
        emit(initialVerses)

        // Attempt remote fetch for full Uthmani and English translation
        try {
            val response = NetworkClient.quranApiService.getSurahVerses(surahNumber)
            if (response.code == 200 && response.data.size >= 2) {
                val arabicEdition = response.data[0]
                val englishEdition = response.data[1]

                val formattedSurahNum = String.format("%03d", surahNumber)
                val combinedAyahs = arabicEdition.ayahs.mapIndexed { index, arabicAyah ->
                    val translationAyah = englishEdition.ayahs.getOrNull(index)
                    val formattedAyahNum = String.format("%03d", arabicAyah.numberInSurah)
                    Ayah(
                        numberInSurah = arabicAyah.numberInSurah,
                        numberInQuran = arabicAyah.number,
                        textArabic = arabicAyah.text,
                        translationEnglish = translationAyah?.text ?: "",
                        transliteration = "Ayah ${arabicAyah.numberInSurah}",
                        audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$formattedSurahNum$formattedAyahNum.mp3",
                        juzNumber = arabicAyah.juz ?: 1
                    )
                }
                emit(combinedAyahs)
            }
        } catch (_: Exception) {
            // Offline verified data continues to serve seamlessly
        }
    }.flowOn(Dispatchers.IO)

    fun getJuzList(): List<Juz> = OfflineQuranData.allJuz

    fun getDailyVerse(): Ayah = OfflineQuranData.dailyVerse

    // Bookmarks
    fun getAllBookmarks(): Flow<List<BookmarkEntity>> = quranDao.getAllBookmarks()

    suspend fun saveBookmark(bookmark: BookmarkEntity) = withContext(Dispatchers.IO) {
        quranDao.insertBookmark(bookmark)
    }

    suspend fun deleteBookmark(surahNumber: Int, ayahNumber: Int) = withContext(Dispatchers.IO) {
        quranDao.deleteBookmark(surahNumber, ayahNumber)
    }

    fun isBookmarked(surahNumber: Int, ayahNumber: Int): Flow<Boolean> =
        quranDao.isBookmarked(surahNumber, ayahNumber)

    // Last Read
    fun getLastRead(): Flow<LastReadEntity?> = quranDao.getLastRead()

    suspend fun updateLastRead(
        surahNumber: Int,
        surahNameEnglish: String,
        surahNameArabic: String,
        ayahNumber: Int,
        totalAyahs: Int
    ) = withContext(Dispatchers.IO) {
        quranDao.updateLastRead(
            LastReadEntity(
                id = 1,
                surahNumber = surahNumber,
                surahNameEnglish = surahNameEnglish,
                surahNameArabic = surahNameArabic,
                ayahNumber = ayahNumber,
                totalAyahs = totalAyahs,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    // Favorites
    fun getFavorites(category: String): Flow<List<FavoriteEntity>> =
        quranDao.getFavoritesByCategory(category)

    suspend fun toggleFavorite(favorite: FavoriteEntity, isCurrentlyFavorite: Boolean) = withContext(Dispatchers.IO) {
        if (isCurrentlyFavorite) {
            quranDao.deleteFavorite(favorite.category, favorite.itemId)
        } else {
            quranDao.insertFavorite(favorite)
        }
    }

    fun isFavorite(category: String, itemId: String): Flow<Boolean> =
        quranDao.isFavorite(category, itemId)
}
