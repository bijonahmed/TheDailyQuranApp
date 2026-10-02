package com.example.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.components.AudioPlayerFloatingBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.BooksBlogScreen
import com.example.ui.screens.DuaScreen
import com.example.ui.screens.HadithScreen
import com.example.ui.screens.HijriCalendarScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.IslamicNamesScreen
import com.example.ui.screens.KidsZoneScreen
import com.example.ui.screens.MoreScreen
import com.example.ui.screens.PrayerTimesScreen
import com.example.ui.screens.ProphetsTreeScreen
import com.example.ui.screens.QiblaCompassScreen
import com.example.ui.screens.QuranReaderScreen
import com.example.ui.screens.QuranScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.TasbeehScreen
import com.example.ui.screens.ToolsScreen
import com.example.ui.theme.Emerald800
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.DuaViewModel
import com.example.ui.viewmodel.HadithViewModel
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.QuranViewModel
import com.example.ui.viewmodel.TasbeehViewModel
import com.example.ui.viewmodel.ToolsViewModel

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun DailyQuranAppContent(
    mainViewModel: MainViewModel = viewModel(),
    quranViewModel: QuranViewModel = viewModel(),
    hadithViewModel: HadithViewModel = viewModel(),
    tasbeehViewModel: TasbeehViewModel = viewModel(),
    duaViewModel: DuaViewModel = viewModel(),
    toolsViewModel: ToolsViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val playerState by mainViewModel.playerState.collectAsState()

    val navItems = listOf(
        BottomNavItem(Screen.Home.route, "Home", Icons.Default.Home),
        BottomNavItem(Screen.Quran.route, "Quran", Icons.AutoMirrored.Filled.MenuBook),
        BottomNavItem(Screen.Hadith.route, "Hadith", Icons.Default.Book),
        BottomNavItem(Screen.Tools.route, "Tools", Icons.Default.Spa),
        BottomNavItem(Screen.More.route, "More", Icons.Default.MoreHoriz)
    )

    val isTopLevelRoute = navItems.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (isTopLevelRoute) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                ) {
                    navItems.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Emerald800,
                                selectedTextColor = Emerald800,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.testTag("nav_item_${item.route}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.fillMaxSize()
            ) {
                // 1. Home
                composable(Screen.Home.route) {
                    HomeScreen(
                        viewModel = mainViewModel,
                        onNavigateToSurah = { surahNumber ->
                            navController.navigate(Screen.Reader.createRoute(surahNumber))
                        },
                        onNavigateToPrayerTimes = { navController.navigate(Screen.PrayerTimes.route) },
                        onNavigateToQibla = { navController.navigate(Screen.Qibla.route) },
                        onNavigateToTasbeeh = { navController.navigate(Screen.Tasbeeh.route) },
                        onNavigateToDuas = { navController.navigate(Screen.Duas.route) },
                        onNavigateToKidsZone = { navController.navigate(Screen.KidsZone.route) },
                        onNavigateToProphetsTree = { navController.navigate(Screen.ProphetsTree.route) },
                        onNavigateToBooksBlog = { navController.navigate(Screen.BooksBlog.route) },
                        onNavigateToHijriCalendar = { navController.navigate(Screen.HijriCalendar.route) },
                        onNavigateToAbout = { navController.navigate(Screen.About.route) }
                    )
                }

                // 2. Quran
                composable(Screen.Quran.route) {
                    QuranScreen(
                        viewModel = quranViewModel,
                        onSurahClick = { surahNumber ->
                            navController.navigate(Screen.Reader.createRoute(surahNumber))
                        },
                        onBookmarksClick = {
                            navController.navigate(Screen.Bookmarks.route)
                        }
                    )
                }

                // 3. Hadith
                composable(Screen.Hadith.route) {
                    HadithScreen(viewModel = hadithViewModel)
                }

                // 4. Tools
                composable(Screen.Tools.route) {
                    ToolsScreen(
                        onPrayerTimesClick = { navController.navigate(Screen.PrayerTimes.route) },
                        onQiblaClick = { navController.navigate(Screen.Qibla.route) },
                        onHijriCalendarClick = { navController.navigate(Screen.HijriCalendar.route) },
                        onIslamicNamesClick = { navController.navigate(Screen.IslamicNames.route) },
                        onTasbeehClick = { navController.navigate(Screen.Tasbeeh.route) },
                        onDuasClick = { navController.navigate(Screen.Duas.route) },
                        onBookmarksClick = { navController.navigate(Screen.Bookmarks.route) }
                    )
                }

                // 5. More
                composable(Screen.More.route) {
                    MoreScreen(
                        onNavigateToKids = { navController.navigate(Screen.KidsZone.route) },
                        onNavigateToProphets = { navController.navigate(Screen.ProphetsTree.route) },
                        onNavigateToBooks = { navController.navigate(Screen.BooksBlog.route) },
                        onNavigateToBookmarks = { navController.navigate(Screen.Bookmarks.route) },
                        onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                        onNavigateToDuas = { navController.navigate(Screen.Duas.route) },
                        onNavigateToAbout = { navController.navigate(Screen.About.route) }
                    )
                }

                // Reader Screen
                composable(
                    route = Screen.Reader.route,
                    arguments = listOf(navArgument("surahNumber") { type = NavType.IntType })
                ) { backStackEntry ->
                    val surahNumber = backStackEntry.arguments?.getInt("surahNumber") ?: 1
                    QuranReaderScreen(
                        surahNumber = surahNumber,
                        viewModel = quranViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                // Tools Subscreens
                composable(Screen.PrayerTimes.route) {
                    PrayerTimesScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.Qibla.route) {
                    QiblaCompassScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.HijriCalendar.route) {
                    HijriCalendarScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.IslamicNames.route) {
                    IslamicNamesScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.Tasbeeh.route) {
                    TasbeehScreen(
                        viewModel = tasbeehViewModel,
                        showBackButton = true,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.Duas.route) {
                    DuaScreen(
                        viewModel = duaViewModel,
                        showBackButton = true,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.KidsZone.route) {
                    KidsZoneScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.ProphetsTree.route) {
                    ProphetsTreeScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.BooksBlog.route) {
                    BooksBlogScreen(
                        viewModel = toolsViewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.Bookmarks.route) {
                    BookmarksScreen(
                        viewModel = quranViewModel,
                        onNavigateToSurah = { surahNumber ->
                            navController.navigate(Screen.Reader.createRoute(surahNumber))
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.Settings.route) {
                    SettingsScreen(
                        viewModel = quranViewModel,
                        onNavigateToAbout = { navController.navigate(Screen.About.route) },
                        onBackClick = { navController.popBackStack() }
                    )
                }

                composable(Screen.About.route) {
                    AboutScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }

            // Floating Audio Mini-Player Bar
            AudioPlayerFloatingBar(
                state = playerState,
                onPlayPause = { mainViewModel.playPauseAudio() },
                onClose = { mainViewModel.audioPlayer.stop() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = if (isTopLevelRoute) 64.dp else 12.dp)
            )
        }
    }
}
