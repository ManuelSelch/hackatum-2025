package login

import common.Store
import kotlinx.coroutines.launch

data class LoginState(
    val isLoading: Boolean = false,
    var route: LoginRoute = LoginRoute.Login,
    var error: String? = null,

    // profile
    val username: String? = null,
    val email: String? = null
)

enum class LoginRoute { Login, Register, Settings}

sealed class LoginAction {
    data class Login(val email: String, val password: String): LoginAction()
    data class Register(val username: String, val email: String, val password: String) : LoginAction()

    data object SwitchToRegister : LoginAction()
    data object SwitchToLogin : LoginAction()

    data class AuthSuccess(val username: String, val email: String) : LoginAction()
    data class AuthFailed(val error: String): LoginAction()
    data object Logout: LoginAction()
    data class Update(val username: String, val email: String): LoginAction()
    data object ShowSettings : LoginAction()
}

sealed class LoginEffect {
    data object NavigateToHome: LoginEffect()
}

class LoginStore: Store<LoginState, LoginAction, LoginEffect>(LoginState()) {
    private val api = LoginAPI()

    override fun reduce(state: LoginState, action: LoginAction): LoginState {
        return  when(action) {
            is LoginAction.Login -> login(state, action.email, action.password)
            is LoginAction.Register -> register(state, action.username, action.email, action.password)

            is LoginAction.SwitchToLogin -> state.copy(route = LoginRoute.Login)
            is LoginAction.SwitchToRegister -> state.copy(route = LoginRoute.Register)

            is LoginAction.AuthSuccess -> state.copy(isLoading = false, error = null, username = action.username, email = action.email)
            is LoginAction.AuthFailed -> state.copy(isLoading = false, error = action.error)

            is LoginAction.ShowSettings -> state.copy(route = LoginRoute.Settings)
            is LoginAction.Logout -> logout(state)
            is LoginAction.Update -> updateProfile(state, action.username, action.email)
        }
    }

    fun login(state: LoginState, email: String, password: String): LoginState {
        scope.launch {
            api.login(email, password)
                .onSuccess {
                    dispatch(LoginAction.AuthSuccess(it.user.name, it.user.email))
                    emit(LoginEffect.NavigateToHome)
                }
                .onFailure { dispatch(LoginAction.AuthFailed(it.toString())) }
        }

        return state.copy(isLoading = true)
    }

    fun register(state: LoginState, username: String, email: String, password: String): LoginState {
        scope.launch {
            api.register(username, email,password)
                .onSuccess {
                    api.login(email, password).onSuccess {
                        dispatch(LoginAction.AuthSuccess(it.user.name, it.user.email))
                        emit(LoginEffect.NavigateToHome)
                    }.onFailure { dispatch(LoginAction.AuthFailed(it.toString())) }

                }
                .onFailure { dispatch(LoginAction.AuthFailed(it.toString())) }
        }

        return state.copy(isLoading = true)
    }

    fun logout(state: LoginState): LoginState {
        api.clearToken()
        return state.copy(route = LoginRoute.Login)
    }

    fun updateProfile(state: LoginState, username: String, email: String): LoginState {
        scope.launch {
            val success = api.update(username, email)
            if(success)
                emit(LoginEffect.NavigateToHome)
        }
        return state.copy(username = username, email = email)
    }
}