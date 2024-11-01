package com.example.newsapp.presentation.screens.saved_articles

import com.example.newsapp.domain.models.Article

sealed class SavedArticlesEvent {
    data class Remove(val article: Article) : SavedArticlesEvent()
}