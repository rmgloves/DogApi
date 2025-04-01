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
import com.rmgloves.dogapi.ui.theme.DogApiTheme

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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchFieldPreviewLightEmpty() {
    DogApiTheme {
        SearchField("") { }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchFieldPreviewLightPopulated() {
    DogApiTheme {
        SearchField("Husky") { }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchFieldPreviewDarkEmpty() {
    DogApiTheme {
        SearchField("") { }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchFieldPreviewDarkPopulated() {
    DogApiTheme {
        SearchField("Husky") { }
    }
}
