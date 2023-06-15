package com.task.tmdb.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        HeaderText(text = "Popular TV Shows", modifier = Modifier.align(Alignment.CenterStart))
    }
}