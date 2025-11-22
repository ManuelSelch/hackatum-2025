package org.example.project.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.AppTheme
import org.example.project.theme.ROUNDED_XL
import org.example.project.theme.TEXT_SM
import org.example.project.theme.icons.Add
import org.example.project.theme.icons.Drink
import org.example.project.theme.icons.Food
import org.example.project.theme.icons.Misc
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ListItem(
    item: String,
    quantity: Int,
    unit: String,
    type: String,
){
        Box(Modifier.width(128.dp)
            .wrapContentHeight()
            .background(brush = AppTheme.brushes.gradient, RoundedCornerShape(ROUNDED_XL.dp))
            .border(
                2.dp,
                MaterialTheme.colorScheme.outlineVariant,
                RoundedCornerShape(ROUNDED_XL))
            .padding(4.dp)) {
            Row(Modifier.fillMaxWidth().padding(4.dp, 0.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(item, color = MaterialTheme.colorScheme.primary, fontSize = TEXT_SM.sp)
                Icon(if(type == "food") Food else if(type == "drink") Drink else Misc, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }

        }
}

@Preview
@Composable
fun ListItemPreview(){
    ListItem("Banana", quantity = 1, unit = "", "drink")
}