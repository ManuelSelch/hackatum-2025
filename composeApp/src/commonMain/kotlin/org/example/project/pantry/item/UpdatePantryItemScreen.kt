package org.example.project.pantry.item

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.theme.AppColors.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import pantry.PantryItem

@Composable
@Preview
fun UpdatePantryItemScreen(
    item: PantryItem,
    onUpdate: (PantryItem) -> Unit
) {
    AppTheme{
        Surface(Modifier.background(color = MaterialTheme.colorScheme.background)) {
            PantryItemForm(
                initialItem = item,
                buttonText = "Update",
                titleText = "Update Pantry Item",
                onSubmit = onUpdate,
            )
        }
    }
}
