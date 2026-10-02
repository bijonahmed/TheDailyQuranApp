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

// Smart Luxury Islamic Light Palette (Parchment, Emerald & Champagne Gold)
private val LightColorScheme = lightColorScheme(
    primary = ImperialEmerald800,
    onPrimary = Color.White,
    primaryContainer = ImperialEmerald50,
    onPrimaryContainer = ImperialEmerald950,
    secondary = RoyalGoldDorado,
    onSecondary = Color(0xFF281E04),
    secondaryContainer = RoyalGoldContainer,
    onSecondaryContainer = RoyalGoldAntique,
    tertiary = ImperialEmerald600,
    onTertiary = Color.White,
    background = AlabasterParchment,
    onBackground = TextInkBlack,
    surface = PristineSurface,
    onSurface = TextInkBlack,
    surfaceVariant = SandstoneSurface,
    onSurfaceVariant = TextMutedSage,
    outline = WarmBorderSubtle,
    outlineVariant = Color(0xFFCCC5B8)
)

// Smart Luxury Islamic Dark Palette (Obsidian Velvet, Radiant Jade & Luminous Gold)
private val DarkColorScheme = darkColorScheme(
    primary = ImperialEmerald300,
    onPrimary = ImperialEmerald950,
    primaryContainer = ObsidianElevatedCard,
    onPrimaryContainer = ImperialEmerald100,
    secondary = RoyalGoldGlow,
    onSecondary = Color(0xFF2C220B),
    secondaryContainer = RoyalGoldDarkCard,
    onSecondaryContainer = RoyalGoldGlow,
    tertiary = ImperialEmerald400,
    onTertiary = ImperialEmerald950,
    background = ObsidianNightBase,
    onBackground = FrostWhiteText,
    surface = ObsidianJadeSurface,
    onSurface = FrostWhiteText,
    surfaceVariant = ObsidianElevatedCard,
    onSurfaceVariant = CeladonMutedText,
    outline = ObsidianBorderSubtle,
    outlineVariant = Color(0xFF244835)
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
