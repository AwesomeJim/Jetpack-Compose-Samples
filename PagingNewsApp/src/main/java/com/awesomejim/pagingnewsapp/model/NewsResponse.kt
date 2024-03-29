package com.awesomejim.pagingnewsapp.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)