package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val surahNumber: Int,
    val surahNameEnglish: String,
    val surahNameArabic: String,
    val ayahNumber: Int,
    val arabicText: String,
    val translationText: String,
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "last_read")
data class LastReadEntity(
    @PrimaryKey
    val id: Int = 1,
    val surahNumber: Int,
    val surahNameEnglish: String,
    val surahNameArabic: String,
    val ayahNumber: Int,
    val totalAyahs: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "tasbeeh_sessions")
data class TasbeehSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dhikrName: String,
    val count: Int,
    val target: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val category: String, // "quran", "hadith", "dua", "name"
    val itemId: String,
    val title: String,
    val subtitle: String,
    val arabicText: String = "",
    val translationText: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
