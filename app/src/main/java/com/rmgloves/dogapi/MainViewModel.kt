package com.rmgloves.dogapi

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val connectionStatus = networkMonitor.connectionState
}
