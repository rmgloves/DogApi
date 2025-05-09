package com.rmgloves.dogapi.navigation

import java.net.URLEncoder

sealed class Screen(val route: String) {
    data object BreedList : Screen(ROUTE_LIST)
    data object BreedPhotos : Screen("${ROUTE_PHOTOS}/{$ARG_BREED}") {
        fun createRoute(breed: String) = "${ROUTE_PHOTOS}/$breed"
    }

    data object PhotoDetail :
        Screen("${ROUTE_DETAIL}/{${ARG_DETAIL_IMAGES}}/{${ARG_DETAIL_INDEX}}") {
        fun createRoute(images: List<String>, index: Int): String {
            val encodedImages = URLEncoder.encode(images.joinToString(","), Charsets.UTF_8.name())
            return "${ROUTE_DETAIL}/$encodedImages/$index"
        }
    }

    companion object {
        // Nav Arguments
        const val ARG_BREED = "encodedBreed"
        const val ARG_DETAIL_IMAGES = "detailImages"
        const val ARG_DETAIL_INDEX = "detailIndex"

        // Routes
        const val ROUTE_DETAIL = "detail"
        const val ROUTE_LIST = "list"
        const val ROUTE_PHOTOS = "photos"
    }
}
