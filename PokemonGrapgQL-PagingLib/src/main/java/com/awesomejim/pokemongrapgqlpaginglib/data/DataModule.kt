package com.awesomejim.pokemongrapgqlpaginglib.data

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.awesomejim.pokemongrapgqlpaginglib.data.local.PokemonDatabase
import com.awesomejim.pokemongrapgqlpaginglib.data.local.PokemonEntity
import com.awesomejim.pokemongrapgqlpaginglib.data.remote.PokemonApi
import com.awesomejim.pokemongrapgqlpaginglib.data.remote.PokemonRemoteMediator
import com.awesomejim.pokemongrapgqlpaginglib.data.repository.PokemonRepositoryImpl
import com.awesomejim.pokemongrapgqlpaginglib.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon.db",
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return PokemonApi()
    }
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePokemonPager(
        pokemonDatabase: PokemonDatabase,
        pokemonApi: PokemonApi,
    ): Pager<Int, PokemonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                pokemonDatabase = pokemonDatabase,
                pokemonApi = pokemonApi,
            ),
            pagingSourceFactory = {
                pokemonDatabase.pokemonDao.pagingSource()
            },
        )
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonPager: Pager<Int, PokemonEntity>,
        pokemonDatabase: PokemonDatabase,
        pokemonApi: PokemonApi,
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            pokemonPager = pokemonPager,
            pokemonDatabase = pokemonDatabase,
            pokemonApi = pokemonApi,
        )
    }
}