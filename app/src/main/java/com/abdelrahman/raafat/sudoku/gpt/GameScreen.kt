// GameScreen.kt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdelrahman.raafat.sudoku.gpt.BoardSize
import com.abdelrahman.raafat.sudoku.gpt.DifficultyLevel
import com.abdelrahman.raafat.sudoku.ui.theme.SudokuTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SudokuGameScreen(
    difficulty: DifficultyLevel,
    boardSize: BoardSize,
    onBack: () -> Unit
) {
    val size = boardSize.size
    val cellValues = remember { Array(size) { IntArray(size) } }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${size}×${size} Sudoku",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = difficulty.name,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Timer and Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatBadge(icon = Icons.Default.AddCircle, text = "05:23")
                StatBadge(icon = Icons.Default.Edit, text = "12/45")
                StatBadge(icon = Icons.Default.Info, text = "3 mistakes")
            }

            // Board
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(4.dp)
            ) {
                SudokuBoard(size, cellValues)
            }

            // Number Pad
            NumberPad(
                modifier = Modifier.fillMaxWidth(),
                onNumberSelected = { /* Handle input */ }
            )

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = "Hint",
                    icon = Icons.Default.Notifications,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = "Undo",
                    icon = Icons.Default.Clear,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = "Check",
                    icon = Icons.Default.CheckCircle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun StatBadge(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun NumberPad(modifier: Modifier = Modifier, onNumberSelected: (Int) -> Unit) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Number buttons 1-9
        repeat(3) { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(3) { col ->
                    val number = row * 3 + col + 1
                    NumberButton(
                        number = number,
                        onClick = { onNumberSelected(number) }
                    )
                }
            }
        }

        // Bottom row with 0 (erase) and other functions
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            NumberButton(
                number = 0,
                onClick = { onNumberSelected(0) },
                modifier = Modifier.weight(2f)
            )
            NumberButton(
                icon = Icons.Default.Delete,
                onClick = { /* Handle erase */ },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NumberButton(
    number: Int? = null,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ) {
        if (number != null) {
            Text(
                text = if (number > 0) number.toString() else "Erase",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        } else if (icon != null) {
            Icon(imageVector = icon, contentDescription = null)
        }
    }
}

@Composable
fun ActionButton(text: String, icon: ImageVector, modifier: Modifier = Modifier) {
    FilledTonalButton(
        onClick = { /* Handle action */ },
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Text(text)
        }
    }
}

@Composable
fun SudokuBoard(size: Int, cellValues: Array<IntArray>) {
    val subGridSize = when {
        size % 3 == 0 -> 3
        size % 2 == 0 -> 2
        else -> 1
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Draw subgrid borders
        val cellSize = size.width / size.toFloat()

        // Draw thick subgrid borders
        for (i in 0..size step subGridSize) {
            drawLine(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                start = Offset(x = i * cellSize, y = 0f),
                end = Offset(x = i * cellSize, y = size.height),
                strokeWidth = if (i % subGridSize == 0) 2.dp.toPx() else 1.dp.toPx()
            )
            drawLine(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                start = Offset(x = 0f, y = i * cellSize),
                end = Offset(x = size.width, y = i * cellSize),
                strokeWidth = if (i % subGridSize == 0) 2.dp.toPx() else 1.dp.toPx()
            )
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val cellSize = maxWidth / size

        // Draw cells
        for (row in 0 until size) {
            for (col in 0 until size) {
                val value = cellValues[row][col]
                Box(
                    modifier = Modifier
                        .size(cellSize)
                        .offset(x = cellSize * col, y = cellSize * row)
                        .clickable { /* Handle cell selection */ },
                    contentAlignment = Alignment.Center
                ) {
                    if (value != 0) {
                        Text(
                            text = value.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = if (/* isGiven */ true) {
                                MaterialTheme.colorScheme.onSurface
                            } else {
                                MaterialTheme.colorScheme.primary
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGameScreen() {
    SudokuTheme {
        SudokuGameScreen(
            difficulty = DifficultyLevel.Medium,
            boardSize = BoardSize.NineByNine,
            onBack = {}
        )
    }
}