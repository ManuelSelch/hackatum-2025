package org.example.project.pantry.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.AppTheme
import org.example.project.theme.TEXT_REG
import org.example.project.theme.icons.Drink
import org.example.project.theme.icons.Food
import org.example.project.theme.icons.Misc
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable

fun PantryItemCard(name: String, unit: String?, quantity: Double, category: String?, minimumQuantity: Int?) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(AppTheme.brushes.gradient)
            .width(100.dp)
            .height(110.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium),


    ) {
        Column(Modifier.fillMaxWidth().fillMaxHeight().padding(0.dp, 8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Icon(if(category == "Food") Food else if(category == "Drinks") Drink else Misc, contentDescription = "food_icon", Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Color Indicators for how much is left compared to wanted amount.
                Text("${quantity.pretty()} ${unit ?: ""}",  fontSize = TEXT_REG.sp,
                    color =
                    if(minimumQuantity != null && quantity <= minimumQuantity) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary.copy(0.8f))

                Text(if(name.length > 8) name.take(8) + "..." else name, textAlign = TextAlign.Center, fontSize = TEXT_REG.sp)

            }
        }



    }
}

@Preview
@Composable
fun PantryItemCardPreview(){
    AppTheme{
        PantryItemCard("Toilet Paper", "Rolls", 9.0, "misc", minimumQuantity = 3)
    }

}

fun Double.pretty(): String =
    if (this % 1.0 == 0.0) {
        this.toInt().toString()
    } else {
        this.toString()
    }

