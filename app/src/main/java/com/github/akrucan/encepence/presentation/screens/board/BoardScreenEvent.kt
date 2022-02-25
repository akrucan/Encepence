package com.github.akrucan.encepence.presentation.screens.board

sealed class BoardScreenEvent {
    data class BoardNameChanged(val value: String) : BoardScreenEvent()
    object BoardNameSaved : BoardScreenEvent()
    object BoardDeleted : BoardScreenEvent()
    data class BoardContentChanged(val value: String): BoardScreenEvent()
}