package com.example.newsapp.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.snackbar.SnackbarController
import com.example.newsapp.core.snackbar.SnackbarEvent
import com.example.newsapp.domain.usecases.GetSavedArticleUseCase
import com.example.newsapp.domain.usecases.RemoveArticleUseCase
import com.example.newsapp.domain.usecases.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedArticleUseCase: GetSavedArticleUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val removeArticle: RemoveArticleUseCase
) : ViewModel() {
    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.SaveOrRemoveArticle -> {
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(event.article.url)
                    if (article != null) {
                        removeArticle(article)
                        SnackbarController.send(SnackbarEvent(message = "Article removed"))
                    } else {
                        saveArticleUseCase(event.article)
                        SnackbarController.send(SnackbarEvent(message = "Article saved"))
                    }
                }
            }
        }
    }
}