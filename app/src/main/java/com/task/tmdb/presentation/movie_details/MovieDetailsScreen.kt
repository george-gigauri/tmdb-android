package com.task.tmdb.presentation.movie_details

import android.content.Intent
import android.telecom.Call
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.task.tmdb.common.Result
import com.task.tmdb.common.Screen
import com.task.tmdb.domain.model.Movie
import com.task.tmdb.domain.model.MovieDetails
import com.task.tmdb.presentation.home.components.ItemMoviePoster
import com.task.tmdb.presentation.movie_details.components.BoldTitleText
import com.task.tmdb.presentation.movie_details.components.Error
import com.task.tmdb.presentation.movie_details.components.ItemMovieCreator
import com.task.tmdb.presentation.movie_details.components.ItemProductionCompany
import com.task.tmdb.presentation.movie_details.components.Loading
import com.task.tmdb.presentation.movie_details.components.MovieDetailsScreenTopBar
import com.task.tmdb.presentation.movie_details.components.RegularSubtitleText
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    navController: NavHostController,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val details: Result<MovieDetails> by viewModel.details.collectAsState()
    val similarMovies: Result<List<Movie>> by viewModel.similar.collectAsState()

    Scaffold(topBar = {
        MovieDetailsScreenTopBar(
            title = if (details is Result.Success) (details as Result.Success<MovieDetails>).result.name else "---",
            if (details is Result.Success) (details as Result.Success<MovieDetails>).result.homepage.isNotEmpty() else false,
            onBackClick = { navController.popBackStack() },
            onHomepageClick = {
                if (details is Result.Success) {
                    val homepageUrl = (details as Result.Success<MovieDetails>).result.homepage
                    val intent = Intent(Intent.ACTION_VIEW, homepageUrl.toUri())
                    context.startActivity(intent)
                }
            }
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {

            when (details) {
                is Result.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Loading(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is Result.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Error(
                            message = (details as Result.Error).message ?: "",
                            onRetry = { viewModel.refresh() },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                is Result.Success -> {
                    val movie = (details as Result.Success<MovieDetails>).result
                    // Display Details
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            // Poster
                            AsyncImage(
                                model = movie.posterUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            // Basic Details
                            Column(modifier = Modifier.weight(1f)) {
                                BoldTitleText(text = "Original Title")
                                RegularSubtitleText(text = movie.originalName)
                                Spacer(modifier = Modifier.height(12.dp))

                                BoldTitleText(text = "IMDB Rating")
                                RegularSubtitleText(text = movie.voteAverage.toString())
                                Spacer(modifier = Modifier.height(12.dp))

                                BoldTitleText(text = "Production Countries")
                                RegularSubtitleText(text = movie.productionCountries.joinToString(",") { it.name })
                                Spacer(modifier = Modifier.height(12.dp))

                                BoldTitleText(text = "Release Date")
                                RegularSubtitleText(
                                    text = SimpleDateFormat(
                                        "dd MMM, yyyy",
                                        Locale.getDefault()
                                    ).format(movie.firstAirDate)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Description
                        if (movie.overview.isNotEmpty()) {
                            BoldTitleText(text = "Description")
                            RegularSubtitleText(text = movie.overview)
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Production Companies
                        if (movie.productionCompanies.isNotEmpty()) {
                            BoldTitleText(text = "Companies")
                            Spacer(modifier = Modifier.height(12.dp))
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(movie.productionCompanies) { company ->
                                    ItemProductionCompany(p = company)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Authors
                        if (movie.createdBy.isNotEmpty()) {
                            BoldTitleText(text = "Authors")
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyRow(modifier = Modifier.fillMaxWidth()) {
                                items(movie.createdBy) { person ->
                                    ItemMovieCreator(p = person)
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        }

                        // Similar Movies Horizontal List
                        Spacer(modifier = Modifier.height(16.dp))
                        when (similarMovies) {
                            is Result.Loading -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Loading(modifier = Modifier.align(Alignment.Center))
                                }
                            }

                            is Result.Error -> {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Error(
                                        message = (similarMovies as Result.Error).message ?: "",
                                        onRetry = { viewModel.refresh() },
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }

                            is Result.Success -> {
                                val similar = (similarMovies as Result.Success).result

                                if (similar.isNotEmpty()) {
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        BoldTitleText(text = "Similar Movies")
                                        Spacer(modifier = Modifier.height(12.dp))

                                        LazyRow {
                                            items(similar) { item ->
                                                ItemMoviePoster(item = item, onClick = { movie ->
                                                    navController.navigate("${Screen.MOVIE_DETAILS.name}/${movie.id}")
                                                })
                                                Spacer(modifier = Modifier.width(12.dp))
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(12.dp))
                                    }
                                }
                            }

                            else -> Unit
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}