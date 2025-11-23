package pantry

import common.Store
import common.UserService
import kotlinx.coroutines.launch
import pantry.PantryRoute.*
data class PantryItem(
    var name: String,
    var unit: String?,
    val quantity: Double,
    var category: ShelfType,
    val minimumQuantity: Int?
)

data class PantryState(
    val route: PantryRoute = PantryRoute.View
)

enum class ShelfType(val label: String) {
    Food("Food"),
    Drinks("Drinks"),
    Miscellaneous("Miscellaneous")
}

sealed class PantryRoute {
    data object View : PantryRoute()
    data object Create : PantryRoute()
    data class Update(val item: PantryItem) : PantryRoute()
    data class Shelf(val type: ShelfType) : PantryRoute()
}
sealed class PantryAction {
    data object GoToView : PantryAction()
    data class GoToShelf( val type: ShelfType): PantryAction()

    data object GoToCreatePantryItem : PantryAction()

    data object GoToUpdatePantryItem : PantryAction()

    data class CreatePantryItem(val item: PantryItem) : PantryAction()
    data class UpdatePantryItem(val item: PantryItem) : PantryAction()
}

sealed class PantryEffect {
}
class PantryStore(val user: UserService): Store<PantryState, PantryAction, PantryEffect>(PantryState()) {
    val api = PantryAPI()

    override fun reduce(state: PantryState, action: PantryAction): PantryState {
        println(action)
        return when (action) {
            is PantryAction.GoToShelf -> state.copy(route = Shelf(action.type))
            PantryAction.GoToView -> state.copy(route = View)
            PantryAction.GoToCreatePantryItem -> state.copy(route = Create)
            PantryAction.GoToUpdatePantryItem -> state;
            is PantryAction.CreatePantryItem -> createPantryItem(state, action.item);
            is PantryAction.UpdatePantryItem -> {
                //api.update()
                state;
            };
        }
    }
    fun createPantryItem(state: PantryState, item: PantryItem): PantryState {
        scope.launch{
            api.create(user.currentGroup!!, item).onSuccess {
                    dispatch(PantryAction.GoToShelf(item.category))
            }.onFailure {

            }
        }
        return state

    }

}