package com.rmgloves.dogapi.data

import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.data.model.ErrorMessage
import com.rmgloves.dogapi.data.model.NetworkResult
import com.rmgloves.dogapi.data.network.DogApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DogRepository @Inject constructor(
    private val dogApiService: DogApiService
) {

    suspend fun getAllBreeds() = withContext(Dispatchers.IO) {
        try {
            val result = dogApiService.getAllDogBreeds().message
            if (result.isNotEmpty()) {
                val breedList = result.flatMap { (breed, subBreeds) ->
                    if (subBreeds.isEmpty()) {
                        listOf(Breed(breed))
                    } else {
                        subBreeds.map { sub ->
                            Breed(breed = breed, subBreed = sub)
                        }
                    }
                }
                NetworkResult.Success(breedList)
            } else {
                NetworkResult.Error(ErrorMessage.Resource(R.string.error_no_breeds))
            }
        } catch (e: IOException) {
            NetworkResult.Error(ErrorMessage.Resource(R.string.error_network))
        } catch (e: HttpException) {
            NetworkResult.Error(ErrorMessage.Literal(e.message()))
        } catch (e: Exception) {
            NetworkResult.Error(e.message?.let {
                ErrorMessage.Literal(it)
            } ?: ErrorMessage.Resource(R.string.error_generic))
        }
    }
}
