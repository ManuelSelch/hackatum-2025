package org.example.project.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InviteView(
    groupId: Long?,
    backTapped: () -> Unit
) {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Invite")
        Text("Group ID: $groupId")

        Image(
            painter = rememberQrCodePainter(groupId?.toString() ?: "0"),
            contentDescription = groupId?.toString() ?: "0",
            modifier = Modifier.size(300.dp)
        )

        Button(onClick = backTapped) {
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