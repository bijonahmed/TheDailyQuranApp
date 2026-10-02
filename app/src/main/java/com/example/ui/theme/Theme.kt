package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Premium Islamic Light Palette (Parchment & Emerald)
private val LightColorScheme = lightColorScheme(
    primary = Emerald800,
    onPrimary = Color.White,
    primaryContainer = Emerald50,
    onPrimaryContainer = Emerald950,
    secondary = IslamicGoldWarm,
    onSecondary = Color(0xFF2B2002),
    secondaryContainer = IslamicGoldContainer,
    onSecondaryContainer = IslamicGoldDark,
    tertiary = Emerald600,
    onTertiary = Color.White,
    background = ParchmentBackground,
    onBackground = TextCharcoal,
    surface = ParchmentSurface,
    onSurface = TextCharcoal,
    surfaceVariant = ParchmentVariant,
    onSurfaceVariant = TextSubtitle,
    outline = BorderSubtle,
    outlineVariant = Color(0xFFC7D3CB)
)

// Premium Islamic Dark Palette (Obsidian Forest & Radiant Gold)
private val DarkColorScheme = darkColorScheme(
    primary = Emerald300,
    onPrimary = Emerald950,
    primaryContainer = DarkObsidianVariant,
    onPrimaryContainer = Emerald100,
    secondary = IslamicGoldLight,
    onSecondary = Color(0xFF2B2002),
    secondaryContainer = IslamicGoldDarkContainer,
    onSecondaryContainer = IslamicGoldLight,
    tertiary = Emerald400,
    onTertiary = Emerald950,
    background = DarkObsidianBackground,
    onBackground = DarkTextPrimary,
    surface = DarkObsidianSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkObsidianVariant,
    onSurfaceVariant = DarkTextSecondary,
    outline = DarkBorderSubtle,
    outlineVariant = Color(0xFF264734)
)

@Composable
fun DailyQuranTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.background.toArgb()
                window.navigationBarColor = colorScheme.background.toArgb()
                val controller = WindowCompat.getInsetsController(window, view)
                controller.isAppearanceLightStatusBars = !darkTheme
                controller.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    DailyQuranTheme(darkTheme = darkTheme, content = content)
}
