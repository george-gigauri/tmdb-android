package com.task.tmdb.presentation.movie_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.task.tmdb.R
import com.task.tmdb.domain.model.ProductionCompany

@Composable
fun ItemProductionCompany(p: ProductionCompany) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier) {
        AsyncImage(
            model = p.logoUrl,
            contentDescription = p.name,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.no_image_available),
            modifier = Modifier
                .height(24.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}