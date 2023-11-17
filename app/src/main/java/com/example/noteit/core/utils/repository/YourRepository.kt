package com.example.noteit.core.utils.repository

import com.example.noteit.core.utils.roomDb.MainListDao
import com.example.noteit.model.YourEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// YourRepository.kt
class YourRepository(private val yourDao: MainListDao) {

    suspend fun insertData(yourEntity: YourEntity) {
        withContext(Dispatchers.IO) {
            yourDao.insert(yourEntity)
        }
    }

    suspend fun getAllData(): List<YourEntity> {
        return withContext(Dispatchers.IO) {
            yourDao.getAllData()
        }
    }
}
