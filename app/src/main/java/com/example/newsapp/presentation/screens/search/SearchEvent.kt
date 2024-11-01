package com.example.newsapp.presentation.screens.search

sealed class SearchEvent {
    data class QueryChanged(val text: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}