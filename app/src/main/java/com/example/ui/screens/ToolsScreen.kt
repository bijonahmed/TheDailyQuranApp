package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DailyQuranTopBar

@Composable
fun ToolsScreen(
    onPrayerTimesClick: () -> Unit,
    onQiblaClick: () -> Unit,
    onHijriCalendarClick: () -> Unit,
    onIslamicNamesClick: () -> Unit,
    onTasbeehClick: () -> Unit,
    onDuasClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "Islamic Tools",
            subtitle = "Calculators & Essential Utilities"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ToolItemCard(
                    title = "Prayer Times",
                    description = "Accurate daily Salah schedules for Fajr, Dhuhr, Asr, Maghrib, and Isha.",
                    icon = Icons.Default.AccessTime,
                    onClick = onPrayerTimesClick,
                    tag = "tool_prayer_times"
                )
            }
            item {
                ToolItemCard(
                    title = "Qibla Direction",
                    description = "Spherical compass bearing pointing towards the Holy Ka'bah in Makkah.",
                    icon = Icons.Default.CompassCalibration,
                    onClick = onQiblaClick,
                    tag = "tool_qibla"
                )
            }
            item {
                ToolItemCard(
                    title = "Islamic Hijri Calendar",
                    description = "Umm al-Qura lunar dates, important Islamic occasions, and Ramadan countdown.",
                    icon = Icons.Default.CalendarMonth,
                    onClick = onHijriCalendarClick,
                    tag = "tool_hijri"
                )
            }
            item {
                ToolItemCard(
                    title = "Islamic Names Directory",
                    description = "Search authentic Muslim boy and girl names with original Arabic and rich meanings.",
                    icon = Icons.Default.Person,
                    onClick = onIslamicNamesClick,
                    tag = "tool_names"
                )
            }
            item {
                ToolItemCard(
                    title = "Digital Tasbeeh Counter",
                    description = "Track daily Dhikr with haptic feedback, preset praises, and lifetime stats.",
                    icon = Icons.Default.Spa,
                    onClick = onTasbeehClick,
                    tag = "tool_tasbeeh"
                )
            }
            item {
                ToolItemCard(
                    title = "Islamic Duas & Supplications",
                    description = "Hisnul Muslim authentic Duas for morning, evening, protection, and prayers.",
                    icon = Icons.Default.VolunteerActivism,
                    onClick = onDuasClick,
                    tag = "tool_duas"
                )
            }
            item {
                ToolItemCard(
                    title = "Bookmarks & Reading Progress",
                    description = "View your saved verses and recently read Surah locations.",
                    icon = Icons.Default.Bookmark,
                    onClick = onBookmarksClick,
                    tag = "tool_bookmarks"
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ToolItemCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    tag: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .testTag(tag),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(52.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Open",
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
