package login

import common.Store
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class LoginState(
    val isLoading: Boolean = false,
    var route: LoginRoute = LoginRoute.Login
)

enum class LoginRoute {
    Login,
    Register,
}

sealed class LoginAction {
    data class Login(val username: String, val password: String): LoginAction()
    data class Register(val username: String, val password: String) : LoginAction()

    data object SwitchToRegister : LoginAction()
    data object SwitchToLogin : LoginAction()
    data object LoadingDone : LoginAction()
}

sealed class LoginEffect {
    data object LoginSuccess: LoginEffect()
    data class LoginFailed(val msg: String): LoginEffect()
}

class LoginStore: Store<LoginState, LoginAction, LoginEffect>(LoginState()) {
    override fun reduce(state: LoginState, action: LoginAction): LoginState {
        return  when(action) {
            is LoginAction.Login -> {
                scope.launch { login(action.username) }
                state.copy(isLoading = true)
            }

            is LoginAction.Register -> {
                scope.launch { register(action.username, action.password) }
                state.copy(isLoading = true)
            }

            is LoginAction.SwitchToLogin -> state.copy(route = LoginRoute.Login)
            is LoginAction.SwitchToRegister -> state.copy(route = LoginRoute.Register)
            is LoginAction.LoadingDone -> state.copy(isLoading = false)
        }
    }

    suspend fun login(username: String) {
        delay(3000)

        if(username == "admin")
            emit(LoginEffect.LoginSuccess)
        else
            emit(LoginEffect.LoginFailed("you are stupid: $username"))

        dispatch(LoginAction.LoadingDone)
    }

    suspend fun register(username: String, password: String) {
        delay(1000)
        dispatch(LoginAction.LoadingDone)
    }
}