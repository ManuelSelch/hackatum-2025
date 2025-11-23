package org.example.project.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import home.HomeAction
import home.HomeRoute
import home.HomeStore
import org.example.project.login.SettingsView
import org.example.project.theme.AppColors.AppTheme

@Composable
fun HomeContainer(store: HomeStore ) {
    val state by store.state.collectAsState()

    AppTheme() {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .safeContentPadding()
                    .padding(16.dp),
            ) {

                if (state.loading)
                    CircularProgressIndicator()

                state.error?.let { Text(it) }

                when (state.route) {
                    HomeRoute.Dashboard -> HomeView(
                        state.groups,
                        state.current,
                        { store.dispatch(HomeAction.GroupSelected(it)) },
                        { store.dispatch(HomeAction.CreateHouseHoldTapped) },
                        { store.dispatch(HomeAction.JoinTapped) },
                        { store.dispatch((HomeAction.InviteTapped)) },
                        { store.dispatch(HomeAction.RefreshTapped) },
                        { store.dispatch(HomeAction.PantryTapped) },
                        { store.dispatch(HomeAction.SettingsTapped) },
                    )

                    HomeRoute.CreateHouseHold -> CreateHouseholdView(
                        { store.dispatch(HomeAction.CreateHouseHold(it)) },
                        {store.dispatch(HomeAction.BackTapped)}
                    )

                    HomeRoute.Join -> JoinView(
                        { store.dispatch(HomeAction.Join(it)) },
                        backTapped = { store.dispatch(HomeAction.BackTapped) }
                    )

                    HomeRoute.Invite -> InviteView(
                        state.current?.id,
                        backTapped = { store.dispatch(HomeAction.BackTapped) }
                    )
                }
            }
        }
    }

}