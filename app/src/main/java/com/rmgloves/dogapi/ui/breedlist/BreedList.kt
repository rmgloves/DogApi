package com.rmgloves.dogapi.ui.breedlist

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.data.model.testBreeds
import com.rmgloves.dogapi.ui.common.AppAlertDialog
import com.rmgloves.dogapi.ui.common.LoadingOverlay
import com.rmgloves.dogapi.ui.common.SearchField
import com.rmgloves.dogapi.ui.common.ThemedPreview
import kotlinx.coroutines.delay

@Composable
fun BreedList(
    goToBreed: (String) -> Unit
) {
    val viewModel = hiltViewModel<BreedListViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state.value) {
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
            PullPrompt {
                viewModel.loadBreeds()
            }
        }

        is BreedListState.Success -> {
            BreedListDisplay(immutableState.breedList) { breed ->
                goToBreed(breed)
            }
        }

        else -> {}
    }

    LoadingOverlay(state.value is BreedListState.Loading)
}

@Composable
fun BreedListDisplay(breeds: List<Breed>, goToBreed: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val filteredBreeds = breeds.filter {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullPrompt(refresh: () -> Unit) {
    var refreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = refreshing,
        onRefresh = {
            refreshing = true
            refresh()
        }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 36.dp),
            text = stringResource(R.string.breed_list_refresh_hint),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BreedDisplayListPreviewLight() {
    ThemedPreview {
        BreedListDisplay(testBreeds) { }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BreedDisplayListPreviewDark() {
    ThemedPreview {
        BreedListDisplay(testBreeds) { }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PullPromptPreviewLight() {
    ThemedPreview {
        PullPrompt {}
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PullPromptPreviewDark() {
    ThemedPreview {
        PullPrompt {}
    }
}
