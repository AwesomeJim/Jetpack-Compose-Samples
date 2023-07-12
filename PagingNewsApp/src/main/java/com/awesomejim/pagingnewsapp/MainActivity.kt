package com.awesomejim.pagingnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.awesomejim.pagingnewsapp.ui.nav.Home
import com.awesomejim.pagingnewsapp.ui.nav.NewsNavHost
import com.awesomejim.pagingnewsapp.ui.news.NewsViewModel
import com.awesomejim.pagingnewsapp.ui.theme.MyNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNewsAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                // Fetch your currentDestination:
                val currentBackStack by navController.currentBackStackEntryAsState()
                // Fetch your currentDestination:
                val currentDestination = currentBackStack?.destination
                // Change the variable to this and use Overview as a backup screen if this returns null
                  val currentScreen = currentDestination?.route ?: Home
                val viewModel = hiltViewModel<NewsViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsNavHost(
                        viewModel= viewModel,
                        navController = navController)
                }
            }
        }
    }
}
