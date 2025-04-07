package com.abdelrahman.raafat.sudoku

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdelrahman.raafat.sudoku.ui.theme.Blue
import com.abdelrahman.raafat.sudoku.ui.theme.SudokuTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SudokuBoard(viewModel: SudokuViewModel, modifier: Modifier = Modifier) {

    val boardSize by viewModel.boardSize.collectAsState()

    val boardState = viewModel.boardState.collectAsState()
    val selectedIndex by viewModel.selectedIndex.collectAsState()
    val isGamedEnded by viewModel.isGamedEnded.collectAsState()

    var isValidSudoku by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Blue,
                        0.4f to Blue,
                        0.4f to MaterialTheme.colorScheme.background,
                        1.0f to MaterialTheme.colorScheme.background
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sudoku_board),
        )

        Spacer(Modifier.height(30.dp))

        //Draw levels
        Levels(viewModel = viewModel)


        Spacer(Modifier.height(50.dp))

        AnimatedVisibility(!isValidSudoku) {
            Column {
                Text(
                    text = "Error: Invalid Sudoku",
                )
                Spacer(Modifier.height(50.dp))
            }
        }

        AnimatedVisibility(isGamedEnded) {
            Column {
                Text(
                    text = "You win, please start a new game to start again",
                )
                Spacer(Modifier.height(50.dp))
            }
        }

        Board(
            viewModel = viewModel,
            boardSize = boardSize,
            selectedIndex = selectedIndex,
            boardState = boardState
        )


        Spacer(Modifier.height(100.dp))

        //Draw numbers
        Numbers(
            boardSize = boardSize,
            onButtonClicked = { cellValue ->
                selectedIndex?.let {
                    viewModel.updateBoard(it, cellValue)
                    isValidSudoku = viewModel.isValidSudoku(boardState.value)
                }
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SudokuBoardPreview() {
    SudokuTheme {
        SudokuBoard(viewModel = SudokuViewModel())
    }
}