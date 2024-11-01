package com.example.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.core.extensions.message
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.components.ArticleCard
import com.example.newsapp.presentation.components.EmptyState
import com.example.newsapp.presentation.components.ErrorState

@Composable
fun ArticleList(articles: LazyPagingItems<Article>, onItemClick: (Article) -> Unit) {
    val loadState = articles.loadState
    when {
        loadState.refresh is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        loadState.refresh is LoadState.Error -> {
            val error = loadState.refresh as LoadState.Error
            ErrorState(
                message = stringResource(error.message),
                onRefresh = { articles.refresh() })
        }

        articles.itemCount == 0 -> {
            EmptyState(onRefresh = { articles.refresh() })
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(all = 8.dp)
            ) {
                items(count = articles.itemCount) { index ->
                    articles[index]?.let {
                        ArticleCard(article = it, onClick = { onItemClick(it) })
                    }
                }
                item {
                    if (articles.loadState.append is LoadState.Loading) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }
}

