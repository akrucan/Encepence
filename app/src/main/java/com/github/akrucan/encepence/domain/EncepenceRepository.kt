package com.github.akrucan.encepence.domain

import com.github.akrucan.encepence.data.entity.Board
import kotlinx.coroutines.flow.Flow

interface EncepenceRepository {

    fun getAllBoards(): Flow<List<Board>>

    suspend fun addBoard(board: Board)

    suspend fun deleteBoard(board: Board)
}
