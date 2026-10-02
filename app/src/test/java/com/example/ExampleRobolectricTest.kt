package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.datasource.OfflineDuaData
import com.example.data.datasource.OfflineHadithData
import com.example.data.datasource.OfflineKidsData
import com.example.data.datasource.OfflineNamesData
import com.example.data.datasource.OfflineProphetsData
import com.example.data.datasource.OfflineQuranData
import com.example.data.repository.IslamicToolsRepository
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.ThemeManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun testAppNameString() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("The Daily Quran", appName)
    }

    @Test
    fun testThemeManagerDataStore() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val themeManager = ThemeManager.getInstance(context)
        assertNotNull(themeManager)

        // Test mode conversions
        assertEquals(AppThemeMode.LIGHT, AppThemeMode.fromString("light"))
        assertEquals(AppThemeMode.DARK, AppThemeMode.fromString("dark"))
        assertEquals(AppThemeMode.SYSTEM, AppThemeMode.fromString("system"))
        assertEquals(AppThemeMode.SYSTEM, AppThemeMode.fromString("unknown"))

        // Set mode directly
        themeManager.setThemeMode(AppThemeMode.LIGHT)
        assertFalse("Light mode isDark should return false", themeManager.isDark(systemInDark = true))

        themeManager.setThemeMode(AppThemeMode.DARK)
        assertTrue("Dark mode isDark should return true", themeManager.isDark(systemInDark = false))

        themeManager.setThemeMode(AppThemeMode.SYSTEM)
        assertTrue(themeManager.isDark(systemInDark = true))
        assertFalse(themeManager.isDark(systemInDark = false))
    }

    @Test
    fun testQuranSurahsAndJuzIntegrity() {
        assertEquals("The Holy Quran must have 114 Surahs", 114, OfflineQuranData.allSurahs.size)
        assertEquals("The Holy Quran must have 30 Juz", 30, OfflineQuranData.allJuz.size)

        val fatihah = OfflineQuranData.allSurahs[0]
        assertEquals("Al-Fatihah", fatihah.nameEnglish)
        assertEquals("الفاتحة", fatihah.nameArabic)
        assertEquals(7, fatihah.numberOfAyahs)

        val fatihahVerses = OfflineQuranData.getVersesForSurah(1)
        assertEquals(7, fatihahVerses.size)
        assertTrue(fatihahVerses[0].textArabic.contains("بِسْمِ اللَّهِ"))
    }

    @Test
    fun testHadithAndDuaIntegrity() {
        assertTrue("Hadith collection must not be empty", OfflineHadithData.hadiths.isNotEmpty())
        assertTrue("Duas collection must not be empty", OfflineDuaData.duas.isNotEmpty())

        val bukhari1 = OfflineHadithData.hadiths.first()
        assertTrue(bukhari1.arabicText.contains("إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ"))
        assertEquals("Sahih (Authentic)", bukhari1.grade)
    }

    @Test
    fun testQiblaAndPrayerTimesCalculations() {
        val qiblaBearing = IslamicToolsRepository.getQiblaBearing(23.8103, 90.4125)
        assertTrue("Qibla bearing must be between 0 and 360 degrees", qiblaBearing in 0.0..360.0)

        val prayerTimes = IslamicToolsRepository.getTodayPrayerTimes()
        assertNotNull(prayerTimes.fajr)
        assertNotNull(prayerTimes.nextPrayerName)
        assertNotNull(prayerTimes.nextPrayerCountdown)
    }

    @Test
    fun testIslamicNamesAndProphetsTree() {
        assertEquals("Prophets tree must have exactly 25 prophets", 25, OfflineProphetsData.prophets.size)
        assertEquals("Adam", OfflineProphetsData.prophets.first().nameEnglish)
        assertEquals("Muhammad", OfflineProphetsData.prophets.last().nameEnglish)

        assertTrue(OfflineNamesData.names.isNotEmpty())
        assertEquals(28, OfflineKidsData.arabicAlphabet.size)
    }
}
