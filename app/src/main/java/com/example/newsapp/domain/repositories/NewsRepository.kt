package com.example.newsapp.domain.repositories

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getBreakingNews(): Flow<PagingData<Article>>

    fun searchForNews(query: String): Flow<PagingData<Article>>

    fun getSavedArticles(): Flow<List<Article>>

    suspend fun getSavedArticle(url: String): Article?

    suspend fun saveArticle(article: Article)

    suspend fun removeArticle(article: Article)
}