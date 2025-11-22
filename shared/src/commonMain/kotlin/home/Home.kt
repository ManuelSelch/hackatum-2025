package home

import common.Store
import kotlinx.coroutines.launch
import models.GroupResponse

data class HomeState(
    val groups: List<GroupResponse> = emptyList(),
    val route: HomeRoute = HomeRoute.Dashboard,
    val loading: Boolean = false,
    val error: String? = null,
    val current: GroupResponse? = null
)

enum class HomeRoute { Dashboard, CreateHouseHold, Invite, Join }

sealed class HomeAction {
    data object CreateHouseHoldTapped: HomeAction()
    data class CreateHouseHold(val name: String): HomeAction()
    data class GroupSelected(val group: GroupResponse): HomeAction()

    data object HouseholdCreated: HomeAction()
    data class Failed(val error: String): HomeAction()
    data class GroupsFetched(val groups: List<GroupResponse>): HomeAction()

    data object InviteTapped: HomeAction()
    data object JoinTapped: HomeAction()

    data class Join(val groupId: String): HomeAction()
    data object JoinSuccess: HomeAction()

    data object BackTapped: HomeAction()
    data object RefreshTapped: HomeAction()
}

sealed class HomeEffect {}

class HomeStore: Store<HomeState, HomeAction, HomeEffect>(HomeState()) {
    private val api = HomeAPI()

    override fun reduce(state: HomeState, action: HomeAction): HomeState {
        println("action:\t $action")

        return when (action) {
            is HomeAction.CreateHouseHoldTapped -> state.copy(route = HomeRoute.CreateHouseHold)
            is HomeAction.CreateHouseHold -> createHousehold(state, action.name)
            is HomeAction.GroupSelected -> state.copy(current = action.group)

            is HomeAction.HouseholdCreated -> state.copy(loading = false, error = null, route = HomeRoute.Dashboard)
            is HomeAction.Failed -> state.copy(loading = false, error = action.error)
            is HomeAction.GroupsFetched -> {
                state.copy(groups = action.groups, current = state.current ?: action.groups.firstOrNull(), loading = false, error = null)
            }

            is HomeAction.InviteTapped -> state.copy(route = HomeRoute.Invite)
            is HomeAction.JoinTapped -> state.copy(route = HomeRoute.Join)

            is HomeAction.Join -> join(state, action.groupId)
            is HomeAction.JoinSuccess -> state.copy(loading = false, error = null, route = HomeRoute.Dashboard)

            is HomeAction.BackTapped -> state.copy(route = HomeRoute.Dashboard)
            is HomeAction.RefreshTapped -> { fetchHouseholds(); state}
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
                .onSuccess { dispatch(HomeAction.GroupsFetched(it.groups)) }
                .onFailure { dispatch(HomeAction.Failed(it.toString()))}
        }
    }
    fun join(state: HomeState, groupId: String): HomeState {
        val groupIdParsed = groupId.toLongOrNull() ?: return state.copy(loading = false, error = "Group ID is missing")

        scope.launch {
            api.join(groupIdParsed)
                .onSuccess { dispatch(HomeAction.JoinSuccess) }
                .onFailure { dispatch(HomeAction.Failed(it.toString()))}
        }

        return  state.copy(loading = true, error = null)
    }
}