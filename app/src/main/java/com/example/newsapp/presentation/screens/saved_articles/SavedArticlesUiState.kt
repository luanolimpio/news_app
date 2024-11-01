package com.example.newsapp.presentation.screens.saved_articles

import com.example.newsapp.domain.models.Article

data class SavedArticlesUiState(
    val articles: List<Article> = emptyList()
)
