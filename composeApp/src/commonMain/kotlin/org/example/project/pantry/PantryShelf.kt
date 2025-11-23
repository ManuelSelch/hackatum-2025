package org.example.project.pantry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.PantryItemDTO
import org.example.project.pantry.item.PantryItemCard
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.AppTheme
import org.example.project.theme.TEXT_L
import org.example.project.theme.icons.Arrow_drop_down
import pantry.ShelfType


@Composable
fun PantryShelf(
    shelfType: ShelfType,
    onBack: () -> Unit,
    updateTapped: (PantryItemDTO) -> Unit,
    createTapped: () -> Unit,
    pantryItems: List<PantryItemDTO>,
)
{
    AppTheme{
        Surface(Modifier .background(color = MaterialTheme.colorScheme.background)){
            val items = pantryItems

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            )
            {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = onBack) { Row(verticalAlignment = Alignment.CenterVertically){Icon(Arrow_drop_down, contentDescription = "back_to_pantry",  Modifier.rotate(90f).size(32.dp), tint = MaterialTheme.colorScheme.primary); Text("Back", fontSize = TEXT_L.sp) }}

                    Spacer (Modifier.width(0.dp).height(24.dp))

                    Text(
                        text = "$shelfType Shelf",
                        Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.headlineMedium,


                    )
                }
                Spacer(Modifier.height(12.dp))

                // Grid of pantry items
                LazyVerticalGrid(
                    modifier = Modifier.weight(1f),
                    columns = GridCells.Adaptive(minSize = 90.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items) { item: PantryItemDTO ->
                        PantryItemCard(
                            item.name,
                            item.unit,
                            item.quantity,
                            item.category,
                            item.minimumQuantity
                        ) { updateTapped(item) }
                    }
                }

                Spacer(Modifier.height(12.dp))

                // Add Item Button
                Button(
                    modifier = Modifier.wrapContentWidth().clip(ButtonDefaults.shape).background(brush = AppTheme.brushes.primaryGradient).align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    onClick = {
                        createTapped()
                    }
                ) {
                    Text("Add Pantry Item", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }

    }
}


