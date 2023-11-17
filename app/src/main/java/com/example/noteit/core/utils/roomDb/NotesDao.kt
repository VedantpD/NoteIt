package com.example.noteit.core.utils.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.noteit.model.Note


// YourDao.kt
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Query("SELECT * FROM table_two WHERE id = :itemId")
    suspend fun getIdData(itemId : Long): Note
}