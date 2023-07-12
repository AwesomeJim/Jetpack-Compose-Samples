package com.awesomejim.pagingnewsapp.ui.nav


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.awesomejim.pagingnewsapp.model.Article
import com.awesomejim.pagingnewsapp.ui.article.ArticleScreen
import com.awesomejim.pagingnewsapp.ui.news.NewsListScreen
import com.awesomejim.pagingnewsapp.ui.news.NewsViewModel
import com.google.gson.Gson


@Composable
fun NewsNavHost(
    viewModel: NewsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            NewsListScreen(
                viewModel = viewModel,
                onClickArticle = { accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(
            route = SingleArticle.routeWithArgs,
            arguments = SingleArticle.arguments,
        ) { navBackStackEntry ->
            // Retrieve the passed argument
//            val article =
//                navBackStackEntry.arguments?.getParcelable<Article>(SingleArticle.accountTypeArg) as Article
            val article = navBackStackEntry.arguments?.getString("article")
                ?.let { Gson().fromJson(it, Article::class.java) }
            article?.let {
                // Pass accountType to SingleAccountScreen
                ArticleScreen(
                    post = it,
                    isExpandedScreen = false,
                    onBack = {
                        navController.popBackStack()
                    },
                    isFavorite = false,
                    onToggleFavorite = { /*TODO*/ })
            }

        }

    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

private fun NavHostController.navigateToSingleAccount(accountType: Article) {
    this.navigateSingleTopTo("${SingleArticle.route}/$accountType")
}
