package org.example.project.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import login.LoginAction
import login.LoginStore
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LoginContainer(store: LoginStore = LoginStore()) {
    val state by store.state.collectAsState()

    var username by remember { mutableStateOf(state.username) }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if(state.isLoading) {
            CircularProgressIndicator()
        }
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        Button(onClick = { store.dispatch(LoginAction.Submit(username)) }) {
            Text("Login")
        }
    }
}