package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DailyQuranTopBar
import com.example.ui.components.DailyVerseCard
import com.example.ui.components.PrayerGlimpseCard
import com.example.ui.theme.Emerald800
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldRoyal
import com.example.ui.theme.SmartGradients
import com.example.ui.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onNavigateToSurah: (Int) -> Unit,
    onNavigateToPrayerTimes: () -> Unit,
    onNavigateToQibla: () -> Unit,
    onNavigateToTasbeeh: () -> Unit,
    onNavigateToDuas: () -> Unit,
    onNavigateToKidsZone: () -> Unit,
    onNavigateToProphetsTree: () -> Unit,
    onNavigateToBooksBlog: () -> Unit,
    onNavigateToHijriCalendar: () -> Unit,
    onNavigateToAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lastRead by viewModel.lastRead.collectAsState()
    val playerState by viewModel.playerState.collectAsState()
    val prayerTimes by viewModel.prayerTimes.collectAsState()
    val hijriDate by viewModel.hijriDate.collectAsState()
    val themeMode by viewModel.userPrefs.themeMode.collectAsState()
    val isSystemDark = isSystemInDarkTheme()
    val isDarkActive = viewModel.userPrefs.isDark(isSystemDark)
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "The Daily Quran",
            subtitle = "Read, Listen & Learn • thedailyquran.com",
            actions = {
                IconButton(
                    onClick = { viewModel.userPrefs.toggleTheme(isSystemDark) },
                    modifier = Modifier.testTag("home_theme_toggle")
                ) {
                    Text(
                        text = if (isDarkActive) "☀️" else "🌙",
                        fontSize = 18.sp
                    )
                }
                IconButton(
                    onClick = onNavigateToAbout,
                    modifier = Modifier.testTag("home_about_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "About",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Hero Bismillah Banner
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp)),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 1.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "In the Name of Allah, the Most Gracious, the Most Merciful",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }

            // Continue Reading / Last Read Card
            item {
                ContinueReadingCard(
                    surahNumber = lastRead?.surahNumber ?: 1,
                    surahName = lastRead?.surahNameEnglish ?: "Al-Fatihah",
                    surahNameArabic = lastRead?.surahNameArabic ?: "الفاتحة",
                    ayahNumber = lastRead?.ayahNumber ?: 1,
                    totalAyahs = lastRead?.totalAyahs ?: 7,
                    onContinueClick = {
                        onNavigateToSurah(lastRead?.surahNumber ?: 1)
                    }
                )
            }

            // Prayer Times Glimpse Card
            item {
                PrayerGlimpseCard(
                    prayerTimes = prayerTimes,
                    hijriDate = hijriDate,
                    onCardClick = onNavigateToPrayerTimes
                )
            }

            // Explore Platform Quick Hub
            item {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "EXPLORE PLATFORM",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.2.sp,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.padding(start = 4.dp)
                        )
                        Text(
                            text = "thedailyquran.com",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = IslamicGoldRoyal
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    QuickActionsGrid(
                        onTasbeeh = onNavigateToTasbeeh,
                        onQibla = onNavigateToQibla,
                        onDuas = onNavigateToDuas,
                        onKids = onNavigateToKidsZone,
                        onProphets = onNavigateToProphetsTree,
                        onCalendar = onNavigateToHijriCalendar,
                        onBooks = onNavigateToBooksBlog
                    )
                }
            }

            // Daily Quran Verse of the Day Card
            item {
                DailyVerseCard(
                    ayah = viewModel.dailyVerse,
                    surahReference = "Surah Ar-Ra'd 13:28",
                    isPlaying = playerState.isPlaying && playerState.currentSurahNumber == 13,
                    onPlayClick = { viewModel.playDailyVerse() },
                    onReadClick = { onNavigateToSurah(13) }
                )
            }

            // Hadith of the Day
            item {
                DailyHadithCard(
                    hadith = viewModel.dailyHadith,
                    onShareClick = {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "${viewModel.dailyHadith.arabicText}\n\n\"${viewModel.dailyHadith.englishText}\"\n\n— ${viewModel.dailyHadith.narrator} (${viewModel.dailyHadith.reference})\nShared via The Daily Quran • thedailyquran.com"
                            )
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(intent, "Share Hadith"))
                    }
                )
            }

            // Daily Dua
            item {
                DailyDuaCard(
                    dua = viewModel.dailyDua,
                    onViewDuas = onNavigateToDuas
                )
            }

            // Daily Islamic Quote
            item {
                DailyQuoteCard()
            }

            // Platform Website Card (thedailyquran.com)
            item {
                OfficialWebsiteBanner(
                    onVisit = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://thedailyquran.com/"))
                        context.startActivity(intent)
                    },
                    onAbout = onNavigateToAbout
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ContinueReadingCard(
    surahNumber: Int,
    surahName: String,
    surahNameArabic: String,
    ayahNumber: Int,
    totalAyahs: Int,
    onContinueClick: () -> Unit
) {
    val progress = (ayahNumber.toFloat() / totalAyahs.coerceAtLeast(1)).coerceIn(0f, 1f)
    val percentage = (progress * 100).toInt()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .clickable { onContinueClick() }
            .testTag("continue_reading_card"),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, IslamicGoldRoyal.copy(alpha = 0.4f)),
        tonalElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .background(SmartGradients.EmeraldLuxury)
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(IslamicGoldRoyal),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Last Read",
                                tint = Color.Black,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CONTINUE READING",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = IslamicGoldRoyal,
                                letterSpacing = 1.2.sp
                            )
                        )
                    }

                    // Progress percentage badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "$percentage% Done",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Surah $surahName",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Ayah $ayahNumber of $totalAyahs",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFFD4EDDA)
                            )
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = surahNameArabic,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                color = IslamicGoldRoyal
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Smart Progress bar
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = IslamicGoldRoyal,
                    trackColor = Color.White.copy(alpha = 0.2f),
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Resume Surah",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = IslamicGoldRoyal
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Resume",
                        tint = IslamicGoldRoyal,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionsGrid(
    onTasbeeh: () -> Unit,
    onQibla: () -> Unit,
    onDuas: () -> Unit,
    onKids: () -> Unit,
    onProphets: () -> Unit,
    onCalendar: () -> Unit,
    onBooks: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionItem(icon = Icons.Default.Spa, label = "Tasbeeh", onClick = onTasbeeh)
        QuickActionItem(icon = Icons.Default.CompassCalibration, label = "Qibla", onClick = onQibla)
        QuickActionItem(icon = Icons.Default.VolunteerActivism, label = "Duas", onClick = onDuas)
        QuickActionItem(icon = Icons.Default.DateRange, label = "Hijri", onClick = onCalendar)
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionItem(icon = Icons.Default.ChildCare, label = "Kids Zone", onClick = onKids)
        QuickActionItem(icon = Icons.AutoMirrored.Filled.MenuBook, label = "Prophets", onClick = onProphets)
        QuickActionItem(icon = Icons.Default.MenuBook, label = "Articles", onClick = onBooks)
        QuickActionItem(icon = Icons.Default.FormatQuote, label = "Quotes", onClick = onBooks)
    }
}

@Composable
private fun QuickActionItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Surface(
            modifier = Modifier.size(56.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
            tonalElevation = 2.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DailyHadithCard(
    hadith: com.example.data.model.Hadith,
    onShareClick: () -> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)),
        tonalElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "✦",
                        color = IslamicGoldRoyal,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "HADITH OF THE DAY",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = hadith.collection,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
            ) {
                Text(
                    text = hadith.arabicText,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 28.sp,
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "\"${hadith.englishText}\"",
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "— Narrated by ${hadith.narrator} (${hadith.reference})",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText(
                                "Hadith",
                                "${hadith.arabicText}\n\n\"${hadith.englishText}\"\n\n— ${hadith.narrator} (${hadith.reference})\nthedailyquran.com"
                            )
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Hadith copied to clipboard", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy Hadith",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    IconButton(
                        onClick = onShareClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Hadith",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DailyDuaCard(
    dua: com.example.data.model.Dua,
    onViewDuas: () -> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onViewDuas() },
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
        tonalElevation = 1.dp
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "✦",
                        color = IslamicGoldRoyal,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "DAILY SUPPLICATION",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }

                Text(
                    text = "View All →",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = dua.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dua.arabicText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dua.translation,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp
                )
            )
        }
    }
}

@Composable
fun DailyQuoteCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(IslamicGoldRoyal.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FormatQuote,
                    contentDescription = "Islamic Quote",
                    tint = IslamicGoldRoyal,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = "\"Be like a flower that gives fragrance even to the hand that crushes it.\"",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "— Ali ibn Abi Talib (RA)",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
    }
}

@Composable
fun OfficialWebsiteBanner(
    onVisit: () -> Unit,
    onAbout: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .clickable { onVisit() },
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, IslamicGoldRoyal.copy(alpha = 0.4f)),
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🌐", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "thedailyquran.com",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = "Official Web Platform",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }

                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.OpenInBrowser,
                            contentDescription = "Open Website",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Explore the comprehensive web library with all 114 Surahs, Hadith, Duas, Islamic Calendar, and Kids Zone online at thedailyquran.com.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    lineHeight = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onVisit() }
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.OpenInBrowser,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Open Website",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline),
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onAbout() }
                ) {
                    Box(
                        modifier = Modifier.padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "About App",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }
        }
    }
}
