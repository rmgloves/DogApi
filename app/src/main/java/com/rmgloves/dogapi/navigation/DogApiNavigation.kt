package com.rmgloves.dogapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.ui.breedlist.BreedList
import com.rmgloves.dogapi.ui.breedphotos.BreedPhotos

@Composable
fun DogApiNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BreedList.route
    ) {
        composable(Screen.BreedList.route) {
            BreedList { breed ->
                navController.navigate(Screen.BreedPhotos.createRoute(breed))
            }
        }
        composable(
            route = Screen.BreedPhotos.route,
            arguments = listOf(navArgument("encodedBreed") { type = NavType.StringType })
        ) {
            val encodedBreed = requireNotNull(it.arguments?.getString("encodedBreed"))

            BreedPhotos(Breed.decodeClass(encodedBreed))
        }
    }
}
