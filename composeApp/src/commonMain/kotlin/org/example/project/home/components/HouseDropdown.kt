package org.example.project.home.components

import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import org.example.project.theme.icons.Add
import org.example.project.theme.icons.Arrow_drop_down
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseDropdown(
    items: List<String>,
    label: String = "Select item",
    onSelected: (String) -> Unit,
    createHouseholdTapped: () -> Unit
) {
        var expanded by remember { mutableStateOf(false) }
        var selected by remember { mutableStateOf(items.firstOrNull() ?: "") }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            ) {
            TextField(
                value = selected,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    Image(Arrow_drop_down, contentDescription = null, modifier = Modifier.rotate(if (expanded) 180f else 0f))
                },

                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable, true)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selected = item
                            expanded = false
                            onSelected(item)
                        },


                    )
                }
                DropdownMenuItem(
                    text = { Text("Create new Team")},
                    leadingIcon = { Icon(Add, contentDescription = null) },
                    onClick = {
                        expanded = false
                        createHouseholdTapped()
                    }
                )
            }
        }


}

@Composable
@Preview

fun HouseDropdownPreview(){
    HouseDropdown(
        listOf("hello", "hello1", "hello2"),
        "Select item",
        {}, {}
    )
}