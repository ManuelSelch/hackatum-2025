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
import org.example.project.theme.AppColors.AppTheme
import org.example.project.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeContainer(store: HomeStore = HomeStore()) {
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
                        households = state.households,
                        { store.dispatch(HomeAction.CreateHouseHoldTapped) },
                        { store.dispatch(HomeAction.JoinTapped) },
                        { store.dispatch((HomeAction.InviteTapped)) }
                    )

                    HomeRoute.CreateHouseHold -> CreateHouseholdView(
                        { store.dispatch(HomeAction.CreateHouseHold(it)) }
                    )

                    HomeRoute.Join -> JoinView(
                        { store.dispatch(HomeAction.Join(it)) },
                        backTapped = { store.dispatch(HomeAction.BackTapped) }
                    )

                    HomeRoute.Invite -> InviteView(
                        state.groupId,
                        backTapped = { store.dispatch(HomeAction.BackTapped) }
                    )
                }
            }
        }
    }

}