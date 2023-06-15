package com.task.tmdb.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.task.tmdb.common.Screen
import com.task.tmdb.presentation.home.components.HomeScreenTopBar
import com.task.tmdb.presentation.home.components.ItemMoviePoster

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val movies = viewModel.data.collectAsLazyPagingItems()

    Scaffold(topBar = { HomeScreenTopBar() }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                top = it.calculateTopPadding() + 12.dp,
                bottom = 12.dp
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(movies.itemCount) { index ->
                movies[index]?.let { movie ->
                    ItemMoviePoster(item = movie, onClick = { item ->
                        navController.navigate("${Screen.MOVIE_DETAILS.name}/${item.id}")
                    })
                }
            }

            // Handle Paging Error and Loading States
            when (val state = movies.loadState.refresh) { // FIRST LOAD
                is LoadState.Error -> {
                    item(span = { GridItemSpan(2) }) {
                        ErrorBox(message = state.error.localizedMessage, onRetry = {
                            movies.retry()
                        })
                    }
                }

                is LoadState.Loading -> {
                    item(span = { GridItemSpan(2) }) {
                        LoadingBox()
                    }
                }

                else -> Unit
            }

            when (val state = movies.loadState.append) { // PAGINATION AND ADDING DATA
                is LoadState.Error -> {
                    item(span = { GridItemSpan(2) }) {
                        ErrorBox(message = state.error.localizedMessage, onRetry = {
                            movies.retry()
                        })
                    }
                }

                is LoadState.Loading -> {
                    item(span = { GridItemSpan(2) }) {
                        LoadingBox()
                    }
                }

                else -> Unit
            }
        }
    }
}

@Composable
fun LoadingBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(54.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorBox(message: String?, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Text(
            text = message ?: "Unknown Error Occurred",
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        FilledTonalButton(onClick = { onRetry() }) {
            Text(
                text = "Retry",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}