package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginView(
    email: String, emailChanged: (String) -> Unit,
    password: String, passwordChanged: (String) -> Unit,
    loginTapped: () -> Unit,
    registerTapped: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = email, onValueChange = emailChanged,
            singleLine = true,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = password, onValueChange = passwordChanged,
            singleLine = true,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = loginTapped,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Row {
            Text("dont have an account?")
            Button(onClick = registerTapped) {
                Text("Register")
            }
        }

    }
}

@Composable
@Preview
fun LoginPreview() {
    LoginView(
        "", {},
        "", {},
        {}, {}
    )
}