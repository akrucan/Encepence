package com.github.akrucan.encepence.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.akrucan.encepence.data.dao.BoardDao
import com.github.akrucan.encepence.data.dao.TaskDao
import com.github.akrucan.encepence.data.entity.Board
import com.github.akrucan.encepence.data.entity.Task

@TypeConverters(BoardTypeConverter::class)
@Database(
    version = 1,
    exportSchema = false,
    entities = [
        Board::class,
        Task::class,
    ]
)


abstract class EncepenceDatabase : RoomDatabase() {

    abstract val boardDao: BoardDao
    abstract val taskDao: TaskDao
}