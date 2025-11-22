package org.example.project

import AppColors.AppTheme
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// 0.08, 261





@Composable
@Preview
fun Pantry() {
    AppTheme {
        Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
        ) {
            Box(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)) {
                Text("Haushaltsmanager",  Modifier
                    .padding(8.dp, 8.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterStart),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 36.sp,

                    )
            }


        }

    }
}