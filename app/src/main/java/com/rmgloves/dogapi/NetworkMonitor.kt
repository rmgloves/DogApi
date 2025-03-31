package com.rmgloves.dogapi

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.rmgloves.dogapi.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitor @Inject constructor(
    @ApplicationContext
    private val context: Context,
    @ApplicationScope
    private val scope: CoroutineScope
) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _connectionState = MutableStateFlow(currentConnectionState())

    @OptIn(FlowPreview::class)
    val connectionState: StateFlow<ConnectionState> = _connectionState
        .debounce(300)
        .distinctUntilChanged()
        .stateIn(
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
            started = SharingStarted.Eagerly,
            initialValue = _connectionState.value
        )

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Timber.d("onAvailable")
            _connectionState.value = ConnectionState.Connecting
            scope.launch {
                _connectionState.value = if (verifyInternetAccess()) {
                    Timber.d("on available connected")
                    ConnectionState.Connected
                } else {
                    Timber.d("on available no connection")
                    ConnectionState.NoConnection
                }
            }
        }

        override fun onLost(network: Network) {
            Timber.d("onLost")
            _connectionState.value = ConnectionState.NoConnection
        }

        override fun onUnavailable() {
            Timber.d("onUnavailable")
            _connectionState.value = ConnectionState.NoConnection
        }

//        override fun onCapabilitiesChanged(
//            network: Network,
//            networkCapabilities: NetworkCapabilities
//        ) {
//            super.onCapabilitiesChanged(network, networkCapabilities)
//            Timber.d("onCapabilitiesChanged")
//            scope.launch {
//                _connectionState.value = if (verifyInternetAccess()) {
//                    Timber.d("capabilities changed, connected")
//                    ConnectionState.Connected
//                } else {
//                    Timber.d("capabilities changed, no connection")
//                    ConnectionState.NoConnection
//                }
//            }
//        }
    }

    init {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    private fun currentConnectionState(): ConnectionState {
        Timber.d("currentConnectionState")
        val network = connectivityManager.activeNetwork ?: return ConnectionState.NoConnection
        Timber.d("network is active")
        val capabilities = connectivityManager.getNetworkCapabilities(network)
            ?: return ConnectionState.NoConnection
        Timber.d("capabilities retrieved $capabilities")
        return if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        ) {
            Timber.d("Connected")
            ConnectionState.Connected
        } else {
            Timber.d("No Connection")
            ConnectionState.NoConnection
        }
    }

    private suspend fun verifyInternetAccess(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL("https://clients3.google.com/generate_204")
                val connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 1500
                connection.readTimeout = 1500
                connection.instanceFollowRedirects = false
                connection.useCaches = false
                connection.connect()
                connection.responseCode == 204
            } catch (e: IOException) {
                Timber.d("Call failed $e")
                false
            }
        }
    }
}

sealed class ConnectionState {
    data object Connected : ConnectionState()
    data object Connecting : ConnectionState()
    data object NoConnection : ConnectionState()
}
