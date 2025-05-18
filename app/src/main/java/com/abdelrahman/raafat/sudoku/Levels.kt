package com.abdelrahman.raafat.sudoku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abdelrahman.raafat.sudoku.ui.theme.Blue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Levels(viewModel: SudokuViewModel) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        for (i in viewModel.gameType.indices) {
            Button(
                onClick = {
                    viewModel.drawNewGame(i)
                },
                content = {
                    Text(text = viewModel.gameType[i].first, color = Blue)
                },
                modifier =
                    Modifier
                        .defaultMinSize(minWidth = 75.dp, minHeight = 50.dp)
                        .padding(end = 5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
            )
        }
    }
}
