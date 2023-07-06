package com.awesomejim.pokemongrapgqlpaginglib.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("remote_key")
data class RemoteKeyEntity(
    @PrimaryKey val id: String,
    val nextOffset: Int,
)