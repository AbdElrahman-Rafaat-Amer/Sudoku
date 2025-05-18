package com.abdelrahman.raafat.sudoku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Numbers(
    boardSize: Int,
    onButtonClicked: (String) -> Unit,
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        for (i in 1..boardSize) {
            Button(
                onClick = {
                    onButtonClicked("$i")
                },
                content = {
                    Text(text = i.toString(), color = MaterialTheme.colorScheme.tertiary)
                },
                modifier =
                    Modifier
                        .defaultMinSize(minWidth = 75.dp, minHeight = 50.dp)
                        .padding(end = 5.dp),
            )
        }
        Button(
            onClick = {
                onButtonClicked("-")
            },
            content = {
                Text(text = "Clear")
            },
            modifier =
                Modifier
                    .defaultMinSize(minWidth = 75.dp, minHeight = 50.dp)
                    .padding(end = 5.dp),
        )
    }
}
