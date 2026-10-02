package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// =======================================================
// THE DAILY QURAN — SMART LUXURY ISLAMIC COLOR SYSTEM
// =======================================================

// Imperial Mosque Emeralds (Deep, Serene & Timeless)
val ImperialEmerald950 = Color(0xFF03140C)
val ImperialEmerald900 = Color(0xFF072417)
val ImperialEmerald850 = Color(0xFF0B3824)
val ImperialEmerald800 = Color(0xFF0E4D32) // Primary Light
val ImperialEmerald700 = Color(0xFF146844)
val ImperialEmerald600 = Color(0xFF1B8557)
val ImperialEmerald500 = Color(0xFF26A870)
val ImperialEmerald400 = Color(0xFF3ED28F)
val ImperialEmerald300 = Color(0xFF6EE7B0) // Primary Dark
val ImperialEmerald200 = Color(0xFFA7F3D0)
val ImperialEmerald100 = Color(0xFFD1FAE5)
val ImperialEmerald50  = Color(0xFFECFDF5)

// Backward-compatible aliases
val Emerald950 = ImperialEmerald950
val Emerald900 = ImperialEmerald900
val Emerald850 = ImperialEmerald850
val Emerald800 = ImperialEmerald800
val Emerald700 = ImperialEmerald700
val Emerald600 = ImperialEmerald600
val Emerald500 = ImperialEmerald500
val Emerald400 = ImperialEmerald400
val Emerald300 = ImperialEmerald300
val Emerald200 = ImperialEmerald200
val Emerald100 = ImperialEmerald100
val Emerald50  = ImperialEmerald50

// Royal Champagne & Minaret Golds (Luminous Accents)
val RoyalGoldBrass      = Color(0xFFC89B3C) // Warm metallic brass
val RoyalGoldDorado     = Color(0xFFD4AF37) // Iconic pure Islamic gold
val RoyalGoldGlow       = Color(0xFFF3DA8E) // Soft radiance
val RoyalGoldAmber      = Color(0xFFE5A93C) // Vibrant amber
val RoyalGoldAntique    = Color(0xFF8F6B1D) // Deep antique bronze
val RoyalGoldContainer  = Color(0xFFFCF6E8) // Light mode cream container
val RoyalGoldDarkCard   = Color(0xFF2C220B) // Dark mode golden container

val IslamicGoldRoyal         = RoyalGoldDorado
val IslamicGoldWarm          = RoyalGoldBrass
val IslamicGold              = RoyalGoldDorado
val IslamicGoldLight         = RoyalGoldGlow
val IslamicGoldDark          = RoyalGoldAntique
val IslamicGoldContainer     = RoyalGoldContainer
val IslamicGoldDarkContainer = RoyalGoldDarkCard

// Parchment, Alabaster & Marble Whites (Light Theme)
val AlabasterParchment = Color(0xFFFAF7F2) // Soft warm background
val PristineSurface    = Color(0xFFFFFFFF) // Crisp white card surface
val SandstoneSurface   = Color(0xFFF3EFE7) // Secondary surface
val TextInkBlack       = Color(0xFF111D16) // High contrast text
val TextMutedSage      = Color(0xFF4D6154) // Subtitle text
val WarmBorderSubtle   = Color(0xFFE4DDD0) // Delicate card border

val ParchmentBackground = AlabasterParchment
val ParchmentSurface    = PristineSurface
val ParchmentVariant    = SandstoneSurface
val TextCharcoal        = TextInkBlack
val TextSubtitle        = TextMutedSage
val BorderSubtle        = WarmBorderSubtle

// Nocturnal Obsidian Velvet (Dark Theme)
val ObsidianNightBase    = Color(0xFF040D08) // Deepest nocturnal green-black
val ObsidianJadeSurface  = Color(0xFF0A1D13) // Luxurious surface
val ObsidianElevatedCard = Color(0xFF102A1C) // Elevated card surface
val FrostWhiteText       = Color(0xFFF3F7F4) // Crisp primary text
val CeladonMutedText     = Color(0xFF9AB2A4) // Soft secondary text
val ObsidianBorderSubtle = Color(0xFF1A3B2A) // Subtle emerald border

val DarkObsidianBackground = ObsidianNightBase
val DarkObsidianSurface    = ObsidianJadeSurface
val DarkObsidianVariant    = ObsidianElevatedCard
val DarkTextPrimary        = FrostWhiteText
val DarkTextSecondary      = CeladonMutedText
val DarkBorderSubtle       = ObsidianBorderSubtle

// =======================================================
// SMART GRADIENT BRUSHES
// =======================================================
object SmartGradients {
    val EmeraldLuxury = Brush.linearGradient(
        colors = listOf(Color(0xFF0E4D32), Color(0xFF062819))
    )
    val EmeraldVibrant = Brush.linearGradient(
        colors = listOf(Color(0xFF146844), Color(0xFF0A3A25))
    )
    val GoldRadiance = Brush.linearGradient(
        colors = listOf(Color(0xFFE7C268), Color(0xFFC89B3C), Color(0xFFB38528))
    )
    val DarkVelvetCard = Brush.linearGradient(
        colors = listOf(Color(0xFF0E2519), Color(0xFF07170E))
    )
    val LightCardSheen = Brush.linearGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFAF7F2))
    )
    val QuranHeaderSheen = Brush.verticalGradient(
        colors = listOf(Color(0xFF0F472C), Color(0xFF062215))
    )
}
