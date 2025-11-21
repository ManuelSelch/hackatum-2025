package login

import common.Store

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
}

sealed class LoginEffect {
    data class LoginSuccess(val username: String): LoginEffect()
    data class LoginFailed(val msg: String): LoginEffect()
}

class LoginStore: Store<LoginState, LoginAction, LoginEffect>(LoginState()) {
    override fun reduce(state: LoginState, action: LoginAction): LoginState {
        return  when(action) {
            is LoginAction.Submit -> {
                state.copy(isLoading = true)
            }
        }
    }
}