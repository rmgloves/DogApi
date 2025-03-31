package com.rmgloves.dogapi.data.network

import retrofit2.http.GET

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getAllDogBreeds(): Map<String, List<String>>
}
