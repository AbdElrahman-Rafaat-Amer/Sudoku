package com.abdelrahman.raafat.sudoku

import org.junit.Assert.assertEquals
import org.junit.Test

class SudokuValidatorTest {

    @Test
    fun validSudoku_9x9FullyCorrect() {
        val result = SudokuViewModel().isValidSudoku(
            listOf(
                listOf("3", "3", "4", "6", "7", "8", "9", "1", "2"),
                listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
                listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
                listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
                listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
                listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
                listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
                listOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
                listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
            )
        )
        assertEquals(true, result)
    }

    @Test
    fun invalidSudoku_EmptyBoard() {
        val result = SudokuViewModel().isValidSudoku(emptyList())
        assertEquals(false, result)
    }

    // Add more @Test fun for each scenario...
}
