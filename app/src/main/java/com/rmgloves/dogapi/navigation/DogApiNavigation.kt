package com.rmgloves.dogapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmgloves.dogapi.ui.breedlist.BreedList
import timber.log.Timber

@Composable
fun DogApiNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BreedList.route
    ) {
        composable(Screen.BreedList.route) {
            BreedList { breed ->
                Timber.d("breed selected $breed")
            }
        }
    }
}
