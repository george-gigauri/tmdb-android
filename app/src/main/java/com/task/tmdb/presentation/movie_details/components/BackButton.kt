package com.task.tmdb.presentation.movie_details.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.task.tmdb.R


@Composable
@Preview
private fun DefaultBackButtonPreview() {
    DefaultBackButton(onClick = { })
}

@Composable
fun DefaultBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black,
) {
    IconButton(onClick = onClick, modifier = Modifier.then(modifier)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "back button",
            tint = tint
        )
    }
}