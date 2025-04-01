package com.rmgloves.dogapi.ui.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmgloves.dogapi.data.DogRepository
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.data.model.ErrorMessage
import com.rmgloves.dogapi.data.model.NetworkResult
import com.rmgloves.dogapi.util.capitalize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            when(val result = dogRepository.getAllBreeds()) {
                is NetworkResult.Error -> _state.emit(BreedListState.Error(result.error))
                is NetworkResult.Success<List<Breed>> -> {
                    _state.emit(BreedListState.Success(result.data))
                }
            }
        }
    }
}

sealed class BreedListState {
    data object Loading: BreedListState()
    data class Error(val errorMessage: ErrorMessage) : BreedListState()
    data class Success(val breedList: List<Breed>) : BreedListState()
}
