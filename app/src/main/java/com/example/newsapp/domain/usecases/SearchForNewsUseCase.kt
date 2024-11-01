package com.example.newsapp.domain.usecases

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchForNewsUseCase @Inject constructor(private val repository: NewsRepository) {
     operator fun invoke(query: String): Flow<PagingData<Article>> {
        return repository.searchForNews(query)
    }
}