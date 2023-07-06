package com.awesomejim.pokemongrapgqlpaginglib.ui.pokeman.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import com.awesomejim.pokemongrapgqlpaginglib.domain.usecase.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonList: GetPokemonList
) : ViewModel() {

    val pokemonPagingDataFlow: Flow<PagingData<Pokemon>> = getPokemonList()
        .cachedIn(viewModelScope)
}