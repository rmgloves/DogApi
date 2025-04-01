package com.rmgloves.dogapi.ui.breedlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.ui.common.AppAlertDialog

@Composable
fun BreedList(
    goToBreed: (String) -> Unit
) {
    val viewModel = hiltViewModel<BreedListViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(state) {
        showDialog = state.value is BreedListState.Error
    }

    when (val immutableState = state.value) {
        is BreedListState.Error -> {
            if (showDialog) {
                AppAlertDialog(
                    message = immutableState.errorMessage,
                    confirmLabel = stringResource(R.string.retry),
                    dismissLabel = stringResource(R.string.cancel),
                    onConfirm = {
                        showDialog = false
                        viewModel.loadBreeds()
                    },
                    onDismiss = {
                        showDialog = false
                    }
                )
            }
            Text(
                text = stringResource(R.string.breed_list_refresh_hint),
                style = MaterialTheme.typography.bodyLarge
            )
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
            var query by remember { mutableStateOf("") }
            val filteredBreeds = immutableState.breedList.filter {
                val q = query.trim().lowercase()
                it.breed.contains(q, ignoreCase = true) || it.subBreed?.contains(
                    q,
                    ignoreCase = true
                ) == true
            }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                SearchField(query) {
                    query = it
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredBreeds) { breed ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    goToBreed(breed.encodeClass())
                                }
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            text = breed.getDisplayString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchField(
    query: String,
    onChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = query,
        onValueChange = onChanged,
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.titleSmall
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { onChanged("") }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.clear)
                    )
                }
            }
        }
    )
}
