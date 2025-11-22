package org.example.project.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.TEXT_M
import org.example.project.theme.icons.Person
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

@Composable

fun DebtUser(
    username: String,
    debt: Float,
) {
    AppTheme() {
        Column(Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Person, contentDescription = username,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onBackground)
                Text(username, fontSize = TEXT_M.sp, color = MaterialTheme.colorScheme.onBackground)
            }
            Text("$debt",
                textAlign = TextAlign.End, fontSize = TEXT_M.sp,
                color = MaterialTheme.colorScheme.onBackground)
        }

    }

}

@Composable
@Preview
fun DebtUserPreview(){
    DebtUser("Isaac", 3.49f)
}