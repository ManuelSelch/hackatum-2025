package org.example.project.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.TEXT_L
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
        Text(text = "Login", fontSize = TEXT_L.sp)

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

        Row ( horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = registerTapped, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Sign Up")
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