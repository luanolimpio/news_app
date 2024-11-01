package com.example.newsapp.data.remote

import com.example.newsapp.core.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.TOP_HEADLINES_END_POINT)
    suspend fun getBreakingNews(
        @Query("page") page: Int,
        @Query("country") countryCode: String = Constants.COUNTRY_CODE,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsDto

    @GET(Constants.EVERYTHING_END_POINT)
    suspend fun searchForNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = Constants.PAGE_SIZE,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsDto
}