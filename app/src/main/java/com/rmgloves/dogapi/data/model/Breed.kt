package com.rmgloves.dogapi.data.model

import com.rmgloves.dogapi.util.capitalize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Serializable
data class Breed(
    val breed: String,
    val subBreed: String? = null,
    val hasSubBreeds: Boolean = false
) {
    fun getDisplayString() = (subBreed?.capitalize()?.plus(" ") ?: "") + breed.capitalize()
    fun getImagesIdentifier() = breed + (subBreed?.let { "/$it" } ?: "")
    fun encodeClass(): String = try {
        URLEncoder.encode(Json.encodeToString(this), Charsets.UTF_8.name())
    } catch (e: Exception) {
        ""
    }

    companion object {
        fun decodeClass(encodedBreed: String): Breed {
            val decoded = URLDecoder.decode(encodedBreed, Charsets.UTF_8.name())
            return Json.decodeFromString<Breed>(decoded)
        }
    }
}

val testBreeds = listOf(
    Breed(breed = "dachshund"),
    Breed(breed = "husky"),
    Breed(breed = "hound", hasSubBreeds = true),
    Breed(breed = "hound", subBreed = "afghan"),
    Breed(breed = "hound", subBreed = "basset")

)
