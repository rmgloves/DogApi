package com.rmgloves.dogapi.data.network

import com.rmgloves.dogapi.data.network.model.BreedsResult
import retrofit2.http.GET

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getAllDogBreeds(): BreedsResult
}
