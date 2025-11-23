package pantry

import common.Store
import common.UserService
import kotlinx.coroutines.launch
import models.PantryItemDTO
import pantry.PantryRoute.*



data class PantryState(
    val route: PantryRoute = View,
    val pantryItems: List<PantryItemDTO>
)
enum class ShelfType(val label: String) {
    Food("Food"),
    Drinks("Drinks"),
    Miscellaneous("Miscellaneous")
}

sealed class PantryRoute {
    data object View : PantryRoute()
    data object Create : PantryRoute()
    data class Update(val item: PantryItemDTO) : PantryRoute()
    data class Shelf(val type: ShelfType) : PantryRoute()
}
sealed class PantryAction {
    data object GoToView : PantryAction()

    data class GoToShelf( val type: ShelfType): PantryAction()

    data object GoToCreatePantryItem : PantryAction()

    data object GoToUpdatePantryItem : PantryAction()

    data class CreatePantryItem(val item: PantryItemDTO) : PantryAction()
    data class UpdatePantryItem(val item: PantryItemDTO) : PantryAction()

    data class PantryItemsFetched(val items: List<PantryItemDTO>): PantryAction()
}

sealed class PantryEffect {
}
class PantryStore(val user: UserService): Store<PantryState, PantryAction, PantryEffect>(PantryState(pantryItems = listOf())) {
    val api = PantryAPI()

    override fun reduce(state: PantryState, action: PantryAction): PantryState {
        return when (action) {
            is PantryAction.GoToShelf -> state.copy(route = Shelf(action.type))
            is PantryAction.GoToView -> {
                fetchPantryItems()
                state.copy(route = View)
            };
            PantryAction.GoToCreatePantryItem -> state.copy(route = Create)
            PantryAction.GoToUpdatePantryItem -> state;
            is PantryAction.CreatePantryItem -> createPantryItem(state, action.item);
            is PantryAction.UpdatePantryItem -> {
                //api.update()
                state;
            };
            is PantryAction.PantryItemsFetched -> state.copy(pantryItems = action.items)
        }
    }
    fun createPantryItem(state: PantryState, item: PantryItemDTO): PantryState {
        scope.launch{
            api.create(user.currentGroup!!, item).onSuccess {
                dispatch(PantryAction.GoToShelf(enumValueOf<ShelfType>(item.category)))
            }.onFailure {
                println("Error on CREATE PANTRY: ${it.message}")
                dispatch(PantryAction.GoToShelf(enumValueOf<ShelfType>(item.category)))
            }
        }
        return state

    }
    fun fetchPantryItems() {
        scope.launch {
            api.get(user.currentGroup!!).onSuccess {
                dispatch(PantryAction.PantryItemsFetched(it))
            }.onFailure {
                println("Error on GET PANTRY: ${it.message}")
            }
        }
        return
    }
}