package com.awesomejim.pokemongrapgqlpaginglib.domain.usecase

import androidx.paging.PagingData
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import com.awesomejim.pokemongrapgqlpaginglib.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return pokemonRepository.getPokemonList()
            .flowOn(Dispatchers.IO)
    }
}
