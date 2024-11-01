package com.example.newsapp.presentation.screens.details

import com.example.newsapp.domain.models.Article

sealed class DetailsEvent {
    data class SaveOrRemoveArticle(val article: Article) : DetailsEvent()
}