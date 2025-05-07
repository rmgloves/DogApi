package com.rmgloves.dogapi.data.network

import com.rmgloves.dogapi.data.network.model.BreedsResult
import com.rmgloves.dogapi.data.network.model.ImagesResult
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getAllDogBreeds(): BreedsResult

    @GET("breed/{breedIdentifier}/images")
    suspend fun getBreedImages(
        @Path(
            "breedIdentifier",
            encoded = true
        ) breedIdentifier: String
    ): ImagesResult
}
