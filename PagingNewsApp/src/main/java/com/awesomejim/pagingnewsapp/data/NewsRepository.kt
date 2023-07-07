package com.awesomejim.pagingnewsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.awesomejim.pagingnewsapp.network.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApiService)
        }
    ).flow
}