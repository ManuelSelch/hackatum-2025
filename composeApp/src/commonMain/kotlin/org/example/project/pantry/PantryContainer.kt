package org.example.project.pantry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import pantry.PantryAction
import pantry.PantryRoute
import pantry.PantryStore

@Composable
@Preview
fun PantryContainer(store: PantryStore = PantryStore()) {
    val state by store.state.collectAsState()

    Column(Modifier.fillMaxSize().safeContentPadding()) {
        when (state.route) {
            is PantryRoute.Shelf -> PantryShelf(
                (state.route as PantryRoute.Shelf).type,
                { store.dispatch(PantryAction.GoToView) }
            )

            PantryRoute.View -> PantryView(
                { store.dispatch(PantryAction.GoToShelf("food")) },
                { store.dispatch(PantryAction.GoToShelf("drinks")) },
                { store.dispatch(PantryAction.GoToShelf("miscellaneous")) }
            )
        }
    }

}