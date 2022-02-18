package com.github.akrucan.encepence.presentation.screens.home

import com.github.akrucan.encepence.data.entity.Board

sealed class HomeScreenEvent {
    data class NameChanged(val value: String) : HomeScreenEvent()
    data class BoardDeleted(val value: Board) : HomeScreenEvent()
    object BoardAdded : HomeScreenEvent()

}
