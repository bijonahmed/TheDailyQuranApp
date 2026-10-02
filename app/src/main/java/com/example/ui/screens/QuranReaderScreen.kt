package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Ayah
import com.example.ui.components.DailyQuranTopBar
import com.example.ui.theme.Emerald800
import com.example.ui.theme.IslamicGold
import com.example.ui.viewmodel.QuranViewModel

@Composable
fun QuranReaderScreen(
    surahNumber: Int,
    viewModel: QuranViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentSurah by viewModel.currentSurah.collectAsState()
    val verses by viewModel.verses.collectAsState()
    val isLoading by viewModel.isLoadingVerses.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val playerState by viewModel.audioPlayer.state.collectAsState()

    val arabicFontSize by viewModel.userPrefs.arabicFontSize.collectAsState()
    val translationFontSize by viewModel.userPrefs.translationFontSize.collectAsState()
    val showTransliteration by viewModel.userPrefs.showTransliteration.collectAsState()

    var showFontSizeDialog by remember { mutableStateOf(false) }

    LaunchedEffect(surahNumber) {
        viewModel.openSurah(surahNumber)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DailyQuranTopBar(
            title = currentSurah?.nameEnglish ?: "Surah $surahNumber",
            subtitle = "${currentSurah?.nameArabic ?: ""} • ${currentSurah?.numberOfAyahs ?: ""} Ayahs",
            showBackButton = true,
            onBackClick = onBackClick,
            actions = {
                IconButton(
                    onClick = { showFontSizeDialog = true },
                    modifier = Modifier.testTag("reader_font_size_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.FormatSize,
                        contentDescription = "Adjust font size",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )

        if (isLoading && verses.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Surah Header Banner
                item {
                    currentSurah?.let { surah ->
                        SurahHeaderBanner(
                            surah = surah,
                            onPlayAll = {
                                if (verses.isNotEmpty()) {
                                    viewModel.playAyahAudio(verses.first())
                                }
                            }
                        )
                    }
                }

                // Bismillah Card (for all surahs except At-Tawbah #9)
                if (surahNumber != 9 && surahNumber != 1) {
                    item {
                        BismillahCard()
                    }
                }

                // Ayahs
                itemsIndexed(verses, key = { _, ayah -> ayah.numberInSurah }) { index, ayah ->
                    val isBookmarked = bookmarks.any { it.surahNumber == surahNumber && it.ayahNumber == ayah.numberInSurah }
                    val isCurrentlyPlaying = playerState.isPlaying &&
                            playerState.currentSurahNumber == surahNumber &&
                            playerState.currentAyahNumber == ayah.numberInSurah

                    AyahCard(
                        ayah = ayah,
                        surahNumber = surahNumber,
                        arabicFontSize = arabicFontSize,
                        translationFontSize = translationFontSize,
                        showTransliteration = showTransliteration,
                        isBookmarked = isBookmarked,
                        isPlaying = isCurrentlyPlaying,
                        onPlayAudio = { viewModel.playAyahAudio(ayah) },
                        onToggleBookmark = { viewModel.toggleBookmark(ayah) },
                        onCopy = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("Quran Ayah", "${ayah.textArabic}\n\n\"${ayah.translationEnglish}\"\n(Quran $surahNumber:${ayah.numberInSurah})")
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Ayah copied to clipboard", Toast.LENGTH_SHORT).show()
                        },
                        onShare = {
                            val intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "${ayah.textArabic}\n\n\"${ayah.translationEnglish}\"\n\n— Holy Quran (Surah ${currentSurah?.nameEnglish} $surahNumber:${ayah.numberInSurah})\nShared via The Daily Quran"
                                )
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(intent, "Share Ayah"))
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }

    // Font Size Adjuster Dialog
    if (showFontSizeDialog) {
        FontSizeDialog(
            arabicSize = arabicFontSize,
            translationSize = translationFontSize,
            onArabicChange = { viewModel.userPrefs.setArabicFontSize(it) },
            onTranslationChange = { viewModel.userPrefs.setTranslationFontSize(it) },
            onDismiss = { showFontSizeDialog = false }
        )
    }
}

@Composable
fun SurahHeaderBanner(
    surah: com.example.data.model.Surah,
    onPlayAll: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Emerald800),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = surah.nameArabic,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = IslamicGold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${surah.number}. ${surah.nameEnglish}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            Text(
                text = "${surah.englishTranslation} • ${surah.revelationType} • ${surah.numberOfAyahs} Verses",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFFD4EDDA)
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = IslamicGold,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onPlayAll() }
                    .testTag("surah_play_all_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Surah Recitation",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Play Surah Recitation",
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

@Composable
fun BismillahCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun AyahCard(
    ayah: Ayah,
    surahNumber: Int,
    arabicFontSize: Float,
    translationFontSize: Float,
    showTransliteration: Boolean,
    isBookmarked: Boolean,
    isPlaying: Boolean,
    onPlayAudio: () -> Unit,
    onToggleBookmark: () -> Unit,
    onCopy: () -> Unit,
    onShare: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isPlaying) Modifier.border(2.dp, IslamicGold, RoundedCornerShape(16.dp))
                else Modifier
            )
            .testTag("ayah_card_${ayah.numberInSurah}"),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Verse Action Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Verse Number Pill
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "${ayah.numberInSurah}",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onPlayAudio,
                        modifier = Modifier.size(32.dp).testTag("ayah_play_${ayah.numberInSurah}")
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play Verse",
                            tint = if (isPlaying) IslamicGold else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = onToggleBookmark,
                        modifier = Modifier.size(32.dp).testTag("ayah_bookmark_${ayah.numberInSurah}")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) IslamicGold else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = onCopy,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = onShare,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Arabic Text
            Text(
                text = ayah.textArabic,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = arabicFontSize.sp,
                    lineHeight = (arabicFontSize * 1.6).sp,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Transliteration
            if (showTransliteration && ayah.transliteration.isNotBlank()) {
                Text(
                    text = ayah.transliteration,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            // Translation Text
            Text(
                text = ayah.translationEnglish,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = translationFontSize.sp,
                    lineHeight = (translationFontSize * 1.45).sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
fun FontSizeDialog(
    arabicSize: Float,
    translationSize: Float,
    onArabicChange: (Float) -> Unit,
    onTranslationChange: (Float) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Adjust Reading Font Size") },
        text = {
            Column {
                Text(
                    text = "Arabic Script Size: ${arabicSize.toInt()} sp",
                    style = MaterialTheme.typography.labelMedium
                )
                Slider(
                    value = arabicSize,
                    onValueChange = onArabicChange,
                    valueRange = 20f..40f,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Translation Size: ${translationSize.toInt()} sp",
                    style = MaterialTheme.typography.labelMedium
                )
                Slider(
                    value = translationSize,
                    onValueChange = onTranslationChange,
                    valueRange = 13f..24f,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Done")
            }
        }
    )
}
