package com.rmgloves.dogapi.data.model

import com.rmgloves.dogapi.util.capitalize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Serializable
data class Breed(val breed: String, val subBreed: String? = null) {
    fun getDisplayString() = (subBreed?.capitalize()?.plus(" ") ?: "") + breed.capitalize()
    fun getImageIdentifier() = breed + (subBreed?.let { "-$it" } ?: "")
    fun encodeClass(): String = try {
        URLEncoder.encode(Json.encodeToString(this), "UTF-8")
    } catch (e: Exception) {
        ""
    }

    companion object {
        fun decodeClass(encodedBreed: String): Breed {
            val decoded = URLDecoder.decode(encodedBreed, "UTF-8")
            return Json.decodeFromString<Breed>(decoded)
        }
    }
}
