package com.task.tmdb.presentation.movie_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.task.tmdb.presentation.home.components.HeaderText

@Composable
fun MovieDetailsScreenTopBar(
    title: String,
    isHomepageVisible: Boolean,
    onBackClick: () -> Unit,
    onHomepageClick: () -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        IconButton(onClick = onBackClick) {
            DefaultBackButton(onClick = onBackClick, tint = Color.White)
        }
        Spacer(modifier = Modifier.width(8.dp))
        HeaderText(text = title, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        if (isHomepageVisible) {
            ShortcutArrow(onClick = onHomepageClick, modifier = Modifier.padding(12.dp))
        }
    }
}