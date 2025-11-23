package org.example.project.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

        Row {
            Button({save(name, email)}) { Text("Save") }
            Button(logoutTapped) { Text("Logout") }
        }
    }
}