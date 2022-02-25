package com.github.akrucan.encepence.data.dao

import androidx.room.*
import com.github.akrucan.encepence.data.entity.Board
import com.github.akrucan.encepence.data.entity.BoardWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardDao {
    @Query("select * from boards")
    fun getAllBoards(): Flow<List<Board>>

    @Insert
    suspend fun addBoard(board: Board)

    @Delete
    suspend fun deleteBoard(board: Board)

    @Transaction
    @Query("select * from boards where id=:id")
    fun getBoardWithTasks(id: Long): Flow<BoardWithTasks>
}