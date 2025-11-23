package org.example.project.pantry.item

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.UserService
import models.PantryItemDTO
import org.example.project.theme.AppColors.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import pantry.ShelfType

@Composable
@Preview
fun AddPantryItemScreen(
    onCreate: (PantryItemDTO) -> Unit
) {
    AppTheme{
        Surface (Modifier.background(color = MaterialTheme.colorScheme.background)){
            PantryItemForm(
                initialItem = PantryItemDTO(
                    groupId = 0,
                    name = "",
                    unit = "",
                    quantity = 0,
                    category = ShelfType.Miscellaneous.toString(),
                    minimumQuantity = 0
                ),
                buttonText = "Create",
                titleText = "New Pantry Item",
                onSubmit = onCreate,
            )
        }
    }

}
