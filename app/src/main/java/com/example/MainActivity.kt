package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ui.navigation.DailyQuranAppContent
import com.example.ui.theme.DailyQuranTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val app = application as DailyQuranApp
            val themeMode by app.themeManager.themeMode.collectAsState()
            val systemDark = isSystemInDarkTheme()
            val isDark = when (themeMode) {
                com.example.ui.theme.AppThemeMode.LIGHT -> false
                com.example.ui.theme.AppThemeMode.DARK -> true
                com.example.ui.theme.AppThemeMode.SYSTEM -> systemDark
            }

            DailyQuranTheme(darkTheme = isDark) {
                DailyQuranAppContent()
            }
        }
    }
}
