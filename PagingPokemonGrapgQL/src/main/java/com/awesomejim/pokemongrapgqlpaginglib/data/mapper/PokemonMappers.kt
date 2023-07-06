package com.awesomejim.pokemongrapgqlpaginglib.data.mapper

import com.awesomejim.pokemongrapgqlpaginglib.data.PokemonDetailQuery
import com.awesomejim.pokemongrapgqlpaginglib.data.PokemonListQuery
import com.awesomejim.pokemongrapgqlpaginglib.data.local.PokemonEntity
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import java.util.Locale

fun PokemonEntity.toPokemon() = Pokemon(
    id = id,
    name = name,
    imageUrl = imageUrl,
    types = types,
)

fun Pokemon.toPokemonEntity() = PokemonEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    types = types,
)

fun PokemonListQuery.Result.toPokemonEntity() = PokemonEntity(
    id = id ?: 0,
    name = name?.replaceFirstChar { it.uppercase(Locale.US) } ?: "",
    imageUrl = image ?: "",
    types = emptyList(),
)

fun PokemonDetailQuery.Pokemon.toPokemon() = Pokemon(
    id = id ?: 0,
    name = name?.replaceFirstChar { it.uppercase(Locale.US) } ?: "",
    imageUrl = sprites?.front_default ?: "",
    types = types?.mapNotNull { it?.type?.name } ?: emptyList(),
)
