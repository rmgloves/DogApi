package com.rmgloves.dogapi.navigation

sealed class Screen(val route: String) {
    data object BreedList : Screen("list")
    data object BreedPhotos: Screen("photos/{breed}") {
        fun createRoute(breed: String) = "details/$breed"
    }
}
