package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.core.Constants

class NewsPagingSource(private val apiService: ApiService, private val query: String? = null) :
    PagingSource<Int, ArticleDto>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        return try {
            val page = params.key ?: Constants.STARTING_PAGE_INDEX
            val result = if (query == null) {
                apiService.getBreakingNews(page)
            } else {
                apiService.searchForNews(query = query, page = page)
            }
            val articles = result.articles.filter { it.title != Constants.REMOVED_ARTICLE_TITLE }
            LoadResult.Page(
                data = articles,
                prevKey = if (page == Constants.STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}