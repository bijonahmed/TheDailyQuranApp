package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuranDao {
    // Bookmarks
    @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity): Long

    @Query("DELETE FROM bookmarks WHERE surahNumber = :surahNumber AND ayahNumber = :ayahNumber")
    suspend fun deleteBookmark(surahNumber: Int, ayahNumber: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE surahNumber = :surahNumber AND ayahNumber = :ayahNumber)")
    fun isBookmarked(surahNumber: Int, ayahNumber: Int): Flow<Boolean>

    // Last Read
    @Query("SELECT * FROM last_read WHERE id = 1 LIMIT 1")
    fun getLastRead(): Flow<LastReadEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLastRead(lastRead: LastReadEntity)

    // Favorites
    @Query("SELECT * FROM favorites WHERE category = :category ORDER BY timestamp DESC")
    fun getFavoritesByCategory(category: String): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity): Long

    @Query("DELETE FROM favorites WHERE category = :category AND itemId = :itemId")
    suspend fun deleteFavorite(category: String, itemId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE category = :category AND itemId = :itemId)")
    fun isFavorite(category: String, itemId: String): Flow<Boolean>
}

@Dao
interface TasbeehDao {
    @Query("SELECT * FROM tasbeeh_sessions ORDER BY timestamp DESC LIMIT 50")
    fun getAllSessions(): Flow<List<TasbeehSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: TasbeehSessionEntity): Long

    @Query("SELECT SUM(count) FROM tasbeeh_sessions")
    fun getTotalLifetimeCount(): Flow<Int?>

    @Query("DELETE FROM tasbeeh_sessions")
    suspend fun clearHistory()
}
