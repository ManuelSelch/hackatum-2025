package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterView(
    username: String, usernameChanged: (String) -> Unit,
    password: String, passwordChanged: (String) -> Unit,
    loginTapped: () -> Unit, registerTapped: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "Register", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = username,
            onValueChange = usernameChanged,
            label = { Text("Username")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = password,
            onValueChange = passwordChanged,
            label = { Text("Password")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = registerTapped,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "already have an account?")
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = loginTapped) {
                Text(text = "Login")
            }
        }

    }
}

@Composable
@Preview
fun RegisterPreview() {
    RegisterView(
        "", {},
        "", {},
        {}, {}
    )
}