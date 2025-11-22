package home

import common.Store
import kotlinx.coroutines.launch
import login.LoginAction

data class HomeState(
    val households: List<String> = listOf("a", "b", "c"),
    val route: HomeRoute = HomeRoute.Dashboard,
    val loading: Boolean = false,
    val error: String? = null,
)

sealed class HomeRoute {
    data object Dashboard : HomeRoute()
    data object CreateHouseHold : HomeRoute()
}

sealed class HomeAction {
    data object CreateHouseHoldTapped: HomeAction()
    data class CreateHouseHold(val name: String): HomeAction()

    data object HouseholdCreated: HomeAction()
    data class HouseholdFailed(val error: String): HomeAction()
}

sealed class HomeEffect {}

class HomeStore: Store<HomeState, HomeAction, HomeEffect>(HomeState()) {
    private val api = HomeAPI()

    override fun reduce(state: HomeState, action: HomeAction): HomeState {
        println(action)

        return when (action) {
            is HomeAction.CreateHouseHoldTapped -> state.copy(route = HomeRoute.CreateHouseHold)
            is HomeAction.CreateHouseHold -> createHousehold(state, action.name)
            is HomeAction.HouseholdCreated -> state.copy(loading = false, error = null, route = HomeRoute.Dashboard)
            is HomeAction.HouseholdFailed -> state.copy(loading = false, error = action.error)
        }
    }

    fun createHousehold(state: HomeState, name: String): HomeState {
        println("create household $name")

        scope.launch {
            api.create(name)
                .onSuccess { household ->
                    println("success: $household")
                    dispatch(HomeAction.HouseholdCreated)
                }
                .onFailure { error ->
                    println("error: $error")
                    dispatch(HomeAction.HouseholdFailed(error.toString()))
                }
        }

        return state.copy(loading = true, error = null)
    }
}