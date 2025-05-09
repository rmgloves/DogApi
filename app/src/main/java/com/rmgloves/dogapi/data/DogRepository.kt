package com.rmgloves.dogapi.data

import com.rmgloves.dogapi.R
import com.rmgloves.dogapi.data.model.Breed
import com.rmgloves.dogapi.data.model.ErrorMessage
import com.rmgloves.dogapi.data.model.NetworkResult
import com.rmgloves.dogapi.data.network.DogApiService
import kotlinx.coroutines.CoroutineDispatcher
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
    suspend fun getAllBreeds(): NetworkResult<List<Breed>> = safeApiCall {
        val response = dogApiService.getAllDogBreeds()
        response.message.flatMap { (breed, subBreeds) ->
            // if there is only 1 sub breed then just treat it as a dog without a sub breed
            listOf(Breed(breed = breed, hasSubBreeds = subBreeds.size > 1)) +
                    subBreeds.map { sub ->
                        Breed(breed = breed, subBreed = sub)
                    }
        }
    }

    suspend fun getBreedImages(breed: Breed): NetworkResult<List<String>> = safeApiCall {
        val identifier = breed.getImagesIdentifier()
        dogApiService.getBreedImages(identifier).message
    }

    private suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO, block: suspend () -> T
    ): NetworkResult<T> = withContext(dispatcher) {
        try {
            NetworkResult.Success(block())
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