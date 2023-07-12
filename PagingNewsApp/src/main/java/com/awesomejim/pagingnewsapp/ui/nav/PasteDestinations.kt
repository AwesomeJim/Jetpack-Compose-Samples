
package com.awesomejim.pagingnewsapp.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.navArgument

/**
 * Contract for information needed on every Rally navigation destination
 */
interface RallyDestination {
    val icon: ImageVector
    val route: String
}

/**
 * Rally app navigation destinations
 */
object Home : RallyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "Home"
}


object SingleArticle : RallyDestination {
    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val icon = Icons.Filled.Money
    override val route = "single_article"
    private const val accountTypeArg = "article"
    val routeWithArgs = "${route}/{${accountTypeArg}}"
    // navArgument(accountTypeArg) { type = NavType.ParcelableType(Article::class.java) }
    val arguments = listOf(navArgument(accountTypeArg) {
        type = ArticleArgType()
    })
}


