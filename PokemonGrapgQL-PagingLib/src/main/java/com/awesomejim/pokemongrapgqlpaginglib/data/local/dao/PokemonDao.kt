package com.awesomejim.pokemongrapgqlpaginglib.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.awesomejim.pokemongrapgqlpaginglib.data.local.PokemonEntity
import com.awesomejim.pokemongrapgqlpaginglib.domain.entity.Pokemon


@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: PokemonEntity)

    @Query("SELECT * FROM pokemon WHERE id=:id")
    suspend fun getById(id: Int): Pokemon?

    @Query("SELECT * FROM pokemon")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}