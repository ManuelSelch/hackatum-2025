package org.example.project.pantry.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.PantryItemDTO
import org.example.project.theme.AppTheme
import org.example.project.theme.TEXT_L
import org.example.project.theme.TEXT_REG
import org.example.project.theme.icons.*
import pantry.ShelfType

@Composable
fun PantryItemForm(
    initialItem: PantryItemDTO,
    buttonText: String,
    titleText: String,
    onSubmit: (PantryItemDTO) -> Unit,
    back: () -> Unit
)
{
    var item by remember { mutableStateOf(initialItem) }
    var name by remember { mutableStateOf(initialItem.name) }
    var unit by remember { mutableStateOf(initialItem.unit) }
    var quantityText by remember { mutableStateOf(initialItem.quantity.toString()) }
    var minimumQuantityText by remember { mutableStateOf(initialItem.minimumQuantity.toString()) }
    var category by remember { mutableStateOf(initialItem.category) }
    var expanded by remember { mutableStateOf(false) }

    val categories = ShelfType.entries

    // Validation
    val isUnitValid = unit.trim().isNotEmpty()
    val isNameValid = name.trim().length >= 3
    val quantity = quantityText.toIntOrNull() ?: -1
    val isQuantityValid = quantity >= 0
    val minimumQuantity = minimumQuantityText.toIntOrNull() ?: 1
    val isMinQuantityValid = minimumQuantity >= 0
    val isFormValid = isNameValid && isQuantityValid && isUnitValid && isMinQuantityValid

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            Icon(XCircle, contentDescription = null, Modifier.size(32.dp).clickable{back()}, tint = MaterialTheme.colorScheme.error)
        }
        Text(titleText, color = MaterialTheme.colorScheme.primary, fontSize = TEXT_L.sp)
        Column(Modifier.padding(0.dp, 32.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {


            TextField(
                value = name,
                onValueChange = { name = it },
                label = {  Row{
                    Text("Name"); Text("*", color = MaterialTheme.colorScheme.error)} },
                isError = !isNameValid && name.isNotEmpty(),
                singleLine = true
            )
            if (!isNameValid && name.isNotEmpty()) {
                Text("Name must be at least 3 characters", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }

            TextField(
                value = unit,
                onValueChange = { unit = it },
                label = { Row{Text("Unit"); Text("*", color = MaterialTheme.colorScheme.error)} },
                isError = !isUnitValid && unit.isNotEmpty(),
                singleLine = true
            )
            if (!isUnitValid && unit.isNotEmpty()){
                Text("Quantity must be >= 0", color = MaterialTheme.colorScheme.error, fontSize = TEXT_REG.sp)
            }

            TextField(
                value = quantityText,
                onValueChange = { quantityText = it },
                label = { Row{Text("Quantity"); Text("*", color = MaterialTheme.colorScheme.error)} },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = !isQuantityValid && quantityText.isNotEmpty(),
                singleLine = true
            )
            if (!isQuantityValid && quantityText.isNotEmpty()) {
                Text("Quantity must be >= 0", color = MaterialTheme.colorScheme.error, fontSize = TEXT_REG.sp)
            }

            // Minimum quantity (optional Int)
            TextField(
                value = minimumQuantityText,
                onValueChange = { minimumQuantityText = it },
                label = { Row{Text("Minimum Quantity"); Text("*", color = MaterialTheme.colorScheme.error)} },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = !isMinQuantityValid && minimumQuantityText.isNotEmpty(),
                singleLine = true
            )
            if (!isMinQuantityValid && minimumQuantityText.isNotEmpty()) {
                Text("Minimum Quantity must be >= 0", color = MaterialTheme.colorScheme.error, fontSize = TEXT_REG.sp)
            }

            // Category dropdown
            var expanded by remember { mutableStateOf(false) }
            Box {
                TextField(
                    value = category.toString(),
                    onValueChange = { },
                    label = { Text("Category") },
                    readOnly = true,
                    trailingIcon = {
                    Icon(
                        Arrow_drop_down, contentDescription = null,
                        Modifier.clickable { expanded = true })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                )
                {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            onClick = {
                                category = cat.toString()
                                expanded = false
                            },
                            text = { Text(cat.toString()) },
                            trailingIcon = {
                                Icon(
                                    if (cat == ShelfType.Food) Food else if (cat == ShelfType.Drinks) Drink else Misc,
                                    contentDescription = "food_icon",
                                    Modifier.size(48.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                        )
                    }
                }
            }


            // Submit button

        }
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .clip(ButtonDefaults.shape)
                .background(AppTheme.brushes.primaryGradient),
            onClick = {
                onSubmit(PantryItemDTO(
                    name = name,
                    unit = unit,
                    category = category,
                    quantity = quantity,
                    minimumQuantity = minimumQuantity,
                    groupId = 1,
                ))
            },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(buttonText, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
