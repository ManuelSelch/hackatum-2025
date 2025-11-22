package org.example.project

import login.LoginAction
import login.LoginRoute
import login.LoginState
import login.LoginStore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LoginShould {
    val store = LoginStore()
    private val state get() = store.state.value

    @Test
    fun showRegisterRoute_whenSwitchToRegister() {
        store.dispatch(LoginAction.SwitchToRegister)
        assertEquals(LoginRoute.Register,state.route)
    }

    @Test
    fun showLoginRoute_whenSwitchToLogin() {
        store.dispatch(LoginAction.SwitchToRegister)
        assertEquals(LoginRoute.Register,state.route)

        store.dispatch(LoginAction.SwitchToLogin)
        assertEquals(LoginRoute.Login,state.route)
    }

    @Test
    fun showError_whenAuthFailed() {
        store.dispatch(LoginAction.AuthFailed("my error"))
        assertEquals("my error", state.error)
    }

    @Test
    fun clearError_whenAuthSuccess() {
        store.dispatch(LoginAction.AuthSuccess)
        assertNull(state.error)
    }
}