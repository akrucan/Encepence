package com.github.akrucan.encepence.data

import com.github.akrucan.encepence.data.entity.Board
import com.github.akrucan.encepence.data.entity.BoardWithTasks
import com.github.akrucan.encepence.domain.EncepenceRepository
import kotlinx.coroutines.flow.Flow

class EncepenceRepositoryImpl(private val db: EncepenceDatabase) : EncepenceRepository {
    override fun getAllBoards(): Flow<List<Board>> {
        return db.boardDao.getAllBoards()
    }
    override suspend fun addBoard(board: Board){
        db.boardDao.addBoard(board)
    }

    override suspend fun deleteBoard(board: Board) {
        db.boardDao.deleteBoard(board = board)
    }

    override fun getBoardWithTasks(id: Long): Flow<BoardWithTasks> {
        return db.boardDao.getBoardWithTasks(id)
    }
}