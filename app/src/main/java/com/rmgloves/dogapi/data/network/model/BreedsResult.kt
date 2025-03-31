package com.rmgloves.dogapi.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BreedsResult(
    val message:Map<String, List<String>>
)