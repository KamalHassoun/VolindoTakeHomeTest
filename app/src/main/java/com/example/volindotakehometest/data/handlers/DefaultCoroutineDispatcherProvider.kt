package com.example.volindotakehometest.data.handlers

import com.example.volindotakehometest.domain.handlers.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DefaultCoroutineDispatcherProvider @Inject constructor(): CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
    override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}