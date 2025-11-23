package org.example.project.home

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
    save: (name: String, email: String) -> Unit,
    logoutTapped: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column {
        TextField(name, onValueChange = { name = it }, label = { Text("Name") })
        TextField(email, onValueChange = { email = it }, label = { Text("Email") })

        Row {
            Button({}) { Text("Save") }
            Button({}) { Text("Logout") }
        }
    }
}