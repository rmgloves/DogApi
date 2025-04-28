package com.rmgloves.dogapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.rmgloves.dogapi.navigation.DogApiNavigation
import com.rmgloves.dogapi.ui.theme.DogApiTheme
import dagger.hilt.android.AndroidEntryPoint

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
    DogApiNavigation()
}
