package com.awesomejim.pagingnewsapp.ui.nav

import com.awesomejim.pagingnewsapp.model.Article
import com.google.gson.Gson

class ArticleArgType : JsonNavType<Article>() {
    override fun fromJsonParse(value: String): Article = Gson().fromJson(value, Article::class.java)

    override fun Article.getJsonParse(): String = Gson().toJson(this)
}
