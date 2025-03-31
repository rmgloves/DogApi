package com.rmgloves.dogapi.data

import com.rmgloves.dogapi.data.network.DogApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton



@Singleton
class DogRepository(
    private val dogApiService: DogApiService
) {

    suspend fun getAllBreeds() = withContext(Dispatchers.IO) {
        dogApiService.getAllDogBreeds()
    }
}
