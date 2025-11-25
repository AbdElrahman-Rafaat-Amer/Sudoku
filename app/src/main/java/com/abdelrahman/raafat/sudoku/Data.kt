package com.abdelrahman.raafat.sudoku

val THREE_BY_THREE_GRID = listOf(
    arrayOf(
        arrayOf("1", "-", "2"),
        arrayOf("-", "-", "-"),
        arrayOf("3", "-", "-")
    ),
    arrayOf(
        arrayOf("-", "3", "-"),
        arrayOf("1", "-", "-"),
        arrayOf("-", "-", "2")
    ),
    arrayOf(
        arrayOf("-", "-", "-"),
        arrayOf("-", "2", "-"),
        arrayOf("3", "-", "1")
    ),
    arrayOf(
        arrayOf("2", "-", "-"),
        arrayOf("-", "-", "1"),
        arrayOf("-", "3", "-")
    ),
    arrayOf(
        arrayOf("-", "1", "-"),
        arrayOf("-", "-", "3"),
        arrayOf("2", "-", "-")
    ),
    arrayOf(
        arrayOf("3", "-", "-"),
        arrayOf("-", "1", "-"),
        arrayOf("-", "-", "2")
    ),
    arrayOf(
        arrayOf("-", "-", "-"),
        arrayOf("1", "-", "3"),
        arrayOf("-", "2", "-")
    ),
    arrayOf(
        arrayOf("-", "2", "-"),
        arrayOf("-", "-", "-"),
        arrayOf("1", "-", "3")
    ),
    arrayOf(
        arrayOf("-", "-", "1"),
        arrayOf("2", "-", "-"),
        arrayOf("-", "3", "-")
    ),
    arrayOf(
        arrayOf("-", "-", "-"),
        arrayOf("-", "3", "-"),
        arrayOf("1", "-", "2")
    )
)

val FOUR_BY_FOUR_GRID = listOf(
    arrayOf(
        arrayOf("1", "-", "-", "4"),
        arrayOf("-", "3", "-", "-"),
        arrayOf("2", "-", "1", "-"),
        arrayOf("-", "-", "-", "3")
    ),
    arrayOf(
        arrayOf("-", "2", "-", "3"),
        arrayOf("1", "-", "-", "-"),
        arrayOf("-", "-", "4", "-"),
        arrayOf("3", "-", "-", "2")
    ),
    arrayOf(
        arrayOf("3", "-", "-", "-"),
        arrayOf("-", "4", "-", "-"),
        arrayOf("1", "-", "-", "2"),
        arrayOf("-", "-", "3", "-")
    ),
    arrayOf(
        arrayOf("-", "-", "-", "1"),
        arrayOf("2", "-", "-", "3"),
        arrayOf("-", "4", "-", "-"),
        arrayOf("3", "-", "-", "2")
    ),
    arrayOf(
        arrayOf("1", "-", "-", "-"),
        arrayOf("-", "-", "4", "2"),
        arrayOf("3", "-", "-", "-"),
        arrayOf("-", "-", "-", "1")
    ),
    arrayOf(
        arrayOf("2", "-", "-", "3"),
        arrayOf("-", "-", "1", "-"),
        arrayOf("4", "-", "-", "-"),
        arrayOf("-", "-", "-", "2")
    ),
    arrayOf(
        arrayOf("-", "3", "-", "4"),
        arrayOf("1", "-", "-", "-"),
        arrayOf("-", "-", "2", "3"),
        arrayOf("4", "-", "-", "-")
    ),
    arrayOf(
        arrayOf("4", "-", "-", "-"),
        arrayOf("2", "-", "3", "-"),
        arrayOf("-", "-", "1", "-"),
        arrayOf("-", "4", "-", "2")
    ),
    arrayOf(
        arrayOf("-", "-", "1", "3"),
        arrayOf("2", "-", "-", "-"),
        arrayOf("-", "4", "-", "1"),
        arrayOf("3", "-", "-", "-")
    ),
    arrayOf(
        arrayOf("3", "-", "-", "2"),
        arrayOf("1", "-", "4", "-"),
        arrayOf("-", "-", "-", "3"),
        arrayOf("-", "2", "-", "-")
    )
)

