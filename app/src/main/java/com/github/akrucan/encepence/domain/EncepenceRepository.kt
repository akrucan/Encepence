package com.github.akrucan.encepence.domain

import com.github.akrucan.encepence.data.entity.Board
import com.github.akrucan.encepence.data.entity.BoardWithTasks
import kotlinx.coroutines.flow.Flow

interface EncepenceRepository {

    fun getAllBoards(): Flow<List<Board>>

    suspend fun addBoard(board: Board)

    suspend fun deleteBoard(board: Board)

    fun getBoardWithTasks(id: Long): Flow<BoardWithTasks>
}
