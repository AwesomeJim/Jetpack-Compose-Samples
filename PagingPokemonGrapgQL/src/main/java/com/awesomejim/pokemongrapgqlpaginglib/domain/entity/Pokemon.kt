package com.awesomejim.pokemongrapgqlpaginglib.domain.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String> = listOf(),
)
