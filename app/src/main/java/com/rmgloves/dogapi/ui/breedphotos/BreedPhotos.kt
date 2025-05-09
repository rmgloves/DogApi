package com.rmgloves.dogapi.ui.breedphotos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.ui.common.LoadingOverlay
import com.rmgloves.dogapi.ui.theme.Dimens

@Composable
fun BreedPhotos(breed: Breed, viewDetail: (List<String>, Int) -> Unit) {
    val viewModel = hiltViewModel<BreedPhotosViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(breed) {
        viewModel.loadImages(breed)
    }

    when (val immutableState = state.value) {
        is BreedPhotosState.Error -> {

        }

        BreedPhotosState.Loading -> LoadingOverlay(true)
        is BreedPhotosState.Success -> {
            PhotoGrid(immutableState.images, viewDetail)
        }
    }
}

@Composable
fun PhotoGrid(images: List<String>, viewDetail: (List<String>, Int) -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(Dimens.ImageSize),
            modifier = Modifier.fillMaxSize()
        ) {
            items(images) { url ->
                val imageBounds = remember { Ref<LayoutCoordinates>() }
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.ImageSize)
                        .clickable {
                            viewDetail(images, images.indexOf(url))
                        }
                        .onGloballyPositioned {
                            imageBounds.value = it
                        },
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
}
