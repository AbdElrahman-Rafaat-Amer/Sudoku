package com.abdelrahman.raafat.sudoku

import kotlin.math.sqrt

fun Int.isPerfectSquare(): Boolean {
    if (this < 0) {
        println("This number is negative")
        return false
    }
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
