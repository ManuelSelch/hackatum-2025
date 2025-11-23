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
            is PantryAction.GoToShelf ->{ fetchPantryItems(); state.copy(route = Shelf(action.type))}
            is PantryAction.GoToView -> state.copy(route = View)
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
            val currentGroupId = user.currentGroup
            if(currentGroupId != null) {
                api.create(currentGroupId, item).onSuccess {
                    println("Successfully created a new item: $item for household ${user.currentGroup!!}")
                    fetchPantryItems()
                    dispatch(PantryAction.GoToShelf(enumValueOf<ShelfType>(item.category)))
                }.onFailure {
                    println("Error on CREATE PANTRY: ${it.message}")
                    dispatch(PantryAction.GoToShelf(enumValueOf<ShelfType>(item.category)))
                }
            }
            else{
                println("No groupId is available")
            }

        }
        return state

    }
    fun fetchPantryItems() {
        scope.launch {
            val currentGroupId = user.currentGroup
            if(currentGroupId != null) {
                api.get(currentGroupId).onSuccess {
                    println("Successfully fetched pantry items: $it")
                    dispatch(PantryAction.PantryItemsFetched(it))
                }.onFailure {
                    println("Error on GET PANTRY: ${it.message}")
                }
            }
            else{
                print("Group not found!")
            }

        }
        return
    }
}