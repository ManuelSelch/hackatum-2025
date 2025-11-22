package org.example.project.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import org.example.project.theme.ROUNDED_M
import org.example.project.theme.ROUNDED_XL
import org.example.project.theme.TEXT_SM
import org.example.project.theme.icons.Person
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DebtUser(
    username: String,
    debt: Double,
) {
        Box(
            Modifier.wrapContentWidth()
                .wrapContentHeight()
                .background(brush = AppTheme.brushes.gradient, RoundedCornerShape(ROUNDED_M.dp))
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.outlineVariant,
                    RoundedCornerShape(ROUNDED_M.dp)
                )
                .padding(4.dp)
        ) {
            Column(Modifier.wrapContentWidth().padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Person, contentDescription = username,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(username, fontSize = TEXT_SM.sp, color = MaterialTheme.colorScheme.onBackground)
                }
                Text(
                    "$$debt",
                    Modifier.align(Alignment.End),
                    fontSize = TEXT_SM.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
}

@Composable
@Preview
fun DebtUserPreview(){
    DebtUser("Isaac", 3.49)
}