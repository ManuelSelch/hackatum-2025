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
import org.example.project.theme.TEXT_M
import org.example.project.theme.icons.Receipt_long

@Composable
fun ShoppingWidget(){
        Box(
            modifier = Modifier
                .background(AppTheme.brushes.gradient, shape = RoundedCornerShape(24.dp))
                .width(160.dp)
                .height(88.dp)
                .border(
                    2.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(24.dp)
                )
        )
        {
            Row(
                Modifier.fillMaxWidth().fillMaxHeight().padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Shopping",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = TEXT_M.sp
                )
                Icon(
                    Receipt_long,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )

            }

        }
}