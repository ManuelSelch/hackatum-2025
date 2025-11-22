package common

import common.AppEffect.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import login.LoginEffect
import login.LoginStore

data class AppState(
    val route: AppRoute = AppRoute.Login,
)

sealed class AppRoute {
    object Login : AppRoute()
    object Home : AppRoute()
}

sealed class AppAction {
    data class Navigate(val route: AppRoute): AppAction()
}

sealed class AppEffect {

}


class AppStore(): Store<AppState, AppAction, AppEffect>(AppState()) {
    val login = LoginStore()

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