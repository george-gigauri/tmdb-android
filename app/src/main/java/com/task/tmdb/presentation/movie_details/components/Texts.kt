package com.task.tmdb.presentation.movie_details.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BoldTitleText(text: String, modifier: Modifier = Modifier, color: Color = Color.Black) {
    Text(
        text = text,
        color = color,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.then(modifier)
    )
}

@Composable
fun RegularSubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.DarkGray
) {
    Text(
        text = text,
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.then(modifier)
    )
}