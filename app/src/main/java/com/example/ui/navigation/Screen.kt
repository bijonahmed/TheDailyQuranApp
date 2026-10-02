package com.example.ui.navigation

sealed class Screen(val route: String) {
    // Bottom Bar Tabs
    data object Home : Screen("home")
    data object Quran : Screen("quran")
    data object Hadith : Screen("hadith")
    data object Tools : Screen("tools")
    data object More : Screen("more")

    // Features and Subscreens
    data object Reader : Screen("reader/{surahNumber}") {
        fun createRoute(surahNumber: Int) = "reader/$surahNumber"
    }
    data object PrayerTimes : Screen("prayer_times")
    data object Qibla : Screen("qibla")
    data object HijriCalendar : Screen("hijri_calendar")
    data object IslamicNames : Screen("islamic_names")
    data object KidsZone : Screen("kids_zone")
    data object ProphetsTree : Screen("prophets_tree")
    data object BooksBlog : Screen("books_blog")
    data object Bookmarks : Screen("bookmarks")
    data object Settings : Screen("settings")
    data object Duas : Screen("duas")
    data object Tasbeeh : Screen("tasbeeh")
    data object About : Screen("about")
}
