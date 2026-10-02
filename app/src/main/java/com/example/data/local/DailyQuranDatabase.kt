package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        BookmarkEntity::class,
        LastReadEntity::class,
        TasbeehSessionEntity::class,
        FavoriteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DailyQuranDatabase : RoomDatabase() {
    abstract fun quranDao(): QuranDao
    abstract fun tasbeehDao(): TasbeehDao

    companion object {
        @Volatile
        private var INSTANCE: DailyQuranDatabase? = null

        fun getDatabase(context: Context): DailyQuranDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DailyQuranDatabase::class.java,
                    "daily_quran_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
