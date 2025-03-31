package com.rmgloves.dogapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmgloves.dogapi.navigation.DogApiNavigation
import com.rmgloves.dogapi.ui.theme.DogApiTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogApiTheme {
                DogApiApp()
            }
        }
    }
}

@Composable
fun DogApiApp() {
    val viewModel = hiltViewModel<MainViewModel>()
    val connectionState = viewModel.connectionStatus.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = when (connectionState.value) {
        ConnectionState.Connecting -> stringResource(R.string.network_connecting)
        ConnectionState.NoConnection -> stringResource(R.string.network_not_connected)
        else -> null

    }
    Timber.d("snackbarMessage $snackbarMessage")
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            DogApiNavigation()
        }

        LaunchedEffect(snackbarMessage) {
            Timber.d("snackbar launched effect")
            snackbarMessage?.let {
                snackbarHostState.showSnackbar(
                    message = snackbarMessage,
                    duration = SnackbarDuration.Indefinite
                )
            } ?: snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
}
