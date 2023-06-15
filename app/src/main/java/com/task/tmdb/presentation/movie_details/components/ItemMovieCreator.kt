package com.task.tmdb.presentation.movie_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.task.tmdb.R
import com.task.tmdb.domain.model.CreatedBy

@Composable
fun ItemMovieCreator(p: CreatedBy) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier) {
        AsyncImage(
            model = p.imageUrl,
            contentDescription = p.name,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.no_image_available),
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = p.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.heightIn(min = 24.dp, max = 100.dp)
        )
    }
}