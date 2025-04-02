package com.rmgloves.dogapi.ui.breedphotos

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rmgloves.dogapi.data.model.Breed

@Composable
fun BreedPhotos(breed: Breed) {
    val viewModel = hiltViewModel<BreedPhotosViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
//    when(val immutableState = state.value) {
//        is BreedPhotosState.Error -> TODO()
//        BreedPhotosState.Loading -> LoadingOverlay()
//        is BreedPhotosState.Success -> {
//            PhotoGrid(immutableState.images)
//        }
//    }
}

@Composable
fun PhotoGrid(images: List<String>) {

}