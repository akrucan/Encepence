package com.github.akrucan.encepence.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boards")
data class Board(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String
)
