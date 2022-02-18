package com.github.akrucan.encepence.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.akrucan.encepence.presentation.screens.home.components.HomeBoardsColumn
import com.github.akrucan.encepence.presentation.screens.home.components.HomeScaffold
import com.github.akrucan.encepence.presentation.screens.home.components.HomeTextField
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state
    HomeScaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            HomeTextField(
                input = state.boardName,
                onValueChange = { viewModel.emit(HomeScreenEvent.NameChanged(it)) },
                onDone = { viewModel.emit(HomeScreenEvent.BoardAdded) }
            )
            HomeBoardsColumn(
                boards = state.boards,
                onDelete = { viewModel.emit(HomeScreenEvent.BoardDeleted(it)) }
            )
        }
    }
}
