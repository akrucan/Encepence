package com.github.akrucan.encepence.presentation.screens.board

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.akrucan.encepence.domain.EncepenceRepository
import com.github.akrucan.encepence.presentation.screens.destinations.BoardScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BoardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: EncepenceRepository
) : ViewModel() {
    private val navArgs: BoardScreenNavArgs = BoardScreenDestination.argsFrom(savedStateHandle)

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent>
        get() = _eventFlow

    private val _state: MutableState<BoardState?> = mutableStateOf(null)
    val state: State<BoardState?>
        get() = _state

    init {
        repository.getBoardWithTasks(navArgs.boardId).onEach {
            when (_state.value) {
                null -> {
                    _state.value = BoardState(
                        board = it.board,
                        tasks = it.tasks,
                        nameEditable = it.board.name
                    )
                }
                else -> {
                    _state.value = _state.value?.copy(
                        board = it.board,
                        tasks = it.tasks
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun editBoardName(name: String) {
        _state.value = _state.value!!.copy(
            nameEditable = name
        )
    }

    private fun deleteBoard() {
        viewModelScope.launch {
            repository.deleteBoard(state.value!!.board)
            _eventFlow.emit(UiEvent.NavigateBack)
        }
    }

    private fun saveBoardName() {
        val state = _state.value!!
        viewModelScope.launch {
            repository.addBoard(state.board.copy(name = state.nameEditable))
        }
    }

    fun emit(event: BoardScreenEvent) {
        when (event) {
            is BoardScreenEvent.BoardNameChanged -> editBoardName(event.value)
            BoardScreenEvent.BoardNameSaved -> saveBoardName()
            BoardScreenEvent.BoardDeleted -> deleteBoard()
            is BoardScreenEvent.BoardContentChanged -> TODO()
        }
    }

    sealed class UiEvent {
        object NavigateBack : UiEvent()
    }
}