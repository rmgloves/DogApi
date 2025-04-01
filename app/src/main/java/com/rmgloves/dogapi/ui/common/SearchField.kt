package com.rmgloves.dogapi.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rmgloves.dogapi.R

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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchFieldPreviewLightEmpty() {
    ThemedPreview {
        SearchField("") { }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchFieldPreviewLightPopulated() {
    ThemedPreview {
        SearchField("Husky") { }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchFieldPreviewDarkEmpty() {
    ThemedPreview {
        SearchField("") { }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchFieldPreviewDarkPopulated() {
    ThemedPreview {
        SearchField("Husky") { }
    }
}
