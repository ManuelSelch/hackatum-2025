package login

import common.Store
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.contracts.Effect

data class LoginState(
    var username: String = "Hallo Welt",
    val isLoading: Boolean = false,
    var route: LoginRoute = LoginRoute.Demo
)


enum class LoginRoute {
    Demo
}

sealed class LoginAction {
    data class Submit(val name: String): LoginAction()
    data object LoginDone : LoginAction()
}

sealed class LoginEffect {
    data object LoginSuccess: LoginEffect()
    data class LoginFailed(val msg: String): LoginEffect()
}

class LoginStore: Store<LoginState, LoginAction, LoginEffect>(LoginState()) {
    override fun reduce(state: LoginState, action: LoginAction): LoginState {
        return  when(action) {
            is LoginAction.Submit -> {
                scope.launch { validateLogin(state.username) }
                state.copy(isLoading = true)
            }

            is LoginAction.LoginDone -> state.copy(isLoading = false)
        }
    }

    suspend fun validateLogin(username: String) {
        delay(3000)

        if(username == "admin")
            emit(LoginEffect.LoginSuccess)
        else
            emit(LoginEffect.LoginFailed("you are stupid"))

        dispatch(LoginAction.LoginDone)
    }
}