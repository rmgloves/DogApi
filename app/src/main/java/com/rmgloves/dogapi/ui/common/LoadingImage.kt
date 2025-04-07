package com.rmgloves.dogapi.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.fallback
import com.rmgloves.dogapi.R

@Composable
fun LoadingImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    val request = ImageRequest.Builder(context)
        .data(url)
        .fallback(R.drawable.placeholder_image)
        .crossfade(true)
        .build()

    SubcomposeAsyncImage(
        modifier = modifier,
        model = request,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = {},
        error = {},
        success = {}
    )
}
