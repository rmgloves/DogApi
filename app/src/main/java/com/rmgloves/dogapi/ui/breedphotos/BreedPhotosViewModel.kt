package com.rmgloves.dogapi.ui.breedphotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmgloves.dogapi.data.DogRepository
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.data.model.ErrorMessage
import com.rmgloves.dogapi.data.model.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedPhotosViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreedPhotosState>(BreedPhotosState.Loading)
    val state = _state.asStateFlow()

    fun loadImages(breed: Breed) {
        viewModelScope.launch {
            _state.emit(
                when (val result = dogRepository.getBreedImages(breed)) {
                    is NetworkResult.Error -> BreedPhotosState.Error(result.error)
                    is NetworkResult.Success<List<String>> -> BreedPhotosState.Success(result.data)
                }
            )
        }
    }
}

sealed class BreedPhotosState {
    data object Loading : BreedPhotosState()
    data class Error(val errorMessage: ErrorMessage) : BreedPhotosState()
    data class Success(val images: List<String>) : BreedPhotosState()
}