package org.example.project.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun JoinView(
    join: (groupId: String) -> Unit,
    backTapped: () -> Unit,
    scanner: @Composable () -> Unit
) {
    var groupId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text("Join")

        TextField(
            groupId, onValueChange = { groupId = it },
            label = { Text("Group ID") },
            singleLine = true
        )

        scanner()

        Row {
            Button(onClick = { join(groupId) }) {
                Text("Join")
            }
            Button(onClick = { backTapped() }) {
                Text("Back")
            }
        }
    }
}

@Composable
@Preview
fun JoinPreview() {
    JoinView(
        {},
        {},
        {}
    )
}