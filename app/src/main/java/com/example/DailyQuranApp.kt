package com.example

import android.app.Application
import com.example.data.local.DailyQuranDatabase
import com.example.data.repository.QuranRepository
import com.example.data.repository.TasbeehRepository
import com.example.data.repository.UserPreferencesRepository
import com.example.player.QuranAudioPlayer
import com.example.ui.theme.ThemeManager

class DailyQuranApp : Application() {

    val themeManager: ThemeManager by lazy {
        ThemeManager.getInstance(this)
    }

    val database: DailyQuranDatabase by lazy {
        DailyQuranDatabase.getDatabase(this)
    }

    val quranRepository: QuranRepository by lazy {
        QuranRepository(database.quranDao())
    }

    val tasbeehRepository: TasbeehRepository by lazy {
        TasbeehRepository(database.tasbeehDao())
    }

    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(this)
    }

    val audioPlayer: QuranAudioPlayer by lazy {
        QuranAudioPlayer(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        audioPlayer.release()
    }

    companion object {
        lateinit var instance: DailyQuranApp
            private set
    }
}
