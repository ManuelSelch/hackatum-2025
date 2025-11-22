package login

import common.Store
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class LoginState(
    val isLoading: Boolean = false,
    var route: LoginRoute = LoginRoute.Login,
    var error: String? = null,
)

enum class LoginRoute { Login, Register, }

sealed class LoginAction {
    data class Login(val username: String, val password: String): LoginAction()
    data class Register(val username: String, val password: String) : LoginAction()

    data object SwitchToRegister : LoginAction()
    data object SwitchToLogin : LoginAction()

    data object AuthSuccess : LoginAction()
    data class AuthFailed(val error: String): LoginAction()
}

sealed class LoginEffect {
    data object NavigateToHome: LoginEffect()
}

class LoginStore: Store<LoginState, LoginAction, LoginEffect>(LoginState()) {
    private val api = LoginAPI()

    override fun reduce(state: LoginState, action: LoginAction): LoginState {
        return  when(action) {
            is LoginAction.Login -> handleAuth(state, action.username, action.password, false)
            is LoginAction.Register -> handleAuth(state, action.username, action.password, true)

            is LoginAction.SwitchToLogin -> state.copy(route = LoginRoute.Login)
            is LoginAction.SwitchToRegister -> state.copy(route = LoginRoute.Register)

            is LoginAction.AuthSuccess -> state.copy(isLoading = false, error = null)
            is LoginAction.AuthFailed -> state.copy(isLoading = false, error = action.error)
        }
    }

    fun handleAuth(state: LoginState, username: String, password: String, isRegister: Boolean): LoginState {
        scope.launch {
            val result = if (isRegister) api.register(username, password) else api.login(username, password)

            result
                .onSuccess {
                    dispatch(LoginAction.AuthSuccess)
                    emit(LoginEffect.NavigateToHome)
                }
                .onFailure {
                    dispatch(LoginAction.AuthFailed("auth failed"))
                }
        }

        return state.copy(isLoading = true)
    }
}