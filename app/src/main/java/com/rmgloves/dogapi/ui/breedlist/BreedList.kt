package com.rmgloves.dogapi.ui.breedlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rmgloves.dogapi.data.model.Breed
import timber.log.Timber

@Composable
fun BreedList(
    goToBreed: (Breed) -> Unit
) {
    val viewModel = hiltViewModel<BreedListViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()

    when (val immutableState = state.value) {
        is BreedListState.Error -> {
            Timber.d(immutableState.errorMessage.toString())
        }

        BreedListState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is BreedListState.Success -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(immutableState.breedList) { breed ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                goToBreed(breed)
                            }
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        text = breed.getDisplayString(),
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }
}
