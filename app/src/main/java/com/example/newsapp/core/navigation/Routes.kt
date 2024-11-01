package com.example.newsapp.core.navigation

import com.example.newsapp.domain.models.Article
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Search : Routes()

    @Serializable
    data object SavedArticles : Routes()

    @Serializable
    data class Details(val article: Article) : Routes()
}