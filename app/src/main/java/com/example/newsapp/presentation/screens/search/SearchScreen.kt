package com.example.newsapp.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.components.SearchTextField

@Composable
fun SearchScreen(
    state: SearchUiState,
    onEvent: (SearchEvent) -> Unit,
    onNavigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTextField(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
            value = state.query,
            onValueChange = { onEvent(SearchEvent.QueryChanged(it)) },
            onSearch = { onEvent(SearchEvent.SearchNews) }
        )
        state.articles?.let {
            ArticleList(
                articles = it.collectAsLazyPagingItems(),
                onItemClick = onNavigateToDetails
            )
        }
    }
}