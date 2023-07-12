package com.awesomejim.pagingnewsapp.ui.nav

import android.os.Bundle
import androidx.navigation.NavType

//https://medium.com/@metaxas4/jetpack-compose-navigation-with-custom-object-3f9f06c0f69
abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}