package common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class Store<State, Action, Effect>(
    var initialState: State
) {
    val scope = CoroutineScope(Dispatchers.Default)

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    val effects = MutableSharedFlow<Effect>()

    fun dispatch(action: Action) {
        val newState = reduce(_state.value, action)
        _state.value = newState
    }

    fun emit(effect: Effect) {
        scope.launch {
            effects.emit(effect)
        }
    }

    abstract fun reduce(state: State, action: Action): State
}