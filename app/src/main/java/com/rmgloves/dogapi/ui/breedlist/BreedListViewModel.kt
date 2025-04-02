package com.rmgloves.dogapi.ui.breedlist

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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreedListState>(BreedListState.Loading)
    val state = _state.asStateFlow()

    init {
        loadBreeds()
    }

    fun loadBreeds() {
        Timber.d("Loading breeds")
        viewModelScope.launch {
            _state.emit(
                when (val result = dogRepository.getAllBreeds()) {
                    is NetworkResult.Error -> BreedListState.Error(result.error)
                    is NetworkResult.Success<List<Breed>> -> {
                        BreedListState.Success(result.data)
                    }
                }
            )
        }
    }
}

sealed class BreedListState {
    data object Loading : BreedListState()
    data class Error(val errorMessage: ErrorMessage) : BreedListState()
    data class Success(val breeds: List<Breed>) : BreedListState()
}
