package home

import common.Store

data class HomeState(
    val households: List<String> = emptyList()
)

sealed class HomeAction {
    data object Test: HomeAction()
}

sealed class HomeEffect {}

class HomeStore: Store<HomeState, HomeAction, HomeEffect>(HomeState()) {
    override fun reduce(state: HomeState, action: HomeAction): HomeState {
        return when (action) {
            HomeAction.Test -> state
        }
    }
}