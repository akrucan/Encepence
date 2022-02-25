package com.github.akrucan.encepence.data.dao

import androidx.room.*
import com.github.akrucan.encepence.data.entity.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Insert
    @Transaction
    suspend fun addTasks(tasks: List<Task>)
}