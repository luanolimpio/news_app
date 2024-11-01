package com.example.newsapp.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.common.ArticleList

@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, onNavigateToDetails: (Article) -> Unit) {
    ArticleList(articles = articles, onItemClick = onNavigateToDetails)
}