package com.example.newsapp.data.remote

data class NewsDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)