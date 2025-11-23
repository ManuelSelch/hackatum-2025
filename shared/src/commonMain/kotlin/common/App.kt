package common

import home.HomeEffect
import home.HomeStore
import kotlinx.coroutines.launch
import login.LoginEffect
import login.LoginStore
import pantry.PantryStore

data class AppState(
    val route: AppRoute = AppRoute.Login,
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
        login.listen {
            when (it) {
                is LoginEffect.NavigateToHome -> dispatch(AppAction.Navigate(AppRoute.Home))
            }
        }

        home.listen {
            when (it) {
                is HomeEffect.NavigateToPantry -> dispatch(AppAction.Navigate(AppRoute.Pantry))
            }
        }
    }


    override fun reduce(state: AppState, action: AppAction): AppState {
        println(action)

        return when(action) {
            is AppAction.Navigate -> state.copy(route = action.route)
        }
    }
}