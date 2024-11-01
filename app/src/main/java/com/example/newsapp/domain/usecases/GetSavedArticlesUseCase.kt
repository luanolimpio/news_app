package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getSavedArticles()
    }
}