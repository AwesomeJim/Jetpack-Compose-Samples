package com.awesomejim.pokemongrapgqlpaginglib.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.apollographql.apollo3.exception.ApolloException
import com.awesomejim.pokemongrapgqlpaginglib.data.local.PokemonDatabase
import com.awesomejim.pokemongrapgqlpaginglib.data.local.PokemonEntity
import com.awesomejim.pokemongrapgqlpaginglib.data.mapper.toPokemon
import com.awesomejim.pokemongrapgqlpaginglib.data.mapper.toPokemonEntity
import com.awesomejim.pokemongrapgqlpaginglib.data.remote.PokemonApi
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Response
import com.awesomejim.pokemongrapgqlpaginglib.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonPager: Pager<Int, PokemonEntity>,
    private val pokemonDatabase: PokemonDatabase,
    private val pokemonApi: PokemonApi,
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return pokemonPager.flow.map { pagingData ->
            pagingData.map { it.toPokemon() }
        }
    }


    override fun getPokemon(id: Int): Flow<Response<Pokemon>> = flow {
        // FETCH LOCAL DATA (IF ANY) & EMIT IT
        val localData = pokemonDatabase.pokemonDao.getById(id)
        if (localData != null) emit(Response.Loading(localData))
        try {
            // MAKE API CALL & INSERT IT TO DATABASE & EMIT IT
            val remoteData = pokemonApi.getPokemon(id)!!.toPokemon()
            pokemonDatabase.pokemonDao.insert(remoteData.toPokemonEntity())
            emit(Response.Success(remoteData))
        } catch (e: ApolloException) {
            emit(Response.Error(error = e.message.toString(), data = localData))
        }
    }
}
