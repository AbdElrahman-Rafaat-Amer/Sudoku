package com.abdelrahman.raafat.sudoku.data

import com.abdelrahman.raafat.sudoku.SudokuViewModel
import com.abdelrahman.raafat.sudoku.isPerfectSquare
import kotlin.math.sqrt


fun main (){
  val board44 =  generateSudoku(4, Difficulty.EASY)
    board44.forEach {
        println("board----> $it")
    }

   val isValidSudokuboard44 =  SudokuViewModel().isValidSudoku(board44.toList())
    println("isValidSudokuboard44----> $isValidSudokuboard44")


    val board66 =  generateSudoku(6, Difficulty.EASY)
    board66.forEach {
        println("board----> $it")
    }

    val isValidSudokuboard66 =  SudokuViewModel().isValidSudoku(board66.toList())
    println("isValidSudokuboard66----> $isValidSudokuboard66")

//                        board----> [4, 1, 3, 2]
//                        board----> [2, 3, 1, 4]
//                        board----> [1, 4, 2, 3]
//                        board----> [3, 2, 4, 1]
}
enum class Difficulty {
    EASY, MEDIUM, HARD
}

fun generateSudoku(size: Int, difficulty: Difficulty): List<List<String>> {
    val board = Array(size) { Array(size) { "-" } }

    // Valid values for the board
    val values = (1..size).map { it.toString() }

    // Backtracking solver to fill the board
    fun isValid(r: Int, c: Int, v: String): Boolean {
        for (i in 0 until size) {
            if (board[r][i] == v || board[i][c] == v) return false
        }

        val boxSize = Math.sqrt(size.toDouble()).toInt()
//        val boxRow = r / boxSize * boxSize
//        val boxCol = c / boxSize * boxSize
        val (boxRow, boxCol) = getSubGridRowColumn(size)

        for (i in boxRow until boxRow + boxSize) {
            for (j in boxCol until boxCol + boxSize) {
                if (board[i][j] == v) return false
            }
        }

        return true
    }

    fun fillBoard(row: Int = 0, col: Int = 0): Boolean {
        if (row == size) return true
        val nextRow = if (col == size - 1) row + 1 else row
        val nextCol = if (col == size - 1) 0 else col + 1

        val shuffledValues = values.shuffled()
        for (v in shuffledValues) {
            if (isValid(row, col, v)) {
                board[row][col] = v
                if (fillBoard(nextRow, nextCol)) return true
                board[row][col] = "-"
            }
        }
        return false
    }

    fillBoard()

    // Calculate how many cells to keep
    val totalCells = size * size
    val cellsToKeep = when (difficulty) {
        Difficulty.EASY -> (totalCells * 0.5).toInt()
        Difficulty.MEDIUM -> {
            var cellsNumber = (totalCells * 0.33).toInt()
            cellsNumber.coerceIn(cellsNumber - size/2, cellsNumber)
        }
        Difficulty.HARD -> size
    }

    // Create masked version
    val allPositions = (0 until totalCells).shuffled()
    val positionsToKeep = allPositions.take(cellsToKeep).toSet()

    val maskedBoard = List(size) { row ->
        List(size) { col ->
            if ((row * size + col) in positionsToKeep) board[row][col] else "-"
        }
    }

    return maskedBoard
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