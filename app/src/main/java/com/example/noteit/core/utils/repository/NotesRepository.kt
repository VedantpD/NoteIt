package com.example.noteit.core.utils.repository

import com.example.noteit.core.utils.roomDb.NotesDao
import com.example.noteit.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// NotesRepository.kt
class NotesRepository(private val noteDao: NotesDao) {

    suspend fun insertData(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    suspend fun getIdData(itemId : Long): Note {
        return withContext(Dispatchers.IO) {
            noteDao.getIdData(itemId)
        }
    }
}