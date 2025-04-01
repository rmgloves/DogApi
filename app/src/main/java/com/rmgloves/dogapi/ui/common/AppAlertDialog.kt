package com.rmgloves.dogapi.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.ErrorMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAlertDialog(
    title: String? = null,
    message: ErrorMessage = ErrorMessage.Resource(),
    confirmLabel: String = stringResource(R.string.okay),
    dismissLabel: String? = null,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    dismissible: Boolean = true
) {
    val messageDisplay = when (message) {
        is ErrorMessage.Literal -> message.message
        is ErrorMessage.Resource -> stringResource(message.resId)
    }
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = dismissible)
    ) {
        Surface(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Text(
                    text = messageDisplay,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    dismissLabel?.let {
                        TextButton(
                            onClick = onDismiss
                        ) {
                            Text(text = it, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                    TextButton(
                        onClick = onConfirm
                    ) {
                        Text(text = confirmLabel, style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppAlertDialogPreviewBasicLight() {
    ThemedPreview {
        AppAlertDialog()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppAlertDialogPreviewFullLight() {
    ThemedPreview {
        AppAlertDialog(
            title = stringResource(R.string.error),
            dismissLabel = stringResource(R.string.cancel)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppAlertDialogPreviewBasicDark() {
    ThemedPreview {
        AppAlertDialog()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppAlertDialogPreviewFullDark() {
    ThemedPreview {
        AppAlertDialog(
            title = stringResource(R.string.error),
            dismissLabel = stringResource(R.string.cancel)
        )
    }
}

