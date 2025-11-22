package org.example.project.home

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import home.HomeAction
import home.HomeRoute
import home.HomeStore
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeContainer(store: HomeStore = HomeStore()) {
    val state by store.state.collectAsState()

    if(state.loading)
        CircularProgressIndicator()

    when(state.route) {
        HomeRoute.Dashboard -> HomeView(
            households = state.households,
            { store.dispatch(HomeAction.CreateHouseHoldTapped) },
            { store.dispatch(HomeAction.JoinTapped)},
            { store.dispatch((HomeAction.InviteTapped)) }
        )
        HomeRoute.CreateHouseHold -> CreateHouseholdView(
            { store.dispatch(HomeAction.CreateHouseHold(it)) }
        )
        HomeRoute.Join -> JoinView(
            {store.dispatch(HomeAction.Join(it))},
            backTapped = { store.dispatch(HomeAction.BackTapped) }
        )
        HomeRoute.Invite -> InviteView(
            state.groupId,
            backTapped = { store.dispatch(HomeAction.BackTapped) }
        )
   }

}