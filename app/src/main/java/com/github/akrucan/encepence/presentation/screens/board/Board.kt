package com.github.akrucan.encepence.presentation.screens.board

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.akrucan.encepence.presentation.screens.board.compoments.BoardTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@Composable
@Destination(
    navArgsDelegate = BoardScreenNavArgs::class
)
fun BoardScreen(
    viewModel: BoardViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                BoardViewModel.UiEvent.NavigateBack -> navigator.navigateUp()
            }
        }
    }
    val state by viewModel.state
    @Suppress("name_shadowing")
    state?.let { state ->
        Scaffold(
            topBar = {
                BoardTopBar(
                    board = state.board,
                    onDelete = { viewModel.emit(event = BoardScreenEvent.BoardDeleted) },
                    onDone = { viewModel.emit(event = BoardScreenEvent.BoardNameSaved) },
                    onNameChanged = { viewModel.emit(event = BoardScreenEvent.BoardNameChanged(it)) }
                )
            }
        ){

        }
    }
}