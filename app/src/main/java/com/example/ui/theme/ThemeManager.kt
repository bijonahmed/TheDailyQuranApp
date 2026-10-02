package com.example.ui.theme

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.IOException

enum class AppThemeMode(val storageKey: String, val title: String) {
    SYSTEM("system", "System Default"),
    LIGHT("light", "Islamic Light"),
    DARK("dark", "Islamic Dark");

    companion object {
        fun fromString(value: String?): AppThemeMode {
            return entries.firstOrNull { it.storageKey.equals(value, ignoreCase = true) } ?: SYSTEM
        }
    }
}

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "daily_quran_theme_preferences")

class ThemeManager(private val context: Context) {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _themeMode = MutableStateFlow(AppThemeMode.SYSTEM)
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    init {
        scope.launch {
            context.themeDataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .collect { preferences ->
                    val modeString = preferences[THEME_MODE_KEY]
                    _themeMode.value = AppThemeMode.fromString(modeString)
                }
        }
    }

    fun setThemeMode(mode: AppThemeMode) {
        _themeMode.value = mode
        scope.launch {
            context.themeDataStore.edit { preferences ->
                preferences[THEME_MODE_KEY] = mode.storageKey
            }
        }
    }

    suspend fun updateThemeModeSync(mode: AppThemeMode) {
        _themeMode.value = mode
        context.themeDataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = mode.storageKey
        }
    }

    fun isDark(systemInDark: Boolean): Boolean {
        return when (_themeMode.value) {
            AppThemeMode.LIGHT -> false
            AppThemeMode.DARK -> true
            AppThemeMode.SYSTEM -> systemInDark
        }
    }

    fun toggleTheme(systemInDark: Boolean) {
        val currentIsDark = isDark(systemInDark)
        setThemeMode(if (currentIsDark) AppThemeMode.LIGHT else AppThemeMode.DARK)
    }

    companion object {
        private val THEME_MODE_KEY = stringPreferencesKey("theme_mode_key")

        @Volatile
        private var INSTANCE: ThemeManager? = null

        fun getInstance(context: Context): ThemeManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThemeManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
