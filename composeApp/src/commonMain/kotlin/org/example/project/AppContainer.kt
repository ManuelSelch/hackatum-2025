package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import common.AppRoute
import common.AppStore
import login.LoginStore
import org.example.project.home.HomeContainer
import org.example.project.login.LoginContainer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContainer() {
    val app = remember { AppStore() }

    val state by app.state.collectAsState()

    Column {
        when(state.route) {
            AppRoute.Login -> LoginContainer(app.login)
            AppRoute.Home -> HomeContainer(app.home)
        }
    }
}