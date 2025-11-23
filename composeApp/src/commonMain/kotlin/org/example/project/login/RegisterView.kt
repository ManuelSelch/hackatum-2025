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
fun RegisterView(
    username: String, usernameChanged: (String) -> Unit,
    email: String, emailChanged: (String) -> Unit,
    password: String, passwordChanged: (String) -> Unit,
    loginTapped: () -> Unit, registerTapped: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "Register", fontSize = TEXT_L.sp)

        TextField(
            value = username,
            onValueChange = usernameChanged,
            label = { Text("Username")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = email,
            onValueChange = emailChanged,
            label = { Text("Email")},
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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row ( horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Already have an account?")
            Button(onClick = loginTapped, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                Text("Log In")
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
        "", {},
        {}, {}
    )
}