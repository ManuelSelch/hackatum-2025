package org.example.project.pantry

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.TEXT_XL
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun PantryView(
    foodTapped: () -> Unit,
    drinksTapped: () -> Unit,
    miscellaneousTapped: () -> Unit,

) {
    AppTheme(darkTheme = false) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)

            )
            {
                // Top title section (25% height)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Your Pantry",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                // Shelf 1
                ShelfRow(
                    type = "Food",
                    foodTapped,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                // Shelf 2
                ShelfRow(
                    type = "Drinks",
                    drinksTapped,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )

                // Shelf 3
                ShelfRow(
                    type = "Miscellaneous",
                    miscellaneousTapped,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ShelfRow(type: String, onClick: () -> Unit,  modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .background(
                    color = when (type) {
                        "Food" -> MaterialTheme.colorScheme.outlineVariant
                        "Drinks" -> MaterialTheme.colorScheme.outline
                        else -> MaterialTheme.colorScheme.primary.copy(0.8f)
                    },
                    shape = MaterialTheme.shapes.medium
                ).border(2.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(type, fontSize = TEXT_XL.sp)

        }

}
