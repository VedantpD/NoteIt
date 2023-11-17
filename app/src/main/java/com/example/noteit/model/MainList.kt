package com.example.noteit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// YourEntity.kt
@Entity(tableName = "table_one")
data class YourEntity(
    val value: String,
    val type: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
