package com.example.data.repository

import com.example.data.local.TasbeehDao
import com.example.data.local.TasbeehSessionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TasbeehRepository(
    private val tasbeehDao: TasbeehDao
) {
    fun getAllSessions(): Flow<List<TasbeehSessionEntity>> = tasbeehDao.getAllSessions()

    fun getTotalCount(): Flow<Int?> = tasbeehDao.getTotalLifetimeCount()

    suspend fun saveSession(dhikrName: String, count: Int, target: Int) = withContext(Dispatchers.IO) {
        tasbeehDao.insertSession(
            TasbeehSessionEntity(
                dhikrName = dhikrName,
                count = count,
                target = target
            )
        )
    }

    suspend fun clearHistory() = withContext(Dispatchers.IO) {
        tasbeehDao.clearHistory()
    }
}
