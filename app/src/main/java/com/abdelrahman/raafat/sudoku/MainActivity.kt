package com.abdelrahman.raafat.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdelrahman.raafat.sudoku.ui.theme.SudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SudokuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SudokuBoard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SudokuBoard(modifier: Modifier = Modifier) {
    val gameType = listOf(
        "3x3" to 3,
        "6x6" to 6,
        "8x8" to 8,
        "9x9" to 9,
        "10x10" to 10,
        "12x12" to 12,
        "14x14" to 14
    )
    var selectedGameTypeIndex by remember { mutableIntStateOf(3) }
    var boardSize by remember { mutableIntStateOf(gameType[selectedGameTypeIndex].second) }

    val boardState = remember(boardSize) {
        List(boardSize) { mutableStateListOf(*Array(boardSize) { "-" }) }
    }


    var selectedIndex by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var isValidSudoku by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sudoku Board",
        )

        Spacer(Modifier.height(30.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (i in gameType.indices) {
                Button(
                    onClick = {
                        selectedGameTypeIndex = i
                        boardSize = gameType[selectedGameTypeIndex].second
                        selectedIndex = null
                    },
                    content = {
                        Text(text = gameType[i].first)
                    },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 75.dp, minHeight = 50.dp)
                        .padding(end = 5.dp)
                )
            }
        }

        Spacer(Modifier.height(50.dp))

        AnimatedVisibility(!isValidSudoku) {
            Column {
                Text(
                    text = "Error: Invalid Sudoku",
                )
                Spacer(Modifier.height(50.dp))
            }
        }

        val (rowSubGrid, columnSubGrid) = getSubGridRowColumn(boardSize)

        for (i in 0..<boardSize) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(color = Color.LightGray)
            ) {

                for (j in 0..<boardSize) {
                    Row(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        selectedIndex = if (selectedIndex == Pair(i, j)) {
                                            null
                                        } else {
                                            Pair(i, j)
                                        }
                                    }
                                )
                                .weight(1f)
                                .size(50.dp)
                                .background(
                                    color = if (selectedIndex == Pair(i, j)) {
                                        Color.DarkGray
                                    } else {
                                        Color.LightGray
                                    }
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = boardState[i][j], color = Color.Black)
                        }
                        if (j < boardSize - 1) {
                            val (color, thickness) = if ((j + 1) % columnSubGrid == 0){
                                Color.Red to 3.dp
                            } else{
                                Color.Black to 1.dp
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
                val (color, thickness) = if ((i + 1) % rowSubGrid == 0){
                    Color.Red to 3.dp
                } else{
                    Color.Black to 1.dp
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


        Spacer(Modifier.height(100.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (i in 1..boardSize) {
                Button(
                    onClick = {
                        selectedIndex?.let {
                            boardState[it.first][it.second] = i.toString()
                            isValidSudoku = isValidSudoku(boardState)
                        }
                    },
                    content = {
                        Text(text = i.toString())
                    },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 75.dp, minHeight = 50.dp)
                        .padding(end = 5.dp)
                )
            }
            Button(
                onClick = {
                    selectedIndex?.let {
                        boardState[it.first][it.second] = "-"
                        isValidSudoku = isValidSudoku(boardState)
                    }
                },
                content = {
                    Text(text = "Clear")
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 75.dp, minHeight = 50.dp)
                    .padding(end = 5.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SudokuBoardPreview() {
    SudokuTheme {
        SudokuBoard()
    }
}