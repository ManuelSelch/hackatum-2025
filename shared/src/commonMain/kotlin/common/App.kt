package common

import home.HomeAction
import home.HomeEffect
import home.HomeStore
import login.LoginAction
import login.LoginEffect
import login.LoginStore
import pantry.PantryEffect
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
    val user = UserService()

    val login = LoginStore()
    val home = HomeStore(user)
    val pantry = PantryStore(user)

    init {
        login.listen {
            when (it) {
                is LoginEffect.NavigateToHome -> {
                    home.dispatch(HomeAction.RefreshTapped)
                    dispatch(AppAction.Navigate(AppRoute.Home))
                }
            }
        }

        home.listen {
            when (it) {
                is HomeEffect.NavigateToPantry -> dispatch(AppAction.Navigate(AppRoute.Pantry))
                is HomeEffect.NavigateToSettings -> {
                    dispatch(AppAction.Navigate(AppRoute.Login))
                    login.dispatch(LoginAction.ShowSettings)
                }
            }
        }
        pantry.listen{
            when (it) {
                is PantryEffect.NavigateToHome -> dispatch(AppAction.Navigate(AppRoute.Home))
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