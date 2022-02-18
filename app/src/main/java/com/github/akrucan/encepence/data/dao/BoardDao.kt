package com.github.akrucan.encepence.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.akrucan.encepence.data.entity.Board
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardDao {
    @Query("select * from boards")
    fun getAllBoards(): Flow<List<Board>>

    @Insert
    suspend fun addBoard(board: Board)

    @Delete
    suspend fun deleteBoard(board: Board)
}