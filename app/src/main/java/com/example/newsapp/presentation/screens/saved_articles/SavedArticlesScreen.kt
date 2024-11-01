package com.example.newsapp.presentation.screens.saved_articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.components.ArticleCard
import com.example.newsapp.presentation.components.EmptyState
import com.example.newsapp.presentation.components.SwipeToDeleteContainer

@Composable
fun SavedArticlesScreen(
    state: SavedArticlesUiState,
    onEvent: (SavedArticlesEvent) -> Unit,
    onNavigateToDetails: (Article) -> Unit
) {
    if (state.articles.isEmpty()) {
        EmptyState(
            description = stringResource(R.string.you_dont_have_any_saved_articles_yet),
            onRefresh = {}
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = state.articles, key = { it.hashCode() }) {
            SwipeToDeleteContainer(onDelete = { onEvent(SavedArticlesEvent.Remove(it)) }) {
                ArticleCard(article = it, onClick = { onNavigateToDetails(it) })
            }
        }
    }
}