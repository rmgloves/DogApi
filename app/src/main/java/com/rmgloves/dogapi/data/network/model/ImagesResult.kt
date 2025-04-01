package com.rmgloves.dogapi.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ImagesResult(
    val message: List<String>
)