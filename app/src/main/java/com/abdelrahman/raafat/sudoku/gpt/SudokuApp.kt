package com.abdelrahman.raafat.sudoku.gpt

// SudokuApp.kt
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdelrahman.raafat.sudoku.ui.theme.SudokuTheme

@Composable
fun SudokuApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            SudokuMainScreen { difficulty, size ->
                navController.navigate("game/${difficulty.name}/${size.name}")
            }
        }
        composable("game/{difficulty}/{size}") { backStackEntry ->
            val difficulty = DifficultyLevel.valueOf(
                backStackEntry.arguments?.getString("difficulty") ?: DifficultyLevel.Medium.name
            )
            val size = BoardSize.valueOf(
                backStackEntry.arguments?.getString("size") ?: BoardSize.NineByNine.name
            )

            SudokuGameScreen(
                difficulty = difficulty,
                boardSize = size,
                onBack = { navController.popBackStack() }
            )
        }
    }
}