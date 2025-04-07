package com.abdelrahman.raafat.sudoku

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Board(
    viewModel: SudokuViewModel,
    boardSize: Int,
    selectedIndex: Pair<Int, Int>?,
    boardState: State<List<SnapshotStateList<String>>>
) {
    val (rowSubGrid, columnSubGrid) = viewModel.getSubGridRowColumn(boardSize)

    Card(
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.padding(horizontal = 5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        for (i in 0..<boardSize) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for (j in 0..<boardSize) {
                    Row(modifier = Modifier.weight(1f)) {
                        val (backGroundColor, textColor) = if (selectedIndex == Pair(i, j)) {
                            MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.secondary
                        } else {
                            MaterialTheme.colorScheme.secondary to MaterialTheme.colorScheme.onPrimary
                        }
                        Box(
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        val selectedIndex = if (selectedIndex == Pair(i, j)) {
                                            null
                                        } else {
                                            Pair(i, j)
                                        }
                                        viewModel.updateSelectedIndex(selectedIndex)
                                    }
                                )
                                .weight(1f)
                                .size(50.dp)
                                .background(color = backGroundColor),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = boardState.value[i][j], color = textColor)
                        }
                        if (j < boardSize - 1) {
                            val (color, thickness) = if ((j + 1) % columnSubGrid == 0) {
                                MaterialTheme.colorScheme.tertiary to 4.dp
                            } else {
                                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f) to 1.dp
                            }

                            VerticalDivider(
                                modifier = Modifier.height(50.dp),
                                color = color,
                                thickness = thickness
                            )
                        }
                    }
                }
            }
            if (i < boardSize - 1) {
                val (color, thickness) = if ((i + 1) % rowSubGrid == 0) {
                    MaterialTheme.colorScheme.tertiary to 4.dp
                } else {
                    MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f) to 2.dp
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    color = color,
                    thickness = thickness
                )
            }

        }
    }
}