package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.DailyQuranApp
import com.example.data.datasource.OfflineBlogData
import com.example.data.datasource.OfflineDuaData
import com.example.data.datasource.OfflineHadithData
import com.example.data.datasource.OfflineKidsData
import com.example.data.datasource.OfflineNamesData
import com.example.data.datasource.OfflineProphetsData
import com.example.data.local.BookmarkEntity
import com.example.data.local.FavoriteEntity
import com.example.data.local.LastReadEntity
import com.example.data.local.TasbeehSessionEntity
import com.example.data.model.Article
import com.example.data.model.Ayah
import com.example.data.model.Dua
import com.example.data.model.Hadith
import com.example.data.model.IslamicDate
import com.example.data.model.IslamicName
import com.example.data.model.Juz
import com.example.data.model.KidsLetter
import com.example.data.model.KidsQuiz
import com.example.data.model.PrayerTimes
import com.example.data.model.Prophet
import com.example.data.model.Surah
import com.example.data.repository.IslamicToolsRepository
import com.example.player.AudioPlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as DailyQuranApp
    private val quranRepo = app.quranRepository
    val userPrefs = app.userPreferencesRepository
    val audioPlayer = app.audioPlayer

    val playerState: StateFlow<AudioPlayerState> = audioPlayer.state

    val lastRead: StateFlow<LastReadEntity?> = quranRepo.getLastRead()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val dailyVerse: Ayah = quranRepo.getDailyVerse()
    val dailyHadith: Hadith = OfflineHadithData.dailyHadith
    val dailyDua: Dua = OfflineDuaData.dailyDua

    private val _hijriDate = MutableStateFlow(IslamicToolsRepository.getTodayHijriDate())
    val hijriDate: StateFlow<IslamicDate> = _hijriDate.asStateFlow()

    private val _prayerTimes = MutableStateFlow(IslamicToolsRepository.getTodayPrayerTimes())
    val prayerTimes: StateFlow<PrayerTimes> = _prayerTimes.asStateFlow()

    fun playDailyVerse() {
        audioPlayer.playAyah(13, dailyVerse, listOf(dailyVerse))
    }

    fun playPauseAudio() {
        audioPlayer.playPause()
    }
}

class QuranViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as DailyQuranApp
    private val quranRepo = app.quranRepository
    val userPrefs = app.userPreferencesRepository
    val audioPlayer = app.audioPlayer

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedTab = MutableStateFlow(0) // 0: Surahs, 1: Juz
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    val surahs: StateFlow<List<Surah>> = quranRepo.getSurahs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val juzList: List<Juz> = quranRepo.getJuzList()

    val bookmarks: StateFlow<List<BookmarkEntity>> = quranRepo.getAllBookmarks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Reader state
    private val _currentSurah = MutableStateFlow<Surah?>(null)
    val currentSurah: StateFlow<Surah?> = _currentSurah.asStateFlow()

    private val _verses = MutableStateFlow<List<Ayah>>(emptyList())
    val verses: StateFlow<List<Ayah>> = _verses.asStateFlow()

    private val _isLoadingVerses = MutableStateFlow(false)
    val isLoadingVerses: StateFlow<Boolean> = _isLoadingVerses.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedTab(tabIndex: Int) {
        _selectedTab.value = tabIndex
    }

    fun openSurah(surahNumber: Int) {
        viewModelScope.launch {
            _isLoadingVerses.value = true
            val surah = surahs.value.firstOrNull { it.number == surahNumber }
                ?: com.example.data.datasource.OfflineQuranData.allSurahs.firstOrNull { it.number == surahNumber }
            _currentSurah.value = surah

            quranRepo.getVersesForSurah(surahNumber).collect { ayahList ->
                _verses.value = ayahList
                _isLoadingVerses.value = false
            }

            if (surah != null) {
                quranRepo.updateLastRead(
                    surahNumber = surah.number,
                    surahNameEnglish = surah.nameEnglish,
                    surahNameArabic = surah.nameArabic,
                    ayahNumber = 1,
                    totalAyahs = surah.numberOfAyahs
                )
            }
        }
    }

    fun toggleBookmark(ayah: Ayah) {
        val surah = _currentSurah.value ?: return
        viewModelScope.launch {
            val isBookmarked = bookmarks.value.any { it.surahNumber == surah.number && it.ayahNumber == ayah.numberInSurah }
            if (isBookmarked) {
                quranRepo.deleteBookmark(surah.number, ayah.numberInSurah)
            } else {
                quranRepo.saveBookmark(
                    BookmarkEntity(
                        surahNumber = surah.number,
                        surahNameEnglish = surah.nameEnglish,
                        surahNameArabic = surah.nameArabic,
                        ayahNumber = ayah.numberInSurah,
                        arabicText = ayah.textArabic,
                        translationText = ayah.translationEnglish
                    )
                )
            }
        }
    }

    fun playAyahAudio(ayah: Ayah) {
        val surah = _currentSurah.value ?: return
        val reciter = userPrefs.availableReciters.firstOrNull { it.id == userPrefs.selectedReciterId.value }
        val baseUrl = reciter?.baseUrlAyah ?: "https://everyayah.com/data/Alafasy_128kbps/"
        audioPlayer.playAyah(surah.number, ayah, _verses.value, baseUrl)
    }

    fun updateReadingPosition(ayahNumber: Int) {
        val surah = _currentSurah.value ?: return
        viewModelScope.launch {
            quranRepo.updateLastRead(
                surahNumber = surah.number,
                surahNameEnglish = surah.nameEnglish,
                surahNameArabic = surah.nameArabic,
                ayahNumber = ayahNumber,
                totalAyahs = surah.numberOfAyahs
            )
        }
    }
}

class TasbeehViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application as DailyQuranApp
    private val repo = app.tasbeehRepository
    val userPrefs = app.userPreferencesRepository

    val dhikrPresets = listOf(
        "SubhanAllah (سُبْحَانَ اللَّهِ)",
        "Alhamdulillah (الْحَمْدُ لِلَّهِ)",
        "Allahu Akbar (اللَّهُ أَكْبَرُ)",
        "La ilaha illallah (لَا إِلَهَ إِلَّا اللَّهُ)",
        "Astaghfirullah (أَسْتَغْفِرُ اللَّهَ)",
        "SubhanAllahi wa bihamdihi (سُبْحَانَ اللَّهِ وَبِحَمْدِهِ)",
        "La hawla wa la quwwata illa billah (لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ)"
    )

    private val _currentDhikr = MutableStateFlow(dhikrPresets[0])
    val currentDhikr: StateFlow<String> = _currentDhikr.asStateFlow()

    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _target = MutableStateFlow(33)
    val target: StateFlow<Int> = _target.asStateFlow()

    val sessions: StateFlow<List<TasbeehSessionEntity>> = repo.getAllSessions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalLifetimeCount: StateFlow<Int?> = repo.getTotalCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun increment() {
        _count.value++
        performHapticFeedback()
        if (_count.value % _target.value == 0) {
            saveCurrentSession()
        }
    }

    fun reset() {
        if (_count.value > 0) {
            saveCurrentSession()
        }
        _count.value = 0
    }

    fun setDhikr(dhikr: String) {
        if (_count.value > 0) {
            saveCurrentSession()
        }
        _currentDhikr.value = dhikr
        _count.value = 0
    }

    fun setTarget(newTarget: Int) {
        _target.value = newTarget
    }

    private fun saveCurrentSession() {
        val countToSave = _count.value
        if (countToSave > 0) {
            viewModelScope.launch {
                repo.saveSession(_currentDhikr.value, countToSave, _target.value)
            }
        }
    }

    private fun performHapticFeedback() {
        if (!userPrefs.tasbeehHaptics.value) return
        val context = getApplication<Application>()
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator?.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
            } else {
                @Suppress("DEPRECATION")
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator?.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    @Suppress("DEPRECATION")
                    vibrator?.vibrate(30)
                }
            }
        } catch (_: Exception) {}
    }
}

class HadithViewModel(application: Application) : AndroidViewModel(application) {
    val allHadiths: List<Hadith> = OfflineHadithData.hadiths

    private val _selectedCollection = MutableStateFlow("All")
    val selectedCollection: StateFlow<String> = _selectedCollection.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val collections = listOf("All", "Sahih al-Bukhari", "Sahih Muslim", "40 Hadith Nawawi")

    fun setCollection(collection: String) {
        _selectedCollection.value = collection
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

class DuaViewModel(application: Application) : AndroidViewModel(application) {
    val allDuas: List<Dua> = OfflineDuaData.duas
    val categories: List<String> = OfflineDuaData.categories

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

class ToolsViewModel(application: Application) : AndroidViewModel(application) {
    private val _prayerTimes = MutableStateFlow(IslamicToolsRepository.getTodayPrayerTimes())
    val prayerTimes: StateFlow<PrayerTimes> = _prayerTimes.asStateFlow()

    private val _hijriDate = MutableStateFlow(IslamicToolsRepository.getTodayHijriDate())
    val hijriDate: StateFlow<IslamicDate> = _hijriDate.asStateFlow()

    private val _qiblaBearing = MutableStateFlow(IslamicToolsRepository.getQiblaBearing())
    val qiblaBearing: StateFlow<Double> = _qiblaBearing.asStateFlow()

    val names: List<IslamicName> = OfflineNamesData.names
    val prophets: List<Prophet> = OfflineProphetsData.prophets
    val alphabet: List<KidsLetter> = OfflineKidsData.arabicAlphabet
    val quizzes: List<KidsQuiz> = OfflineKidsData.quizzes
    val articles: List<Article> = OfflineBlogData.articles
    val books: List<Article> = OfflineBlogData.books
}
