package com.abdelrahman.raafat.sudoku

import org.junit.Assert.assertEquals
import org.junit.Test

class SudokuValidatorTest {

    private val viewModel = SudokuViewModel()

    @Test
    fun validSudoku_9x9FullyCorrect() {
        val input = listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        )
        assertEquals(true, viewModel.isValidSudoku(input))
    }

    @Test
    fun validSudoku_3x3Empty() {
        val input = listOf(
            listOf("-", "-", "-"),
            listOf("-", "-", "-"),
            listOf("-", "-", "-")
        )
        assertEquals(true, viewModel.isValidSudoku(input))
    }

    @Test
    fun validSudoku_10x10WithEmpty() {
        val input = listOf(
            listOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
            listOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
            listOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-"),
            listOf("-", "-", "-", "-", "6", "-", "-", "-", "3", "-"),
            listOf("4", "-", "-", "8", "-", "3", "-", "-", "-", "1"),
            listOf("7", "-", "-", "-", "2", "-", "-", "-", "-", "6"),
            listOf("-", "6", "-", "-", "-", "-", "2", "8", "-", "-"),
            listOf("-", "-", "-", "4", "1", "9", "-", "-", "5", "-"),
            listOf("-", "-", "-", "-", "8", "-", "-", "7", "9", "-"),
            listOf("-", "-", "-", "-", "-", "-", "-", "-", "-", "10")
        )
        assertEquals(true, viewModel.isValidSudoku(input))
    }

    @Test
    fun invalidSudoku_EmptyBoard() {
        assertEquals(false, viewModel.isValidSudoku(emptyList()))
    }

    @Test
    fun invalidSudoku_NotPerfectSquare() {
        val input = listOf(listOf(" ", " ", " "))
        assertEquals(false, viewModel.isValidSudoku(input))
    }

    @Test
    fun invalidSudoku_NonNumericChars() {
        val input = listOf(
            listOf("a", "a", "a"),
            listOf("a", "a", "a"),
            listOf("a", "a", "a")
        )
        assertEquals(false, viewModel.isValidSudoku(input))
    }

    @Test
    fun invalidSudoku_RepeatedInRow() {
        val input = listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "5"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "8", "3", "4", "2", "5", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "-"),
            listOf("3", "4", "5", "2", "8", "6", "1", "7", "9")
        )
        assertEquals(false, viewModel.isValidSudoku(input))
    }

    @Test
    fun invalidSudoku_RepeatedInColumn() {
        val input = listOf(
            listOf("5", "3", "-", "-", "7", "-", "-", "-", "-", "-"),
            listOf("6", "-", "-", "1", "9", "5", "-", "-", "-", "-"),
            listOf("-", "9", "8", "-", "-", "-", "-", "6", "-", "-"),
            listOf("8", "-", "-", "-", "6", "-", "-", "-", "3", "-"),
            listOf("4", "-", "-", "8", "-", "3", "-", "-", "-", "1"),
            listOf("7", "-", "-", "-", "2", "-", "-", "-", "-", "6"),
            listOf("-", "6", "-", "-", "-", "-", "2", "8", "-", "-"),
            listOf("-", "-", "-", "4", "1", "9", "-", "-", "5", "-"),
            listOf("-", "-", "-", "-", "8", "-", "-", "7", "9", "-"),
            listOf("5", "-", "-", "-", "-", "-", "-", "-", "-", "-")
        )
        assertEquals(false, viewModel.isValidSudoku(input))
    }

    @Test
    fun invalidSudoku_RepeatedInBox() {
        val input = listOf(
            listOf("5", "3", "4", "6", "7", "8", "9", "1", "2"),
            listOf("6", "7", "2", "1", "9", "5", "3", "4", "8"),
            listOf("1", "9", "5", "3", "4", "2", "_", "6", "7"),
            listOf("8", "5", "9", "7", "6", "1", "4", "2", "3"),
            listOf("4", "2", "6", "8", "5", "3", "7", "9", "1"),
            listOf("7", "1", "3", "9", "2", "4", "8", "5", "6"),
            listOf("9", "6", "1", "5", "3", "7", "2", "8", "4"),
            listOf("2", "8", "7", "4", "1", "9", "6", "3", "5"),
            listOf("3", "4", "_", "2", "8", "6", "1", "7", "9")
        )
        assertEquals(false, viewModel.isValidSudoku(input))
    }

    @Test
    fun invalidSudoku_OutOfRange() {
        val input = listOf(
            listOf("1", "2", "3"),
            listOf("3", "4", "2"),
            listOf("2", "3", "1")
        )
        assertEquals(false, viewModel.isValidSudoku(input))
    }
}
