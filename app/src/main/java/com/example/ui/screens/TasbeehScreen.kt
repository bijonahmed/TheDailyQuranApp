package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.DailyQuranTopBar
import com.example.ui.theme.Emerald800
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldRoyal
import com.example.ui.viewmodel.TasbeehViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TasbeehScreen(
    viewModel: TasbeehViewModel,
    onBackClick: () -> Unit = {},
    showBackButton: Boolean = false,
    modifier: Modifier = Modifier
) {
    val count by viewModel.count.collectAsState()
    val target by viewModel.target.collectAsState()
    val currentDhikr by viewModel.currentDhikr.collectAsState()
    val sessions by viewModel.sessions.collectAsState()
    val totalLifetime by viewModel.totalLifetimeCount.collectAsState()
    val hapticsEnabled by viewModel.userPrefs.tasbeehHaptics.collectAsState()

    var showResetDialog by remember { mutableStateOf(false) }
    var showHistoryDialog by remember { mutableStateOf(false) }

    val progress = if (target > 0) (count % target).toFloat() / target else 0f
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "tasbeeh_progress")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = "Digital Tasbeeh",
            subtitle = "Daily Remembrance of Allah",
            showBackButton = showBackButton,
            onBackClick = onBackClick,
            actions = {
                IconButton(
                    onClick = { viewModel.userPrefs.setTasbeehHaptics(!hapticsEnabled) },
                    modifier = Modifier.testTag("tasbeeh_haptics_toggle")
                ) {
                    Icon(
                        imageVector = Icons.Default.Vibration,
                        contentDescription = "Toggle Vibration",
                        tint = if (hapticsEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    )
                }
                IconButton(
                    onClick = { showHistoryDialog = true },
                    modifier = Modifier.testTag("tasbeeh_history_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = "Tasbeeh History",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Dhikr Selector Chips
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.dhikrPresets) { dhikr ->
                        FilterChip(
                            selected = currentDhikr == dhikr,
                            onClick = { viewModel.setDhikr(dhikr) },
                            label = {
                                Text(
                                    text = dhikr.substringBefore(" ("),
                                    fontSize = 13.sp
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }

            // Target selector
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Target: ",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    listOf(33, 99, 100, 1000).forEach { targetOption ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (target == targetOption) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { viewModel.setTarget(targetOption) }
                        ) {
                            Text(
                                text = targetOption.toString(),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (target == targetOption) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }
                    }
                }
            }

            // Current Dhikr Arabic Banner
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentDhikr.substringAfter("(").substringBefore(")"),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentDhikr.substringBefore(" ("),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }

            // Main Large Tap Counter
            item {
                Box(
                    modifier = Modifier
                        .size(260.dp)
                        .clip(CircleShape)
                        .clickable { viewModel.increment() }
                        .testTag("tasbeeh_counter_tap_area"),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer Progress Ring
                    CircularProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier.size(250.dp),
                        color = IslamicGold,
                        strokeWidth = 10.dp,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )

                    // Inner Tap Sphere with Smart Luxury Gradient & Gold Accent Ring
                    Surface(
                        modifier = Modifier
                            .size(220.dp)
                            .clip(CircleShape),
                        shape = CircleShape,
                        border = androidx.compose.foundation.BorderStroke(2.dp, IslamicGoldRoyal.copy(alpha = 0.6f)),
                        tonalElevation = 8.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(com.example.ui.theme.SmartGradients.EmeraldLuxury),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "$count",
                                    style = MaterialTheme.typography.displayMedium.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                )
                                Text(
                                    text = "of $target",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = Color(0xFFD4EDDA)
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "TAP ANYWHERE",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        letterSpacing = 1.4.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = IslamicGoldRoyal
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Controls: Reset & Stats
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { showResetDialog = true },
                        modifier = Modifier.testTag("tasbeeh_reset_button")
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reset")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Reset")
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Total Count",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                            Text(
                                text = "${totalLifetime ?: 0}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }

    // Reset confirmation dialog
    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Tasbeeh Counter?") },
            text = { Text("This will save your current progress ($count) to history and reset the counter to zero.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.reset()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // History dialog
    if (showHistoryDialog) {
        AlertDialog(
            onDismissRequest = { showHistoryDialog = false },
            title = { Text("Tasbeeh History") },
            text = {
                if (sessions.isEmpty()) {
                    Text("No saved Tasbeeh sessions yet. Start reciting to track your dhikr.")
                } else {
                    LazyColumn(modifier = Modifier.height(260.dp)) {
                        items(sessions) { session ->
                            val dateStr = SimpleDateFormat("MMM d, h:mm a", Locale.ENGLISH).format(Date(session.timestamp))
                            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = session.dhikrName.substringBefore(" ("),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = "${session.count} counts",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Text(
                                    text = dateStr,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showHistoryDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}
