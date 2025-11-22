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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppTheme
import org.example.project.theme.TEXT_M
import org.example.project.theme.TEXT_REG
import org.example.project.theme.icons.CircleMoney
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ExpenseWidget(){
        Box(
            modifier = Modifier
                .background(AppTheme.brushes.gradient, shape = RoundedCornerShape(36.dp))
                .size(196.dp)
                .border(
                    2.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(36.dp)
                )
        ) {
            Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.Center) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(
                        "Expense Manager",
                        textAlign = TextAlign.Start,
                        fontSize = TEXT_M.sp,
                        modifier = Modifier.padding(0.dp, 16.dp)
                    )
                }

                Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                    Column(Modifier.padding(4.dp), verticalArrangement = Arrangement.Center) {
                        Text(
                            "November",
                            fontSize = TEXT_REG.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text("$-392", color = MaterialTheme.colorScheme.error, fontSize = TEXT_REG.sp)
                    }

                }
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                    Icon(
                        CircleMoney,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(64.dp)
                    )
                }

            }
        }

}