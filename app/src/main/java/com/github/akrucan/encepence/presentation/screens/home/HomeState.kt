package com.github.akrucan.encepence.presentation.screens.home

import com.github.akrucan.encepence.data.entity.Board

data class HomeState(
    val boards: List<Board> = emptyList(),
    val boardName: String = ""
)
