package com.awesomejim.pokemongrapgqlpaginglib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import com.awesomejim.pokemongrapgqlpaginglib.ui.custom.TopBar
import com.awesomejim.pokemongrapgqlpaginglib.ui.pokeman.detail.PokemonDetailScreen
import com.awesomejim.pokemongrapgqlpaginglib.ui.pokeman.list.PokemonListScreen
import com.awesomejim.pokemongrapgqlpaginglib.ui.theme.PokemonPagerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonPagerTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    topBar = { TopBar(navController = navController) },
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = "pokemon-list",
                    ) {
                        composable(
                            "pokemon-list",
                            label = "Gotta Catch 'Em All!",
                        ) {
                            PokemonListScreen(
                                navigateToDetail = { id ->
                                    navController.navigate("pokemon/$id")
                                },
                                snackbarHostState = snackbarHostState,
                            )
                        }
                        composable(
                            "pokemon/{id}",
                            label = "Pokemon",
                            arguments = listOf(navArgument("id") { type = NavType.IntType }),
                        ) {
                            PokemonDetailScreen(snackbarHostState = snackbarHostState)
                        }
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.composable(
    route: String,
    label: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
        this.route = route
        this.label = label // SET LABEL
        arguments.forEach { (argumentName, argument) ->
            addArgument(argumentName, argument)
        }
        deepLinks.forEach { deepLink ->
            addDeepLink(deepLink)
        }
    })
}
