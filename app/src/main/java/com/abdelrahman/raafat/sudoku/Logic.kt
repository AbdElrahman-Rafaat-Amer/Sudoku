package com.abdelrahman.raafat.sudoku

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlin.math.sqrt


fun isValidSudoku(sudokuBoard: List<SnapshotStateList<String>>): Boolean {
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

    return true
}

fun extractSubGrids(grid: List<SnapshotStateList<String>>): List<List<String>> {
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

fun Int.isPerfectSquare(): Boolean {
    if (this < 0) return false
    val sqrtValue = sqrt(this.toDouble()).toInt()
    return sqrtValue * sqrtValue == this
}

fun <T> Collection<T>.hasRepeatedNumbers(): Boolean {
    val emptyCell = "-"
    val rowWithoutEmptyChars = this.filter { it != emptyCell }
    return rowWithoutEmptyChars.distinct().size != rowWithoutEmptyChars.size
}

fun String.isValidSudokuCell(allowedRange: IntRange): Boolean {
    val emptyChar = '-'
    if (this.singleOrNull() == emptyChar) {
        return true
    }

    val number = this.toIntOrNull() ?: return false

    return number in allowedRange
}