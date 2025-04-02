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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.ErrorMessage
import com.rmgloves.dogapi.ui.theme.Dimens
import java.util.UUID

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
    var internalShow by remember { mutableStateOf(true) }
    var lastKey by remember { mutableStateOf<UUID?>(null) }

    LaunchedEffect(message) {
        if (message.key != lastKey) {
            lastKey = message.key
            internalShow = true
        }
    }

    val messageDisplay = when (message) {
        is ErrorMessage.Literal -> message.message
        is ErrorMessage.Resource -> stringResource(message.resId)
    }
    if (internalShow) {
        BasicAlertDialog(
            onDismissRequest = {
                internalShow = false
                onDismiss()
            },
            properties = DialogProperties(dismissOnClickOutside = dismissible)
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = Dimens.SpaceS
            ) {
                Column(modifier = Modifier.padding(Dimens.SpaceM)) {
                    title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(Dimens.SpaceM))
                    }
                    Text(
                        text = messageDisplay,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimens.SpaceM),
                        horizontalArrangement = Arrangement.End
                    ) {
                        dismissLabel?.let {
                            TextButton(
                                onClick = {
                                    internalShow = false
                                }
                            ) {
                                Text(text = it, style = MaterialTheme.typography.labelLarge)
                            }
                        }
                        TextButton(
                            onClick = {
                                internalShow = false
                                onConfirm()
                            }
                        ) {
                            Text(text = confirmLabel, style = MaterialTheme.typography.labelLarge)
                        }
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

