package org.example.project.pantry.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.example.project.theme.AppTheme
import org.example.project.theme.TEXT_L
import org.example.project.theme.TEXT_REG
import org.example.project.theme.icons.Arrow_drop_down
import org.example.project.theme.icons.Drink
import org.example.project.theme.icons.Food
import org.example.project.theme.icons.Misc
import pantry.PantryItem
import pantry.ShelfType

@Composable
fun PantryItemForm(
    initialItem: PantryItem,
    buttonText: String,
    titleText: String,
    onSubmit: (PantryItem) -> Unit
)
{
    var item by remember { mutableStateOf(initialItem) }

    val categories = ShelfType.entries

    // Validation
    var quantityText = item.quantity.toString()
    var minimumQuantityText = item.quantity.toString()
    val isNameValid = item.name.trim().length >= 3
    val quantity = quantityText.toDoubleOrNull() ?: -1.0
    val isQuantityValid = quantity >= 0.0
    val minimumQuantity = minimumQuantityText.toDoubleOrNull() ?: 1.0
    val isMinQuantityValid = minimumQuantity >= 0.0
    val isFormValid = isNameValid && isQuantityValid

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(titleText, color = MaterialTheme.colorScheme.primary, fontSize = TEXT_L.sp)
        Column(Modifier.padding(0.dp, 32.dp).border(2.dp,brush = AppTheme.brushes.primaryGradient, shape = RoundedCornerShape(0.dp)), verticalArrangement = Arrangement.spacedBy(12.dp)) {


            TextField(
                value = item.name,
                onValueChange = { item.name = it },
                label = {  Row{
                    Text("Name"); Text("*", color = MaterialTheme.colorScheme.error)} },
                isError = !isNameValid && item.name.isNotEmpty(),
                singleLine = true
            )
            if (!isNameValid && item.name.isNotEmpty()) {
                Text("Name must be at least 3 characters", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }

            TextField(
                value = item.unit ?: "",
                onValueChange = { item.unit = it },
                label = { Text("Unit") },
                singleLine = true
            )

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
                label = { Text("Minimum Quantity") },
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
                    value = item.category.toString(),
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
                                item.category = cat
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
                onSubmit(item)
            },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(buttonText, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
