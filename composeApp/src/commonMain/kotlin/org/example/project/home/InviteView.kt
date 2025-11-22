package org.example.project.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    ) {
        Text("Invite")
        Text("Group ID: $groupId")

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