package com.task.tmdb.presentation.movie_details.components

import androidx.compose.foundation.background
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.task.tmdb.R

@Composable
fun ShortcutArrow(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FilledTonalIconButton(
        onClick = onClick,
        modifier = Modifier.then(modifier)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_outward),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}