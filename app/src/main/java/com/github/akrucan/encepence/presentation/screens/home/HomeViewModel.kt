package com.github.akrucan.encepence.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.akrucan.encepence.data.entity.Board
import com.github.akrucan.encepence.domain.EncepenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: EncepenceRepository
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState>
        get() = _state

    init {
        repository.getAllBoards().onEach {
            _state.value = _state.value.copy(
                boards = it
            )
        }.launchIn(viewModelScope)
    }

    private fun addBoard() {
        viewModelScope.launch {
            val board = Board(name = state.value.boardName)
            repository.addBoard(board = board)
            _state.value = state.value.copy(boardName = "")
        }
    }

    private fun deleteBoard(board: Board) {
        viewModelScope.launch {
            repository.deleteBoard(board)
        }
    }

    private fun onNameChange(name: String) {
        val name = name.replace("\\s+".toRegex(), " ")
        _state.value = _state.value.copy(
            boardName = name
        )
    }

    fun emit(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.BoardAdded -> addBoard()
            is HomeScreenEvent.BoardDeleted -> deleteBoard(event.value)
            is HomeScreenEvent.NameChanged -> onNameChange(event.value)
        }
    }
}