@file:OptIn(ExperimentalMaterial3Api::class)

package com.rmgloves.dogapi.ui.breedlist

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.data.model.testBreeds
import com.rmgloves.dogapi.ui.common.AppAlertDialog
import com.rmgloves.dogapi.ui.common.SearchField
import com.rmgloves.dogapi.ui.common.ThemedPreview
import com.rmgloves.dogapi.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedList(
    goToBreed: (String) -> Unit
) {
    val viewModel = hiltViewModel<BreedListViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val isRefreshing = state.value == BreedListState.Loading
    val refreshState = rememberPullToRefreshState()
    val errorMessage = (state.value as? BreedListState.Error)?.errorMessage
    var query by rememberSaveable { mutableStateOf("") }
    val filteredBreeds by remember(state.value, query) {
        derivedStateOf {
            val q = query.trim().lowercase()
            (state.value as? BreedListState.Success)?.breeds?.filter {
                it.breed.contains(q, ignoreCase = true) || it.subBreed?.contains(
                    q, ignoreCase = true
                ) ?: false
            } ?: emptyList()
        }
    }

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = isRefreshing,
        state = refreshState,
        onRefresh = {
            viewModel.loadBreeds()
        }) {

        errorMessage?.let {
            AppAlertDialog(
                message = it,
                confirmLabel = stringResource(R.string.retry),
                dismissLabel = stringResource(R.string.cancel),
                onConfirm = {
                    viewModel.loadBreeds()
                }
            )
        }

        Column {
            SearchField(query) {
                query = it
            }
            BreedListDisplay(filteredBreeds, query) { breed ->
                goToBreed(breed)
            }
        }
    }
}

@Composable
fun BreedListDisplay(breeds: List<Breed>, query: String = "", goToBreed: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.SpaceM),
    ) {
        if (breeds.isEmpty()) {
            val messageRes =
                if (query.isEmpty()) R.string.breed_list_refresh_hint else R.string.breed_list_no_match
            item {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = Dimens.SpaceM),
                    text = stringResource(messageRes),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            items(breeds) { breed ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            goToBreed(breed.encodeClass())
                        }
                        .padding(horizontal = Dimens.SpaceM, vertical = Dimens.SpaceM),
                    text = breed.getDisplayString(),
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
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
