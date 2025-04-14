package com.rmgloves.dogapi.ui.breedphotos

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.ui.common.LoadingOverlay
import com.rmgloves.dogapi.ui.theme.Dimens
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun BreedPhotos(breed: Breed) {
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
            PhotoGrid(immutableState.images)
        }
    }
}

@Composable
fun PhotoGrid(images: List<String>) {
    val context = LocalContext.current

    val config = LocalConfiguration.current
    val metrics = context.resources.displayMetrics

    val screenWidth = config.screenWidthDp.dp
    val screenHeight = config.screenHeightDp.dp
    val adjustedSize = with(LocalDensity.current) {
        Dimens.ImageSize.toPx()
    }
    var originOffset by remember {
        mutableStateOf(
            Offset(
                (metrics.widthPixels - adjustedSize) / 2f,
                (metrics.heightPixels - adjustedSize) / 2f
            )
        )
    }
    var selectedImage by rememberSaveable { mutableStateOf<String?>(null) }
    var showAnimation by rememberSaveable { mutableStateOf(true) }
    var expanded by rememberSaveable { mutableStateOf(false) }

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
                            selectedImage = url
                            imageBounds.value?.let { coords ->
                                val windowOffset = coords.positionInParent()
                                originOffset = windowOffset
                            }
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
        selectedImage?.let { image ->
            LaunchedEffect(image) {
                if (showAnimation) {
                    expanded = true
                    showAnimation = false
                }
            }
            LaunchedEffect(expanded) {
                if (!expanded) {
                    delay(500)
                    selectedImage = null
                    showAnimation = true
                }
            }
            val targetOffset = Offset(0f, 0f)

            val width by animateDpAsState(
                targetValue = if (expanded) screenWidth else Dimens.ImageSize,
                animationSpec = tween(durationMillis = 500),
                label = "width"
            )
            val height by animateDpAsState(
                targetValue = if (expanded) screenHeight else Dimens.ImageSize,
                animationSpec = tween(durationMillis = 500),
                label = "height"
            )
            val offset by animateOffsetAsState(
                targetValue = if (expanded) targetOffset else originOffset,
                animationSpec = tween(durationMillis = 500),
                label = "box offset"
            )
            val alpha by animateFloatAsState(
                targetValue = if (expanded) 1f else 0f,
                animationSpec = tween(durationMillis = 500),
                label = "box alpha"
            )

            Box(
                modifier = Modifier
                    .size(width, height)
                    .offset {
                        IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                    }
                    .alpha(alpha)
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable {
                        showAnimation = true
                        expanded = !expanded
                    }
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = ImageRequest.Builder(context)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}
