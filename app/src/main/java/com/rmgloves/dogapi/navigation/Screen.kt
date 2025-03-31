package com.rmgloves.dogapi.navigation

sealed class Screen(val route: String) {
    data object BreedList : Screen("list")
    data object BreedPhotos : Screen("photos/{encodedBreed}") {
        fun createRoute(breed: String) = "photos/$breed"
    }
}
