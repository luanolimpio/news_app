package com.example.newsapp.domain.usecases

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(): Flow<PagingData<Article>> {
        return repository.getBreakingNews()
    }
}