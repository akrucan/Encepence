package com.github.akrucan.encepence.presentation.screens.board

import com.github.akrucan.encepence.data.entity.Board
import com.github.akrucan.encepence.data.entity.Task

data class BoardState(
    val board: Board,
    val tasks: List<Task>,
    val nameEditable: String
)