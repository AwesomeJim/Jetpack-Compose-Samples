package com.awesomejim.pagingnewsapp.model

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize


@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
):Parcelable {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}

data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int
)

data class PostAuthor(
    val name: String,
    val url: String? = null
)

data class Publication(
    val name: String,
    val logoUrl: String
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList()
)

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)

enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}

val previewNewsResources = listOf(
    Article(
        author = "Parker Hall",
        title = "Apple Music Sing Adds 'Karaoke Mode' to Streaming Songs",
        description = "America's most popular music streaming service is adding the ability to turn down the vocals and sing along.",
        url = "https://www.wired.com/story/apple-music-sing/",
        urlToImage = "https://media.wired.com/photos/638f959b54aee410695ffa12/191:100/w_1280,c_limit/Apple-Music-Sing-Featured-Gear.jpg",
        publishedAt = "2022-12-06T20:51:11Z",
        content = "When it comes to advanced technical features and seamless compatibility with iOS devices, Apple Music has Spotify well and truly beaten. The Swedish streaming giant has essentially the same content l… [+3348 chars]",
        source = Source(
            id = "wired",
            name = "Wired"
        )
    )
)
