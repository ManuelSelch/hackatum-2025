package common

import home.HomeStore
import kotlinx.coroutines.launch
import login.LoginEffect
import login.LoginStore
import pantry.PantryStore

data class AppState(
    val route: AppRoute = AppRoute.Pantry,
)

sealed class AppRoute {
    object Login : AppRoute()
    object Home : AppRoute()
    object Pantry : AppRoute()
}

sealed class AppAction {
    data class Navigate(val route: AppRoute): AppAction()
}

sealed class AppEffect {

}


class AppStore(): Store<AppState, AppAction, AppEffect>(AppState()) {
    val login = LoginStore()
    val home = HomeStore()
    val pantry = PantryStore()

    init {
        scope.launch {
            login.effects.collect { effect ->
                when (effect) {
                    is LoginEffect.NavigateToHome -> dispatch(AppAction.Navigate(AppRoute.Home))
                }
            }
        }
    }


    override fun reduce(state: AppState, action: AppAction): AppState {
        return when(action) {
            is AppAction.Navigate -> state.copy(route = action.route)
        }
    }
}