package com.example.newsapp.core.snackbar

import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackbarEvent(
    val message: String,
    val action: SnackbarAction? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short,
)

data class SnackbarAction(
    val name: String,
    val action: suspend () -> Unit
)

object SnackbarController {
    private val _event = Channel<SnackbarEvent>()
    val event = _event.receiveAsFlow()

    suspend fun send(event: SnackbarEvent) {
        _event.send(event)
    }
}