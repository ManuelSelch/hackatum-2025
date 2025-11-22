package org.example.project.pantry

import org.example.project.theme.AppColors.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.example.project.theme.AppTheme


@Composable
@Preview
fun Pantry() {
    AppTheme {
        Column(Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxSize()

                ) {
                    Text("Pantry",  Modifier
                        .padding(8.dp, 8.dp, 0.dp, 0.dp),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 36.sp,

                        )
                }

            }
        }

}