package com.awesomejim.pokemongrapgqlpaginglib.domain.repository

import androidx.paging.PagingData
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Response
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>

    fun getPokemon(id: Int): Flow<Response<Pokemon>>
}
