package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.Reciter
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.ThemeManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserPreferencesRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("daily_quran_user_prefs", Context.MODE_PRIVATE)

    val themeManager: ThemeManager = ThemeManager.getInstance(context)

    val availableReciters = listOf(
        Reciter("alafasy", "Mishary Rashid Alafasy", "Hafs an Asim - 128kbps", "https://everyayah.com/data/Alafasy_128kbps/"),
        Reciter("abdulbaset", "AbdulBaset AbdulSamad", "Murattal - 192kbps", "https://everyayah.com/data/Abdul_Basit_Murattal_192kbps/"),
        Reciter("muaiqly", "Maher Al-Muaiqly", "Hafs an Asim - 128kbps", "https://everyayah.com/data/MaherAlMuaiqly128kbps/"),
        Reciter("sudais", "Abdur-Rahman as-Sudais", "Hafs an Asim - 192kbps", "https://everyayah.com/data/Abdurrahmaan_As-Sudais_192kbps/")
    )

    private val _arabicFontSize = MutableStateFlow(prefs.getFloat(KEY_ARABIC_FONT_SIZE, 26f))
    val arabicFontSize: StateFlow<Float> = _arabicFontSize.asStateFlow()

    private val _translationFontSize = MutableStateFlow(prefs.getFloat(KEY_TRANS_FONT_SIZE, 16f))
    val translationFontSize: StateFlow<Float> = _translationFontSize.asStateFlow()

    private val _selectedReciterId = MutableStateFlow(prefs.getString(KEY_RECITER, "alafasy") ?: "alafasy")
    val selectedReciterId: StateFlow<String> = _selectedReciterId.asStateFlow()

    private val _showTransliteration = MutableStateFlow(prefs.getBoolean(KEY_SHOW_TRANSLITERATION, true))
    val showTransliteration: StateFlow<Boolean> = _showTransliteration.asStateFlow()

    private val _tasbeehHaptics = MutableStateFlow(prefs.getBoolean(KEY_TASBEEH_HAPTICS, true))
    val tasbeehHaptics: StateFlow<Boolean> = _tasbeehHaptics.asStateFlow()

    private val _tasbeehSound = MutableStateFlow(prefs.getBoolean(KEY_TASBEEH_SOUND, true))
    val tasbeehSound: StateFlow<Boolean> = _tasbeehSound.asStateFlow()

    // DataStore-backed ThemeMode
    val themeMode: StateFlow<AppThemeMode> = themeManager.themeMode

    // Prayer notification reminders toggle
    private val _prayerReminders = MutableStateFlow(prefs.getBoolean(KEY_PRAYER_REMINDERS, true))
    val prayerReminders: StateFlow<Boolean> = _prayerReminders.asStateFlow()

    fun setArabicFontSize(size: Float) {
        prefs.edit().putFloat(KEY_ARABIC_FONT_SIZE, size).apply()
        _arabicFontSize.value = size
    }

    fun setTranslationFontSize(size: Float) {
        prefs.edit().putFloat(KEY_TRANS_FONT_SIZE, size).apply()
        _translationFontSize.value = size
    }

    fun setSelectedReciter(reciterId: String) {
        prefs.edit().putString(KEY_RECITER, reciterId).apply()
        _selectedReciterId.value = reciterId
    }

    fun setShowTransliteration(show: Boolean) {
        prefs.edit().putBoolean(KEY_SHOW_TRANSLITERATION, show).apply()
        _showTransliteration.value = show
    }

    fun setTasbeehHaptics(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_TASBEEH_HAPTICS, enabled).apply()
        _tasbeehHaptics.value = enabled
    }

    fun setTasbeehSound(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_TASBEEH_SOUND, enabled).apply()
        _tasbeehSound.value = enabled
    }

    fun setThemeMode(mode: AppThemeMode) {
        themeManager.setThemeMode(mode)
    }

    fun setThemeMode(modeString: String) {
        themeManager.setThemeMode(AppThemeMode.fromString(modeString))
    }

    fun setPrayerReminders(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_PRAYER_REMINDERS, enabled).apply()
        _prayerReminders.value = enabled
    }

    fun isDark(systemInDark: Boolean): Boolean {
        return themeManager.isDark(systemInDark)
    }

    fun toggleTheme(systemInDark: Boolean) {
        themeManager.toggleTheme(systemInDark)
    }

    companion object {
        private const val KEY_ARABIC_FONT_SIZE = "key_arabic_font_size"
        private const val KEY_TRANS_FONT_SIZE = "key_trans_font_size"
        private const val KEY_RECITER = "key_reciter"
        private const val KEY_SHOW_TRANSLITERATION = "key_show_transliteration"
        private const val KEY_TASBEEH_HAPTICS = "key_tasbeeh_haptics"
        private const val KEY_TASBEEH_SOUND = "key_tasbeeh_sound"
        private const val KEY_PRAYER_REMINDERS = "key_prayer_reminders"
    }
}
