package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.AppRoute
import common.AppStore
import org.example.project.home.HomeContainer
import org.example.project.login.LoginContainer
import org.example.project.pantry.PantryContainer
import org.example.project.theme.AppColors.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContainer(scanner: @Composable (callback: (String) -> Unit) -> Unit) {
    val app = remember { AppStore() }

    val state by app.state.collectAsState()
    AppTheme{
        Surface(Modifier.safeContentPadding()) {
            Column {
                when(state.route) {
                    AppRoute.Login -> LoginContainer(app.login)
                    AppRoute.Home -> HomeContainer(app.home, scanner)
                    AppRoute.Pantry -> PantryContainer(app.pantry)
                }
            }
        }
    }

}