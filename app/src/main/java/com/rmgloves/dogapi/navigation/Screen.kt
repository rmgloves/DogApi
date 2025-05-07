package com.rmgloves.dogapi.navigation

sealed class Screen(val route: String) {
    data object BreedList : Screen(ROUTE_LIST)
    data object BreedPhotos : Screen("${ROUTE_PHOTOS}/{$ARG_BREED}") {
        fun createRoute(breed: String) = "${ROUTE_PHOTOS}/$breed"
    }

    companion object {
        const val ARG_BREED = "encodedBreed"
        const val ROUTE_LIST = "list"
        const val ROUTE_PHOTOS = "photos"
    }
}
