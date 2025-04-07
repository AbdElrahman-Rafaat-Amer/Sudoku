package com.abdelrahman.raafat.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.abdelrahman.raafat.sudoku.ui.theme.SudokuTheme


class MainActivity : ComponentActivity() {
    private val sudokuViewModel: SudokuViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SudokuTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SudokuBoard(
                        viewModel = sudokuViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}