package org.example.project.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SettingsView(
    initialName: String, initialEmail: String,
    save: (name: String, email: String) -> Unit,
    logoutTapped: () -> Unit,
) {
    var name by remember { mutableStateOf(initialName) }
    var email by remember { mutableStateOf(initialEmail) }

    Column {
        TextField(name, onValueChange = { name = it }, label = { Text("Name") })
        TextField(email, onValueChange = { email = it }, label = { Text("Email") })

        Row(Modifier.padding(top = 16.dp)) {
            Button({save(name, email)}) { Text("Save") }
            Button(logoutTapped) { Text("Logout") }
        }
    }
}