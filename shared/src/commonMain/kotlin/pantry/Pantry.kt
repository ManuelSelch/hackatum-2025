package pantry

import common.Store

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
    data class Shelf(val type: ShelfType) : PantryRoute()
}
sealed class PantryAction {
    data object GoToView : PantryAction()
    data class GoToShelf( val type: ShelfType): PantryAction()
}

sealed class PantryEffect {
}
class PantryStore: Store<PantryState, PantryAction, PantryEffect>(PantryState()) {
    override fun reduce(state: PantryState, action: PantryAction): PantryState {
        println(action)
        return when (action) {
            is PantryAction.GoToShelf -> state.copy(route = PantryRoute.Shelf(action.type))
            PantryAction.GoToView -> state.copy(route = PantryRoute.View)
        }
    }

}