package com.example.newsapp.presentation.screens.saved_articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.snackbar.SnackbarAction
import com.example.newsapp.core.snackbar.SnackbarController
import com.example.newsapp.core.snackbar.SnackbarEvent
import com.example.newsapp.domain.usecases.GetSavedArticlesUseCase
import com.example.newsapp.domain.usecases.RemoveArticleUseCase
import com.example.newsapp.domain.usecases.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArticlesViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val removeArticle: RemoveArticleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SavedArticlesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSavedArticles()
    }

    fun onEvent(event: SavedArticlesEvent) {
        when (event) {
            is SavedArticlesEvent.Remove -> {
                viewModelScope.launch {
                    removeArticle(event.article)
                    SnackbarController.send(
                        SnackbarEvent(
                            message = "Article removed",
                            action = SnackbarAction(
                                name = "UNDO",
                                action = {
                                    saveArticleUseCase(event.article)
                                },
                            )
                        )
                    )
                }
            }
        }
    }

    private fun getSavedArticles() {
        getSavedArticlesUseCase()
            .onEach {
                _uiState.value = _uiState.value.copy(articles = it)
            }.launchIn(viewModelScope)
    }
}