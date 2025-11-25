package com.abdelrahman.raafat.sudoku.gpt

// Models.kt
enum class DifficultyLevel { Easy, Medium, Hard }

enum class BoardSize(val size: Int) {
    ThreeByThree(3),
    SixBySix(6),
    EightByEight(8),
    NineByNine(9),
    TenByTen(10),
    TwelveByTwelve(12),
    FourteenByFourteen(14)
}