package home

import common.Store
import kotlinx.coroutines.launch

data class HomeState(
    val households: List<String> = listOf("a", "b", "c"),
    val route: HomeRoute = HomeRoute.Dashboard,
    val loading: Boolean = false,
    val error: String? = null,
)

enum class HomeRoute { Dashboard, CreateHouseHold, Invite, Join }

sealed class HomeAction {
    data object CreateHouseHoldTapped: HomeAction()
    data class CreateHouseHold(val name: String): HomeAction()

    data object HouseholdCreated: HomeAction()
    data class Failed(val error: String): HomeAction()
    data class HouseholdsFetched(val households: List<String>): HomeAction()

    data object InviteTapped: HomeAction()
    data object JoinTapped: HomeAction()

    data class Join(val groupId: Long): HomeAction()
    data object JoinSuccess: HomeAction()
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
            is HomeAction.Failed -> state.copy(loading = false, error = action.error)
            is HomeAction.HouseholdsFetched -> state.copy(households = action.households, loading = false, error = null)

            is HomeAction.InviteTapped -> state.copy(route = HomeRoute.Invite)
            is HomeAction.JoinTapped -> state.copy(route = HomeRoute.Join)

            is HomeAction.Join -> join(state, action.groupId)
            is HomeAction.JoinSuccess -> state.copy(loading = false, error = null, route = HomeRoute.Dashboard)
        }
    }

    fun createHousehold(state: HomeState, name: String): HomeState {
        println("create household $name")

        scope.launch {
            api.create(name)
                .onSuccess { household ->
                    println("success: $household")
                    dispatch(HomeAction.HouseholdCreated)
                    fetchHouseholds()
                }
                .onFailure { error ->
                    println("error: $error")
                    dispatch(HomeAction.Failed(error.toString()))
                }
        }

        return state.copy(loading = true, error = null)
    }
    fun fetchHouseholds() {
        scope.launch {
            api.list()
                .onSuccess { res ->
                    val households = res.groups.map { g -> g.name }.toList()
                    dispatch(HomeAction.HouseholdsFetched(households))
                }
                .onFailure { error -> dispatch(HomeAction.Failed(error.toString()))}
        }
    }
    fun join(state: HomeState, groupId: Long): HomeState {
        scope.launch {
            api.join(groupId)
                .onSuccess {  }
                .onFailure { error -> dispatch(HomeAction.Failed(error.toString()))}
        }

        return  state.copy(loading = true, error = null)
    }
}