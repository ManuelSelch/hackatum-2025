package org.example.project.pantry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.example.project.pantry.item.AddPantryItemScreen
import org.example.project.pantry.item.UpdatePantryItemScreen
import pantry.PantryAction.*
import pantry.PantryRoute
import pantry.PantryStore
import pantry.ShelfType

@Composable
fun PantryContainer(store: PantryStore) {
    val state by store.state.collectAsState()

    Column(Modifier.fillMaxSize().safeContentPadding()) {
        when (val route = state.route) {
            is PantryRoute.Shelf -> PantryShelf(
                shelfType = route.type,
                createTapped = {store.dispatch(GoToCreatePantryItem)},
                updateTapped = {store.dispatch(GoToUpdatePantryItem(it))},
                onBack = { store.dispatch(GoToView) },
                items = state.pantryItems,
            )

            is PantryRoute.View -> PantryView(
                { store.dispatch(GoToShelf(ShelfType.Food)) },
                { store.dispatch(GoToShelf(ShelfType.Drinks)) },
                { store.dispatch(GoToShelf(ShelfType.Miscellaneous)) },
                onBack = {store.dispatch(HomeTapped)},
                pantryItems = state.pantryItems,
            )

            is PantryRoute.Create -> AddPantryItemScreen(
                backToShelf = {store.dispatch(GoToShelf(ShelfType.Food))},
                onCreate = { store.dispatch(CreatePantryItem(it)) },
            )
            is PantryRoute.Update -> UpdatePantryItemScreen(
                backToShelf = {store.dispatch(GoToShelf(ShelfType.Food))},
                item = route.item,
                onUpdate = { store.dispatch(UpdatePantryItem(it)) },
            )
        }
    }

}