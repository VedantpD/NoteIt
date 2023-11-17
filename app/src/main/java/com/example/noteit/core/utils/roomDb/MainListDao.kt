package com.example.noteit.core.utils.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.noteit.model.YourEntity

// YourDao.kt
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface MainListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(yourEntity: YourEntity)

    @Query("SELECT * FROM table_one")
    suspend fun getAllData(): List<YourEntity>
}
