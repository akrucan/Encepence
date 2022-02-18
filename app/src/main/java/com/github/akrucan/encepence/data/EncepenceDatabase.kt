package com.github.akrucan.encepence.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.akrucan.encepence.data.dao.BoardDao
import com.github.akrucan.encepence.data.entity.Board

@Database(
    version = 1,
    exportSchema = false,
    entities = [Board::class]
)


abstract class EncepenceDatabase : RoomDatabase() {

    abstract val boardDao: BoardDao
}