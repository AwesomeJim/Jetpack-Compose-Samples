package com.awesomejim.pagingnewsapp.network

import com.awesomejim.pagingnewsapp.BuildConfig
import com.awesomejim.pagingnewsapp.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything?q=apple&sortBy=popularity&apiKey=${BuildConfig.newsApiKey}&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse
}