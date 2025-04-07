package com.rmgloves.dogapi.ui.breedphotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.ui.common.ErrorImage
import com.rmgloves.dogapi.ui.common.FallbackImage
import com.rmgloves.dogapi.ui.common.LayeredImage
import com.rmgloves.dogapi.ui.common.LayeredImageItem
import com.rmgloves.dogapi.ui.common.LoadingOverlay
import com.rmgloves.dogapi.ui.theme.Dimens

@Composable
fun BreedPhotos(breed: Breed) {
    val viewModel = hiltViewModel<BreedPhotosViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    when (val immutableState = state.value) {
        is BreedPhotosState.Error -> {

        }

        BreedPhotosState.Loading -> LoadingOverlay(true)
        is BreedPhotosState.Success -> {
            PhotoGrid(immutableState.images)
        }
    }
    LayeredImage(
        imageItem = ErrorImage
    )
}

@Composable
fun PhotoGrid(images: List<String>) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(Dimens.SpaceS),
        verticalArrangement = Arrangement.spacedBy(Dimens.SpaceS),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SpaceS),
        modifier = Modifier.fillMaxSize()
    ) {
        items(images) { url ->
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}