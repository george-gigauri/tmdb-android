package com.task.tmdb.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.task.tmdb.common.Const
import com.task.tmdb.common.Screen
import com.task.tmdb.presentation.home.HomeScreen
import com.task.tmdb.presentation.movie_details.MovieDetailsScreen
import com.task.tmdb.presentation.ui.theme.TMDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TMDBTheme {
                CompactScreenContent(navController = navController)
            }
        }
    }
}

@Composable
private fun CompactScreenContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.name,
    ) {
        composable(Screen.HOME.name) {
            HomeScreen(navController = navController)
        }

        composable(
            "${Screen.MOVIE_DETAILS.name}/{${Const.PARAM_MOVIE_ID}}",
            arguments = listOf(
                navArgument(Const.PARAM_MOVIE_ID) {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            MovieDetailsScreen(navController)
        }
    }
}

@Composable
private fun ExpandedScreenContent(navController: NavHostController) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.width(150.dp)) {
            HomeScreen(navController = navController)
        }

        Box(modifier = Modifier.weight(1f)) {

        }
    }
}