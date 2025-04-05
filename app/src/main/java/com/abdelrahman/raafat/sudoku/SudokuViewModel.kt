package com.abdelrahman.raafat.sudoku


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.sqrt

class SudokuViewModel() : ViewModel() {

    private val emptyCell = "-"

    val gameType = listOf(
        "3x3" to 3,
        "6x6" to 6,
        "8x8" to 8,
        "9x9" to 9,
        "10x10" to 10,
        "12x12" to 12,
        "14x14" to 14
    )

    private val _boardSize = MutableStateFlow(gameType[3].second)
    val boardSize: StateFlow<Int> = _boardSize


    private val _isGamedEnded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isGamedEnded: StateFlow<Boolean> = _isGamedEnded

    private val _selectedIndex: MutableStateFlow<Pair<Int, Int>?> =
        MutableStateFlow<Pair<Int, Int>?>(null)
    val selectedIndex: StateFlow<Pair<Int, Int>?> = _selectedIndex

    private val _boardState: MutableStateFlow<MutableList<SnapshotStateList<String>>> =
        MutableStateFlow(MutableList(boardSize.value) {
            mutableStateListOf(*Array(boardSize.value) { "-" })
        })
    val boardState: StateFlow<List<SnapshotStateList<String>>> = _boardState


    fun isValidSudoku(sudokuBoard: List<List<String>>): Boolean {
        if (sudokuBoard.isEmpty()) return false

        if (!sudokuBoard.size.isPerfectSquare()
            && sudokuBoard.size % 2 != 0
            && sudokuBoard.size != 3
        ) {
            return false
        }

        val allowedRange = 1..sudokuBoard.size

        sudokuBoard.forEach { row ->
            if (row.size != sudokuBoard.size) {
                return false
            }

            row.forEach { cell ->
                val isValidSudokuCell = cell.isValidSudokuCell(allowedRange)
                if (!isValidSudokuCell) return false

                if (row.toList().hasRepeatedNumbers()) {
                    return false
                }
            }
        }


        val columns = Array(sudokuBoard.size) { i ->
            Array(sudokuBoard.size) { j -> sudokuBoard[j][i] }
        }

        columns.forEach { column ->
            if (column.toList().hasRepeatedNumbers()) {
                return false
            }
        }

        val grids = extractSubGrids(sudokuBoard)
        grids.forEach { grid ->
            if (grid.hasRepeatedNumbers()) {
                return false
            }
        }

        _isGamedEnded.value = isBoardFilled(sudokuBoard)

        return true
    }

    private fun extractSubGrids(grid: List<List<String>>): List<List<String>> {
        val gridSize = grid.size
        val (subGridRow, subGridColumn) = getSubGridRowColumn(gridSize)

        val subGrids = mutableListOf<List<String>>()

        for (rowStart in 0..<gridSize step subGridRow) {
            for (colStart in 0..<gridSize step subGridColumn) {
                val subgrid = mutableListOf<String>()

                for (row in rowStart..<rowStart + subGridRow) {
                    for (col in colStart..<colStart + subGridColumn) {
                        subgrid.add(grid[row][col])
                    }
                }
                subGrids.add(subgrid)
            }
        }
        return subGrids
    }

    fun getSubGridRowColumn(gridSize: Int): Pair<Int, Int> {
        var subGridRow = 0
        var subGridColumn = 0
        if (gridSize.isPerfectSquare()) {
            val sqrtValue = sqrt(gridSize.toDouble()).toInt()
            subGridRow = sqrtValue
            subGridColumn = sqrtValue
        } else {
            for (i in 2..gridSize) {
                if (gridSize % i == 0) {
                    subGridRow = i
                    subGridColumn = gridSize / subGridRow
                    break
                }
            }
        }
        return Pair(subGridRow, subGridColumn)
    }

    private fun isBoardFilled(lists: List<List<String>>): Boolean {
        val hasEmptyCells = lists.any { row ->
            row.any { it == emptyCell }
        }
        return !hasEmptyCells
    }

    fun drawNewGame(i: Int) {
        _boardSize.value = gameType[i].second
        _boardState.value = MutableList(boardSize.value) {
            mutableStateListOf(*Array(boardSize.value) { "-" })
        }
        _selectedIndex.value = null
    }

    fun updateSelectedIndex(pair: Pair<Int, Int>?) {
        _selectedIndex.value = pair
    }

    fun updateBoard(pair: Pair<Int, Int>, value: String) {
        val row = pair.first
        val col = pair.second
        _boardState.value[row][col] = value
    }

}