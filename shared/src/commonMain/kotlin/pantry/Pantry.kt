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

    data class GoToUpdatePantryItem(val item: PantryItemDTO) : PantryAction()

    data class CreatePantryItem(val item: PantryItemDTO) : PantryAction()
    data class UpdatePantryItem(val item: PantryItemDTO) : PantryAction()

    data class PantryItemsFetched(val items: List<PantryItemDTO>): PantryAction()

    data object HomeTapped : PantryAction()

}

sealed class PantryEffect {
    data object NavigateToHome: PantryEffect()
}
class PantryStore(val user: UserService): Store<PantryState, PantryAction, PantryEffect>(PantryState(pantryItems = listOf())) {
    val api = PantryAPI()

    override fun reduce(state: PantryState, action: PantryAction): PantryState {
        return when (action) {
            is PantryAction.GoToShelf ->{ fetchPantryItems(action.type); state.copy(route = Shelf(action.type))}
            is PantryAction.GoToView -> state.copy(route = View)
            is PantryAction.GoToCreatePantryItem -> state.copy(route = Create)
            is PantryAction.GoToUpdatePantryItem -> state.copy(route = Update(action.item));
            is PantryAction.CreatePantryItem -> createPantryItem(state, action.item)
            is PantryAction.UpdatePantryItem -> updatePantryItem(state, action.item)
            is PantryAction.PantryItemsFetched -> state.copy(pantryItems = action.items)
            is PantryAction.HomeTapped -> { emit(PantryEffect.NavigateToHome); state}
        }
    }
    fun createPantryItem(state: PantryState, item: PantryItemDTO): PantryState {
        scope.launch{
            val currentGroupId = user.currentGroup
            if(currentGroupId != null) {
                api.create(currentGroupId, item).onSuccess {
                    println("Successfully created a new item: $item for household ${user.currentGroup!!}")
                    fetchPantryItems(enumValueOf<ShelfType>(item.category))
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
    fun updatePantryItem(state: PantryState, item: PantryItemDTO): PantryState {
        scope.launch{
            val currentGroupId = user.currentGroup
            if(currentGroupId != null) {
                api.update(currentGroupId, item).onSuccess {
                    println("Successfully updated a new item: $item for household ${item.groupId}")
                    fetchPantryItems(enumValueOf<ShelfType>(item.category))
                    dispatch(PantryAction.GoToShelf(enumValueOf<ShelfType>(item.category)))
                }.onFailure {
                    println("Error on UPDATE PANTRY: ${it.message}")
                    dispatch(PantryAction.GoToShelf(enumValueOf<ShelfType>(item.category)))
                }
            }
            else{
                println("No groupId is available")
            }


        }
        return state

    }
    fun fetchPantryItems(type: ShelfType) {
        scope.launch {
            val currentGroupId = user.currentGroup
            if(currentGroupId != null) {
                api.get(currentGroupId, type).onSuccess {
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