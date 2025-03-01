package com.example.volindotakehometest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volindotakehometest.domain.handlers.CoroutineDispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<T, I>(initialValue: T) : ViewModel() {
    @Inject
    lateinit var dispatcher: CoroutineDispatcherProvider

    protected val _uiState = MutableStateFlow(initialValue)
    val uiState = _uiState.asStateFlow()
    val intent: Channel<I> = Channel()

    init {
        observeIntents()
    }

    private fun observeIntents() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect { processIntents(it) }
        }
    }

    abstract fun processIntents(intent: I)
}