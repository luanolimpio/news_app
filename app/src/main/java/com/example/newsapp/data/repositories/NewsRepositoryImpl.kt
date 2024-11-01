package com.example.newsapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.newsapp.core.Constants
import com.example.newsapp.core.extensions.toArticle
import com.example.newsapp.core.extensions.toArticleEntity
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.ApiService
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getBreakingNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(apiService = apiService) }
        ).flow.map { pagingData -> pagingData.map { it.toArticle } }
    }

    override fun searchForNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(apiService = apiService, query = query) }
        ).flow.map { pagingData -> pagingData.map { it.toArticle } }
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return newsDao.getArticles().map { data -> data.map { it.toArticle } }
    }

    override suspend fun getSavedArticle(url: String): Article? {
        return newsDao.getArticle(url)?.toArticle
    }

    override suspend fun saveArticle(article: Article) {
        newsDao.insert(article.toArticleEntity)
    }

    override suspend fun removeArticle(article: Article) {
        newsDao.delete(article.toArticleEntity)
    }
}