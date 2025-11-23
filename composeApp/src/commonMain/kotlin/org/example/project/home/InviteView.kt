package org.example.project.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.options.QrBrush
import io.github.alexzhirkevich.qrose.options.solid
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InviteView(
    groupId: Long?,
    backTapped: () -> Unit
) {
    val foreground =  MaterialTheme.colorScheme.onBackground
    val background =  MaterialTheme.colorScheme.background
    val frameColor = MaterialTheme.colorScheme.primary

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Invite")
        Text("Group ID: $groupId", Modifier.padding(bottom = 32.dp))

        Image(
            painter = rememberQrCodePainter(groupId?.toString() ?: "0"){
               colors {
                    dark = QrBrush.solid(foreground)
                   frame = QrBrush.solid(frameColor)
                }
            },
            contentDescription = groupId?.toString() ?: "0",
            modifier = Modifier.size(300.dp)
        )

        Button(onClick = backTapped, Modifier.padding(top = 32.dp)) {
            Text("Back")
        }
    }
}

@Composable
@Preview
fun InvitePreview() {
    InviteView(
        21,
        {}
    )
}