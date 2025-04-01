package com.rmgloves.dogapi.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rmgloves.dogapi.ui.theme.DogApiTheme

@Composable
fun ThemedPreview(content: @Composable () -> Unit) {
    DogApiTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}
