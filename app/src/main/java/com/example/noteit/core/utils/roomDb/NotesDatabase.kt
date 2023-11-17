package com.example.noteit.core.utils.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteit.core.utils.StringListConverter
import com.example.noteit.model.Note

// NotesDatabase.kt
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "database_two"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}