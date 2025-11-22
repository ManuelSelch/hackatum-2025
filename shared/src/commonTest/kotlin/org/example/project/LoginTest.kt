package org.example.project

import login.LoginAction
import login.LoginRoute
import login.LoginStore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

const val USERNAME = "MyUser"
const val PASSWORD = "MyPass"
const val ERROR = "MyError"

class LoginTest {
    val store = LoginStore()
    private val state get() = store.state.value

    @Test
    fun login_startsLoading() {
        store.dispatch(LoginAction.Login(USERNAME, PASSWORD))
        assertTrue { state.isLoading }
    }

    @Test
    fun register_startsLoading() {
        store.dispatch(LoginAction.Register(USERNAME, PASSWORD))
        assertTrue { state.isLoading }
    }

    @Test
    fun switchToRegister_showsRegisterRoute() {
        store.dispatch(LoginAction.SwitchToRegister)
        assertEquals(LoginRoute.Register,state.route)
    }
    @Test
    fun switchToLogin_showsLoginRoute() {
        store.dispatch(LoginAction.SwitchToRegister)
        assertEquals(LoginRoute.Register,state.route)

        store.dispatch(LoginAction.SwitchToLogin)
        assertEquals(LoginRoute.Login,state.route)
    }

    @Test
    fun authFailed_showsError() {
        store.dispatch(LoginAction.AuthFailed(ERROR))
        assertEquals(ERROR, state.error)
    }
    @Test
    fun authFailed_stopsLoading() {
        store.dispatch(LoginAction.Login(USERNAME, PASSWORD))
        assertTrue { state.isLoading }

        store.dispatch(LoginAction.AuthFailed(ERROR))
        assertFalse { state.isLoading }
    }

    @Test
    fun authSuccess_clearsError() {
        store.dispatch(LoginAction.AuthSuccess)
        assertNull(state.error)
    }
    @Test
    fun authSuccess_stopsLoading() {
        store.dispatch(LoginAction.Login(USERNAME, PASSWORD))
        assertTrue { state.isLoading }

        store.dispatch(LoginAction.AuthSuccess)
        assertFalse { state.isLoading }
    }


}