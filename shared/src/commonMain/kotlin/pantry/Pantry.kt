package pantry

import common.Store

data class PantryState(
    val route: PantryRoute = PantryRoute.View
)

sealed class PantryRoute {
    data object View : PantryRoute()
    data class Shelf(val type: String) : PantryRoute()
}
sealed class PantryAction {
    data object GoToView : PantryAction()
    data class GoToShelf( val type: String): PantryAction()
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