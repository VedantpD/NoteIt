package com.example.noteit.core.utils.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteit.model.YourEntity

// YourDatabase.kt
@Database(entities = [YourEntity::class], version = 1, exportSchema = false)
abstract class YourDatabase : RoomDatabase() {
    abstract fun yourDao(): MainListDao

    companion object {
        @Volatile
        private var INSTANCE: YourDatabase? = null

        fun getDatabase(context: Context): YourDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YourDatabase::class.java,
                    "database_one"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
