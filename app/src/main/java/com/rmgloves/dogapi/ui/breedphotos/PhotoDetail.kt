package com.rmgloves.dogapi.ui.breedphotos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rmgloves.dogapi.R

@Composable
fun PhotoDetail(
    images: List<String>,
    index: Int
) {
    val context = LocalContext.current
    var selectedImageIndex by rememberSaveable { mutableIntStateOf(index) }
    val maxImageIndex = images.size - 1

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = ImageRequest.Builder(context)
                .data(images[selectedImageIndex])
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            IconButton(
                enabled = selectedImageIndex > 0,
                onClick = {
                    selectedImageIndex--
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.previous)
                )
            }
            IconButton(
                enabled = selectedImageIndex < maxImageIndex,
                onClick = {
                    selectedImageIndex++
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = stringResource(R.string.next)
                )
            }
        }
    }
}