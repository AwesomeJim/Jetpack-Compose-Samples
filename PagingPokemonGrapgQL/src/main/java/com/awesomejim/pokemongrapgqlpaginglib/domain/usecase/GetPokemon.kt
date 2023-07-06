package com.awesomejim.pokemongrapgqlpaginglib.domain.usecase

import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Response
import com.awesomejim.pokemongrapgqlpaginglib.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokemon @Inject constructor(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(id: Int): Flow<Response<Pokemon>> {
        return pokemonRepository.getPokemon(id).flowOn(Dispatchers.IO)
    }
}
