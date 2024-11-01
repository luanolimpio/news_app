package com.example.newsapp.presentation.screens.search

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val query: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
