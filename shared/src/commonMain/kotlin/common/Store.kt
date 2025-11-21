package common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Store<State, Action, Effect>(
    var initialState: State
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    fun dispatch(action: Action) {
        val newState = reduce(_state.value, action)
        _state.value = newState
    }

    abstract fun reduce(state: State, action: Action): State
}