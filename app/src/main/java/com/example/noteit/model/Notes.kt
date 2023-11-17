package com.example.noteit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_two")
data class Note(
    val isChecked : Boolean?,
    val info: MutableList<String>?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)