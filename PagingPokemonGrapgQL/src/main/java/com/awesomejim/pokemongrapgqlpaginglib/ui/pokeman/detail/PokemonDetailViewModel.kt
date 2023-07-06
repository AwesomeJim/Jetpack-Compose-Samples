package com.awesomejim.pokemongrapgqlpaginglib.ui.pokeman.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Response
import com.awesomejim.pokemongrapgqlpaginglib.domain.usecase.GetPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPokemon: GetPokemon,
) : ViewModel() {

    val pokemonResponse: StateFlow<Response<Pokemon>> =
        getPokemon(savedStateHandle.get<Int>("id")!!).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Response.Loading(),
        )
}
