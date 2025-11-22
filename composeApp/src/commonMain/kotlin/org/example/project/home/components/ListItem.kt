package org.example.project.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.TEXT_M
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ListItem(
    item: String,
    quantity: Int,
){
    AppTheme {
       Column{
           Text("- $item", color = MaterialTheme.colorScheme.onBackground, fontSize = TEXT_M.sp)
           Text("$quantity Left", color = MaterialTheme.colorScheme.error, fontSize = TEXT_M.sp)
       }
    }
}