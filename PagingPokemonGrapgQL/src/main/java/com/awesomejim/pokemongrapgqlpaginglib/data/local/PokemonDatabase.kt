package com.awesomejim.pokemongrapgqlpaginglib.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.awesomejim.pokemongrapgqlpaginglib.data.local.dao.PokemonDao
import com.awesomejim.pokemongrapgqlpaginglib.data.local.dao.RemoteKeyDao
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Database(entities = [PokemonEntity::class, RemoteKeyEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao
    abstract val remoteKeyDao: RemoteKeyDao
}


class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toStringList(value: String) = Json.decodeFromString<List<String>>(value)
}