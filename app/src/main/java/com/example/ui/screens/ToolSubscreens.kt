package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.IslamicName
import com.example.ui.components.DailyQuranTopBar
import com.example.ui.theme.Emerald800
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.ToolsViewModel
import com.example.util.CompassSensor
import kotlin.math.abs

@Composable
fun PrayerTimesScreen(
    viewModel: ToolsViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val prayerTimes by viewModel.prayerTimes.collectAsState()
    val hijriDate by viewModel.hijriDate.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "Prayer Times",
            subtitle = prayerTimes.city,
            showBackButton = true,
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Next Prayer Hero Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Emerald800),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(22.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "UPCOMING PRAYER",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = IslamicGold,
                                letterSpacing = 1.2.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = prayerTimes.nextPrayerName,
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "in ${prayerTimes.nextPrayerCountdown}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color(0xFFD4EDDA)
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = IslamicGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${prayerTimes.city} • ${prayerTimes.calculationMethod}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color(0xFFD4EDDA)
                                )
                            )
                        }
                    }
                }
            }

            // Prayer Times Schedule Rows
            item {
                PrayerScheduleCard(name = "Fajr", arabic = "الفجر", time = prayerTimes.fajr, isNext = prayerTimes.nextPrayerName == "Fajr")
            }
            item {
                PrayerScheduleCard(name = "Sunrise", arabic = "الشروق", time = prayerTimes.sunrise, isNext = prayerTimes.nextPrayerName == "Sunrise")
            }
            item {
                PrayerScheduleCard(name = "Dhuhr", arabic = "الظهر", time = prayerTimes.dhuhr, isNext = prayerTimes.nextPrayerName == "Dhuhr")
            }
            item {
                PrayerScheduleCard(name = "Asr", arabic = "العصر", time = prayerTimes.asr, isNext = prayerTimes.nextPrayerName == "Asr")
            }
            item {
                PrayerScheduleCard(name = "Maghrib", arabic = "المغرب", time = prayerTimes.maghrib, isNext = prayerTimes.nextPrayerName == "Maghrib")
            }
            item {
                PrayerScheduleCard(name = "Isha", arabic = "العشاء", time = prayerTimes.isha, isNext = prayerTimes.nextPrayerName == "Isha")
            }

            item {
                Text(
                    text = "* Times calculated based on standard astronomical solar equations and verified Islamic calculation conventions.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun PrayerScheduleCard(
    name: String,
    arabic: String,
    time: String,
    isNext: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isNext) Modifier.border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                else Modifier
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isNext) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (isNext) FontWeight.Bold else FontWeight.SemiBold,
                        color = if (isNext) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = arabic,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Serif,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Text(
                text = time,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isNext) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

@Composable
fun QiblaCompassScreen(
    viewModel: ToolsViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current
    val qiblaBearing by viewModel.qiblaBearing.collectAsState()

    // Real device compass sensor listener
    val compassSensor = remember { CompassSensor(context) }
    val deviceAzimuth by compassSensor.azimuth.collectAsState()
    val hasSensor by compassSensor.hasSensor.collectAsState()

    DisposableEffect(Unit) {
        compassSensor.start()
        onDispose {
            compassSensor.stop()
        }
    }

    // Relative angle between current phone heading and Qibla
    val needleAngle = if (hasSensor) {
        (qiblaBearing.toFloat() - deviceAzimuth + 360) % 360
    } else {
        qiblaBearing.toFloat()
    }
    val animatedBearing by animateFloatAsState(targetValue = needleAngle, label = "compass_rotation")

    // Check if aligned towards Qibla within ±5 degrees
    val isAligned = hasSensor && (animatedBearing in 0f..5f || animatedBearing in 355f..360f)

    LaunchedEffect(isAligned) {
        if (isAligned) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "Qibla Direction",
            subtitle = if (isAligned) "Facing Holy Ka'bah! 🕋" else "Direction to the Holy Ka'bah",
            showBackButton = true,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isAligned) Modifier.border(2.dp, IslamicGold, RoundedCornerShape(16.dp))
                        else Modifier
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = if (isAligned) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isAligned) "🕋 Qibla Aligned (${qiblaBearing.toInt()}°)" else "Qibla Bearing: ${qiblaBearing.toInt()}°",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isAligned) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Text(
                        text = if (hasSensor) "Device compass active: rotate phone until the needle points up" else "Bearing towards Holy Ka'bah in Makkah (from True North)",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            // Compass Dial View
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = if (isAligned) 6.dp else 4.dp,
                        color = if (isAligned) IslamicGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                // North indicator
                Text(
                    text = "N",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Red
                    ),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 10.dp)
                )
                Text(
                    text = "S",
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = "E",
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 12.dp)
                )
                Text(
                    text = "W",
                    style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp)
                )

                // Rotating needle pointing to Qibla
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .rotate(animatedBearing),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (isAligned) IslamicGold else Emerald800,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "🕋",
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(60.dp)
                                .background(if (isAligned) IslamicGold else MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            Text(
                text = "Hold your phone flat away from metal cases or magnets. Real-time compass smoothly tracks your heading.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun HijriCalendarScreen(
    viewModel: ToolsViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hijriDate by viewModel.hijriDate.collectAsState()

    val importantDates = listOf(
        "1 Muharram" to "Islamic New Year (1446 AH)",
        "10 Muharram" to "Day of Ashura (Fasting)",
        "12 Rabi' al-Awwal" to "Mawlid an-Nabi (Birth of the Prophet)",
        "27 Rajab" to "Al-Isra' wal-Mi'raj (Night Journey)",
        "15 Sha'ban" to "Laylat al-Bara'at (Night of Forgiveness)",
        "1 Ramadan" to "Beginning of the Blessed Month of Ramadan",
        "27 Ramadan" to "Laylat al-Qadr (Night of Power)",
        "1 Shawwal" to "Eid al-Fitr (Festival of Fast-Breaking)",
        "9 Dhu al-Hijjah" to "Day of Arafah (Hajj culmination)",
        "10 Dhu al-Hijjah" to "Eid al-Adha (Feast of the Sacrifice)"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "Islamic Calendar",
            subtitle = "Umm al-Qura Hijri Dates",
            showBackButton = true,
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Today's Date Banner
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Emerald800),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(22.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "TODAY IN HIJRI",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = IslamicGold,
                                letterSpacing = 1.2.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${hijriDate.hijriDay} ${hijriDate.hijriMonthName} ${hijriDate.hijriYear} AH",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = hijriDate.gregorianFormatted,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFFD4EDDA)
                            )
                        )
                        hijriDate.specialOccasion?.let { occasion ->
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = IslamicGold
                            ) {
                                Text(
                                    text = occasion,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "IMPORTANT ISLAMIC DATES",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(start = 4.dp, top = 8.dp)
                )
            }

            items(importantDates) { (dateStr, eventName) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = eventName,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            )
                            Text(
                                text = dateStr,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "* Hijri dates may vary by 1–2 days according to regional lunar moon sightings.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun IslamicNamesScreen(
    viewModel: ToolsViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("All") }

    val filteredNames = viewModel.names.filter { item ->
        val matchesGender = selectedGender == "All" || item.gender == selectedGender
        val matchesSearch = searchQuery.isBlank() ||
                item.nameEnglish.contains(searchQuery, ignoreCase = true) ||
                item.meaning.contains(searchQuery, ignoreCase = true) ||
                item.nameArabic.contains(searchQuery)
        matchesGender && matchesSearch
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "Islamic Names",
            subtitle = "Beautiful Names with Meanings",
            showBackButton = true,
            onBackClick = onBackClick
        )

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            placeholder = { Text("Search by name or meaning...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Gender Tabs
        TabRow(
            selectedTabIndex = when (selectedGender) {
                "Boy" -> 1
                "Girl" -> 2
                else -> 0
            },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Tab(
                selected = selectedGender == "All",
                onClick = { selectedGender = "All" },
                text = { Text("All (${viewModel.names.size})") }
            )
            Tab(
                selected = selectedGender == "Boy",
                onClick = { selectedGender = "Boy" },
                text = { Text("Boys") }
            )
            Tab(
                selected = selectedGender == "Girl",
                onClick = { selectedGender = "Girl" },
                text = { Text("Girls") }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredNames, key = { it.id }) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = item.nameEnglish,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (item.gender == "Boy") Color(0xFFE3F2FD) else Color(0xFFFCE4EC)
                                ) {
                                    Text(
                                        text = item.gender,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (item.gender == "Boy") Color(0xFF1565C0) else Color(0xFFC2185B),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.meaning,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }

                        Text(
                            text = item.nameArabic,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
