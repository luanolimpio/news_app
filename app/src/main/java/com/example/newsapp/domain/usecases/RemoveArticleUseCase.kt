package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repositories.NewsRepository
import javax.inject.Inject

class RemoveArticleUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        repository.removeArticle(article)
    }
}